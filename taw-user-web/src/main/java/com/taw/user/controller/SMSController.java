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

import com.hawk.exception.BasicException;
import com.hawk.pub.exception.SendMessageQuickerException;
import com.hawk.pub.sms.EnumMessageKind;
import com.hawk.pub.sms.SMSService;
import com.hawk.pub.sms.SendMessageParam;
import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.request.SendAuthCodeParam;
import com.taw.pub.user.response.AuthCodeResp;
import com.taw.user.configure.UserServiceConfigure;

@Controller
public class SMSController {
	
	@Autowired
	@Qualifier("taw_user_service_sms_service")
	private SMSService smsService;
	
	@Autowired
	@Qualifier("taw_user_service_redis_client")
	private RedisClient redisClient;
	
	@Autowired
	private UserServiceConfigure userServiceConfigure;
	
	
	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/sms/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/user/sms/hello.do");
		return "success";
	}

	/**
	 * 发送手机验证码
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/user/sms/auth_code.do", method = {  RequestMethod.POST })
	public void authCode(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SendAuthCodeParam param = HttpRequestHandler.handle(request, SendAuthCodeParam.class);
		CheckTools.check(param);
		String mobile = param.getMobile();
		String timeControlKey = mobile + "-exist";
		/**
		 * 一分钟内不重发
		 */
		if (redisClient.exists(timeControlKey)){
			throw new SendMessageQuickerException();
		}
		
		String authCode = StringTools.randomNumberString(4);
		String msg = "autoCode = " + authCode;
		
		SendMessageParam sendMessageParam = new SendMessageParam();
		sendMessageParam.setMobile(param.getMobile());
		sendMessageParam.setMessage(msg);
		sendMessageParam.setKind(EnumMessageKind.AUTH_CODE.toString());
		smsService.SendMessage(sendMessageParam);
		
		redisClient.set(timeControlKey, authCode, 60,false); //控制时间
		redisClient.set(mobile, authCode, 60*30,false); //保留30分钟验证码
		
		HttpResponseHandler.handle(response,SuccessResponse.SUCCESS_RESPONSE);
	}

	/**
	 * 返回手机验证码 ，只能用于测试环境
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/user/sms/query_auth_code.do", method = RequestMethod.POST)
	public void queryAuthCode(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if (userServiceConfigure.isProd())
			throw new Exception("Not support product environment");
		SendAuthCodeParam param = HttpRequestHandler.handle(request, SendAuthCodeParam.class);
		CheckTools.check(param);
		String mobile = param.getMobile();
		
		String authCode = redisClient.get(mobile);
		if (authCode == null)
			throw new Exception("Found no auth code");
		
		AuthCodeResp authCodeResp = new AuthCodeResp();
		authCodeResp.setAuthCode(authCode);
		
		HttpResponseHandler.handle(response,SuccessResponse.build(authCodeResp));
	}
}
