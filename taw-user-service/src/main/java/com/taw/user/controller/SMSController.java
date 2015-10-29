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

import com.hawk.pub.sms.SMSService;
import com.hawk.pub.web.RequestHandler;
import com.hawk.pub.web.ResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.request.SendAuthCodeParam;

@Controller
public class SMSController {
	
	@Autowired
	@Qualifier("taw_user_service_sms_service")
	private SMSService smsService;
	
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
	@RequestMapping(value = "/user/sms/auth_code.do", method = RequestMethod.POST)
	public void authCode(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SendAuthCodeParam param = RequestHandler.handle(request, SendAuthCodeParam.class);
		CheckTools.check(param);
		String mobile = param.getMobile();
		/**
		 * 一分钟内不重发
		 */
		if (redisClient.exists(mobile)){
			throw new Exception("短消息一分钟内只能发送一次");
		}
		
		String authCode = StringTools.randomNumberString(4);
		String msg = "autoCode = " + authCode;
		smsService.SendMessage(param.getMobile(), msg);
		
		redisClient.set(mobile, authCode, 60,false);
		
		ResponseHandler.handle(response,SuccessResponse.SUCCESS_RESPONSE);
	}

}