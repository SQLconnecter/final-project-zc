package com.baizhi.util;

import com.baizhi.entity.Gruru;
import com.baizhi.entity.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XLSUtil {
    public static List<User> read(MultipartFile file){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheet("model");
            List<User> users = new ArrayList<>();
            //不需要拿去第一行标题，直接对应模板开始读取数据即可
            for(int i=1;i<sheet.getLastRowNum()+1;i++){
                User user = new User();
                HSSFRow row = sheet.getRow(i);
                //因为预先都知道每一行都是什么数据类型所以直接进行取数据就可以了，不用进行类型的判断
                user.setId(row.getCell(0).getStringCellValue());
                user.setName(row.getCell(1).getStringCellValue());
                user.setPassword(row.getCell(2).getStringCellValue());
                user.setSalt(row.getCell(3).getStringCellValue());
                user.setPhotoImg(row.getCell(4).getStringCellValue());
                user.setDharmaName(row.getCell(5).getStringCellValue());
                user.setSex(row.getCell(6).getBooleanCellValue());
                user.setProvince(row.getCell(7).getStringCellValue());
                user.setCity(row.getCell(8).getStringCellValue());
                user.setSign(row.getCell(9).getStringCellValue());
                user.setPhoneNum(row.getCell(10).getStringCellValue());
                user.setStatus(row.getCell(11).getBooleanCellValue());
                user.setRegisterDate(row.getCell(12).getDateCellValue());
                Gruru gruru = new Gruru();
                gruru.setId(row.getCell(13).getStringCellValue());
                user.setGruru(gruru);
                users.add(user);
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
