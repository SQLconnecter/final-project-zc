package com.baizhi.controller;

import com.baizhi.util.CreateValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

@Controller
@RequestMapping("/code")
public class SecurityCodeController {
	@RequestMapping("/createcode.do")
	public void createCode(HttpServletResponse response,HttpSession session)throws Exception {
		//获取验证码
		CreateValidateCode createValidateCode = new CreateValidateCode();
		String code=createValidateCode.getCode();
		session.setAttribute("code", code);
		//生成验证码图片
		BufferedImage image = createValidateCode.getBuffImg();
		OutputStream outputStream = response.getOutputStream();
		createValidateCode.write(outputStream);
	}
}
