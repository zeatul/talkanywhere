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

import com.hawk.pub.web.RequestHandler;
import com.hawk.pub.web.ResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.enums.EnumLoginKind;
import com.taw.pub.user.request.CreateUserRequestParam;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.request.UpdatePasswordRequestParam;
import com.taw.pub.user.response.LoginResp;
import com.taw.user.service.LoginService;
import com.taw.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	@Qualifier("taw_user_service_redis_client")
	private RedisClient redisClient;

	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/user/hello.do");
		return "success";
	}
	
	private void checkAuthCode(String mobile, String authCode) throws Exception{
		String cachedAuthCode = redisClient.get(mobile);
		if (cachedAuthCode == null || !cachedAuthCode.equals(authCode))
			throw new Exception("auth code is error!");
	}

	/**
	 * 新建用户
	 * @param locale
	 * @param model
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping(value = "/user/create.do", method = RequestMethod.POST)
	public void create(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CreateUserRequestParam createUserRequestParam = RequestHandler.handle(request, CreateUserRequestParam.class);
		CheckTools.check(createUserRequestParam);
		
		String authCode = createUserRequestParam.getAuthCode();
		String mobile = createUserRequestParam.getParam().getMobile();
		checkAuthCode(mobile,authCode);
		
		userService.createUser(createUserRequestParam.getParam());
		
		//登录
		LoginParam loginParam = new LoginParam();
		DomainTools.copy(createUserRequestParam.getParam(), loginParam);
		loginParam.setKind(EnumLoginKind.PERMANENT.toString());
		String token = loginService.login(loginParam).getToken();
		
		//返回信息
		LoginResp loginResp = new LoginResp();
		loginResp.setToken(token);
		ResponseHandler.handle(response, SuccessResponse.build(token));
	}
	
	/**
	 * 更新密码，根据短信验证码做校验
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/upwd.do", method = RequestMethod.POST)
	public void upwd(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		UpdatePasswordRequestParam updatePasswordRequestParam = RequestHandler.handle(request, UpdatePasswordRequestParam.class);
		CheckTools.check(updatePasswordRequestParam);
		/**
		 * 校验验证码
		 */
		checkAuthCode(updatePasswordRequestParam.getParam().getMobile(),updatePasswordRequestParam.getAuthCode());
	
		/**
		 * 更新密码
		 */
		userService.updatePasswor(updatePasswordRequestParam.getParam());
		/**
		 * 返回信息
		 */
		ResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

}
