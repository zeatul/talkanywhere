package com.taw.user.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.web.RequestHandler;
import com.hawk.pub.web.ResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.request.LogoutParam;
import com.taw.pub.user.response.LoginResp;
import com.taw.user.service.LoginService;
import com.taw.user.service.LoginService.LoginInfo;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	@Qualifier("taw_user_service_redis_client")
	private RedisClient redisClient;
	
	@RequestMapping(value = "/user/login.do", method = RequestMethod.POST)
	public void login(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		LoginParam loginParam = RequestHandler.handle(request, LoginParam.class);
		/**
		 * 登陆
		 */
		LoginInfo loginInfo = loginService.login(loginParam);
		/**
		 * 加入缓存
		 */
		redisClient.set(loginInfo.getToken(), loginInfo.getUserDomain().getId().toString(), false);
		/**
		 * 返回结果
		 */
		LoginResp loginResp = new LoginResp();
		loginResp.setToken(loginInfo.getToken());
		ResponseHandler.handle(response, SuccessResponse.build(loginResp));
	}
	
	@RequestMapping(value = "/user/logout.do", method = RequestMethod.POST)
	public void logout(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String token = "从登陆信息解码出来";
		/**
		 * 删数据库
		 */
		LogoutParam logoutParam = new LogoutParam();		
		logoutParam.setToken(token);
		loginService.logout(logoutParam);
		/**
		 * 清缓存
		 */
		redisClient.delete(token, false);
		/**
		 * 返回结果
		 */
		ResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

}
