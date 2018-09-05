package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login.do")
    public String login(String username, String password, String enCode, HttpSession session){
        String code = (String) session.getAttribute("code");
        if(code.equals(enCode.toUpperCase())){
            Admin login = adminService.login(username, password);
            if(login==null){
                return "redirect:/login.jsp";
            }else{
                session.setAttribute("login",login);
                return "/main/main";
            }
        }else{
            return "redirect:/login.jsp";
        }

    }
}
