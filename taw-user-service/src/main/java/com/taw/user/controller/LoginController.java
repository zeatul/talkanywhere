package com.taw.user.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.web.RequestHandler;
import com.hawk.pub.web.ResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.taw.pub.user.request.LoginParam;
import com.taw.user.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
	public void login(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		LoginParam loginParam = RequestHandler.handle(request, LoginParam.class);
		String token = loginService.login(loginParam);
		SuccessResponse result = SuccessResponse.build(token);
		ResponseHandler.handle(response, result);
	}

}
