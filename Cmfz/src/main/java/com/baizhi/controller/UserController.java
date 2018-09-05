package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import com.baizhi.service.UserDTOService;
import com.baizhi.service.UserService;
import com.baizhi.util.IPUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOService userDTOService;
    @RequestMapping("/model.do")
    //进行模板的下载
    public void download(HttpSession session , HttpServletResponse response,HttpServletRequest request){
        String webPath = session.getServletContext().getRealPath("/");
        String realPath = webPath+"model/model.xls";
        //设置相应的头部为下载模式
        //content

        String ip = IPUtils.getIPAddress(request);
        response.setHeader("content-disposition","attachment;filename=model.xls");
        try {
            IOUtils.copy(new FileInputStream(realPath),response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //进行数据的导入
    @RequestMapping("/import.do")
    @ResponseBody
    public String dataImport(MultipartFile file){
        //不要把文件上传，只需要读取文件信息只有=之后调用业务层方法即可
        userService.importDB(file);
        return "success";
    }
    //进行数据的分页导出
    @RequestMapping("/export.do")
    public void exportDate(int page, int rows, HttpServletResponse response){
        userService.downloadPage( page, rows, response);
    }
    //进行全部用户信息的自定义导出
    @RequestMapping("/exportself.do")
    public void  exportDateSelf(String titles,String params,HttpServletResponse response){
        //业务层方法
        userService.downloadSelf(titles,params,response);
    }
    //用户信息的分页展示
    @RequestMapping("/show.do")
    @ResponseBody
    public Map<String,Object> show(Integer page,Integer rows){
        Map<String,Object> map = new HashMap<>();
        Integer total = userService.count();
        List<User> users = userService.showByPage(page, rows);
        map.put("total",total);
        map.put("rows",users);
        return map;
    }
    //编辑用户信息
    @RequestMapping("/edit.do")
    @ResponseBody
    public User edit(String id,boolean status){
        User user = userService.change(id, status);
        return user;
    }
    //展示最近一周，最近两周，最近三周，最近一月用户注册的数量//做数据的实时变化
    //前台一定是通过使用ajax发送的请求的
    @RequestMapping("/showusers.do")
    @ResponseBody
    public Map<String,Object> showUsers(){
        Map<String,Object> map = new HashMap<>();
        List<UserDTO> userDTOS = userDTOService.showDto(3);
        List<String> names=userDTOS.stream().map(t->t.getName()).collect(Collectors.toList());
        List<Integer> values = userDTOS.stream().map(t -> t.getValue()).collect(Collectors.toList());
        map.put("names",names);
        map.put("values",values);
        return map;
    }
    //展示不同省份的男女用户
    @RequestMapping("/showapp.do")
    @ResponseBody
    public Map<String,Object> showApp(){
        Map<String,Object> map = new HashMap<>();
        List<UserDTO> manDTOS = userDTOService.showMan();
        List<UserDTO> womanDTOS = userDTOService.showWoman();
        map.put("man",manDTOS);
        map.put("woman",womanDTOS);
        return map;
    }
    //
}
