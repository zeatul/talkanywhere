package com.taw.user.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.exception.SendMessageQuickerException;
import com.hawk.pub.sms.EnumMessageKind;
import com.hawk.pub.sms.SMSService;
import com.hawk.pub.sms.SendMessageParam;
import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.httpclient.HttpClientHelper;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.request.SendAuthCodeParam;
import com.taw.pub.user.response.AuthCodeResp;
import com.taw.user.configure.UserServiceConfigure;

@Controller
public class SMSController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("taw_user_service_sms_service")
	private SMSService smsService;
	
	@Autowired
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
		if (redisClient.exist(timeControlKey)){
			throw new SendMessageQuickerException();
		}
		
		String authCode = StringTools.randomNumberString(4);
		String msg = authCode;
		
		SendMessageParam sendMessageParam = new SendMessageParam();
		sendMessageParam.setMobile(param.getMobile());
		sendMessageParam.setMessage(msg);
		sendMessageParam.setKind(EnumMessageKind.AUTH_CODE.toString());
		smsService.SendMessage(sendMessageParam);
		
		redisClient.set(timeControlKey, authCode, 60); //控制时间
		redisClient.set(mobile, authCode, 60*30); //保留30分钟验证码
		
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
	@RequestMapping(value = "/user/sms/query_auth_code.do", method = {RequestMethod.GET,RequestMethod.POST})
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
	
	@RequestMapping(value = "/user/sms/test_auth_code.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String testPostAuthCode(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpClientHelper httpClientHelper = new HttpClientHelper();
		httpClientHelper.setHostname("127.0.0.1");	//测试环境
		httpClientHelper.setPort(8080);
		httpClientHelper.setScheme("http");
		
		String path = "/taw-user-web/user/sms/auth_code.do";
		SendAuthCodeParam param = new SendAuthCodeParam();
		param.setMobile("18909082489");
		
		String content = JsonTools.toJsonString(param);
		
		logger.info("content={}",content);
		
		String result = httpClientHelper.post(path, content, null);
		
		logger.info("result={}",result);
		
		model.addAttribute("msg", result);
		return "success";
	}
}
