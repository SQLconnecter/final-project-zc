package com.baizhi.serviceimple;

import com.baizhi.aspect.LogAnnotation;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.XLSUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class UserServiceImple implements UserService {
    @Autowired
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(UserServiceImple.class);
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> showByPage(int page, int rows) {
        int start = (page-1)*5;
        List<User> users = userDao.queryByPage(start, rows);
        return users;
    }

    @Override
    @LogAnnotation(name = "更改用户状态")
    public User change(String id, boolean status) {
        userDao.updateStatus(id,status);
        User user = userDao.queryById(id);
        return user;
    }

    @Override
    @LogAnnotation( name = "批量导入数据")
    public void importDB(MultipartFile file) {
        List<User> users = XLSUtil.read(file);
        userDao.addList(users);
        logger.info("用户进行了数据库批量导入的操作，插入数据："+users.size()+"条");
    }

    @Override
    @LogAnnotation(name = "下载数据")
    public void downloadPage(int page, int rows, HttpServletResponse response) {
        List<User> users = this.showByPage(page, rows);
        //这里需要使用反射动态的拿到数据进行赋值
        String[] titles = {"用户ID","用户姓名","用户密码","用户私盐","用户头像","用户法名","用户性别","用户所在省份","用户所在城市","用户签名","用户联系方式","用户状态","用户注册日期","用户上师ID"};
        /*创建工作薄对象*/
        Workbook workbook = new HSSFWorkbook();
        /*创建工作表对象*/
        Sheet sheet = workbook.createSheet("user");
        /* 第一个参数 第几列  第二个参数  列宽*/
        sheet.setColumnWidth(12, 26 * 256);
        /*创建行   参数：行下标*/
        Row row = sheet.createRow(0);
        /*修改日期样式*/
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd天");
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
        /*创建单元格样式*/
        CellStyle cellStyle = workbook.createCellStyle();
        /*居中*/
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        /*修改字体*/
        Font font = workbook.createFont();
        font.setFontName("楷体");
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setItalic(true);
        cellStyle.setFont(font);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(titles[i]);
        }
        /*数据行  查询数据库*/
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(users.get(i).getId());
            row1.createCell(1).setCellValue(users.get(i).getName());
            row1.createCell(2).setCellValue(users.get(i).getPassword());
            row1.createCell(3).setCellValue(users.get(i).getSalt());
            row1.createCell(4).setCellValue(users.get(i).getPhotoImg());
            row1.createCell(5).setCellValue(users.get(i).getDharmaName());
            row1.createCell(6).setCellValue(users.get(i).isSex()?"男":"女");
            row1.createCell(7).setCellValue(users.get(i).getProvince());
            row1.createCell(8).setCellValue(users.get(i).getCity());
            row1.createCell(9).setCellValue(users.get(i).getSign());
            row1.createCell(10).setCellValue(users.get(i).getPhoneNum());
            row1.createCell(11).setCellValue(users.get(i).isStatus()?"男":"女");
            Cell cell = row1.createCell(12);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(users.get(i).getRegisterDate());
            row1.createCell(13).setCellValue(users.get(i).getGruru().getId());
        }
        String a = new java.util.Date().getTime()+ "userExcel.xls";
        String newName = null;
        try {
            newName = new String(a.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;filename=" + newName);
        response.setContentType("application/vnd.ms-excel");

        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @LogAnnotation( name ="下载了自定义数据")
    public void downloadSelf(String titles, String params, HttpServletResponse response) {
        List<User> users = userDao.queryAll();
        //获取操作的对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置一些样式：日期
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        HSSFSheet sheet = workbook.createSheet("user");
        //创建标题行,并对标题进行初始化
        HSSFRow row = sheet.createRow(0);
        String[] tit = titles.split(",");
        for(int i=0;i<tit.length;i++){
            row.createCell(i).setCellValue(tit[i]);
        }
        //进行数据行的设置
        /*数据行*/
        String[] fileds = params.split(",");
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            User user = users.get(i);
            /*获取类对象*/
            Class<? extends User> userClass = user.getClass();
            for (int j = 0; j < fileds.length; j++) {
                Cell cell = row1.createCell(j);
                /*获得方法名*/
                String methodName;
                if(fileds[j].equals("sex")||fileds[j].equals("status")){
                    methodName = "is" + fileds[j].substring(0, 1).toUpperCase() + fileds[j].substring(1);
                }else{
                    methodName = "get" + fileds[j].substring(0, 1).toUpperCase() + fileds[j].substring(1);
                }

                /*获取调用的方法对象*/
                try {
                    Method method = userClass.getMethod(methodName, null);
                    Object invoke = method.invoke(user, null);
                    if (invoke instanceof Date) {
                        sheet.setColumnWidth(j,21*256);
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue((Date) invoke);
                    } else if(invoke instanceof Boolean) {
                        if((Boolean) invoke){
                            cell.setCellValue("Y");
                        }else{
                            cell.setCellValue("N");
                        }
                    }else if(invoke instanceof String) {
                        cell.setCellValue((String) invoke);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                /*创建单元格并且填充内容*/
            }
        }
        String a = new Date().getTime() + "userExcel.xls";
        String newName = null;
        try {
            newName = new String(a.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("content-disposition", "attachment;filename=" + newName);
        response.setContentType("application/vnd.ms-excel");

        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Integer count() {
        int count = userDao.count();
        return count;
    }
}
