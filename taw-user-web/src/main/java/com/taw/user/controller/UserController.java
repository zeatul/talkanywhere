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

import com.hawk.pub.exception.IncorrectAuthCodeException;
import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.enums.EnumLoginKind;
import com.taw.pub.user.request.CreateUserRequestParam;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.request.RestPasswordRequestParam;
import com.taw.pub.user.request.UpdatePasswordParam;
import com.taw.pub.user.response.LoginResp;
import com.taw.pub.user.response.UserResp;
import com.taw.user.auth.AuthThreadLocal;
import com.taw.user.domain.UserDomain;
import com.taw.user.service.LoginService;
import com.taw.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
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
	@RequestMapping(value = "/user/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/user/hello.do");
		return "success";
	}
	
	private void checkAuthCode(String mobile, String authCode) throws Exception{
		String cachedAuthCode = redisClient.get(mobile);
		if (cachedAuthCode == null || !cachedAuthCode.equals(authCode))
			throw new IncorrectAuthCodeException();
	}
	
	/**
	 * 查询用户信息
	 * @param locale
	 * @param model
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping(value = "/user/info.do", method = RequestMethod.POST)
	public void info(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserDomain userDomain = userService.loadUser(AuthThreadLocal.getUserId(), true);
		
		//返回信息
		UserResp userResp = new UserResp();
		userResp.setSex(userDomain.getSex());
		userResp.setUserId(userDomain.getId());
		HttpResponseHandler.handle(response, SuccessResponse.build(userResp));
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
		CreateUserRequestParam createUserRequestParam = HttpRequestHandler.handle(request, CreateUserRequestParam.class);
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
		HttpResponseHandler.handle(response, SuccessResponse.build(loginResp));
	}
	
	/**
	 * 更新密码，根据短信验证码做校验
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/pwd/reset.do", method = RequestMethod.POST)
	public void resetPassword(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		RestPasswordRequestParam updatePasswordRequestParam = HttpRequestHandler.handle(request, RestPasswordRequestParam.class);
		CheckTools.check(updatePasswordRequestParam);
		/**
		 * 校验验证码
		 */
		checkAuthCode(updatePasswordRequestParam.getParam().getMobile(),updatePasswordRequestParam.getAuthCode());
	
		/**
		 * 更新密码
		 */
		userService.updatePassword(updatePasswordRequestParam.getParam());
		/**
		 * 返回信息
		 */
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
	
	/**
	 * 更新密码，根据短信验证码做校验
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/user/pwd/update.do", method = RequestMethod.POST)
	public void updatePassword(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		UpdatePasswordParam updatePasswordRequestParam = HttpRequestHandler.handle(request, UpdatePasswordParam.class);
		CheckTools.check(updatePasswordRequestParam);
	
		/**
		 * 更新密码
		 */
		userService.updatePassword(updatePasswordRequestParam);
		/**
		 * 返回信息
		 */
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

}
