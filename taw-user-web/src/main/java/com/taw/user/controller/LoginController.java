package com.taw.user.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.request.LogoutParam;
import com.taw.pub.user.response.LoginResp;
import com.taw.user.auth.AuthThreadLocal;
import com.taw.user.service.LoginService;
import com.taw.user.service.LoginService.LoginInfo;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private RedisClient redisClient;
	
	
	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/login/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/user/contact/hello.do");
		return "success";
	}
	
	
	@RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
	public void login(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		LoginParam loginParam = HttpRequestHandler.handle(request, LoginParam.class);
		/**
		 * 登陆
		 */
		LoginInfo loginInfo = loginService.login(loginParam);
		
		/**
		 * 返回结果
		 */
		LoginResp loginResp = new LoginResp();
		loginResp.setToken(loginInfo.getToken());
		HttpResponseHandler.handle(response, SuccessResponse.build(loginResp));
	}
	
	@RequestMapping(value = "/user/logout.do", method = RequestMethod.POST)
	public void logout(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String token = AuthThreadLocal.getToken();
		
		LogoutParam logoutParam = new LogoutParam();		
		logoutParam.setToken(token);
		loginService.logout(logoutParam);
		
		/**
		 * 返回结果
		 */
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

}
