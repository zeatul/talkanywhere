package com.taw.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.user.request.SendAuthCodeParam;

public class SMSControllerTest extends AbstractControllerTest{
	
	public SMSControllerTest() throws Exception {
		super();
	}


	@Test
	public void testGet() throws Exception{
		String path = contextPath + "/user/hello.do";
		String result = httpClientHelper.get(path, null);
		printResult(result);
	}
	
	
	
	public void testAuthCode(String mobile) throws Exception{
		/**
		 * taw-user-web/user/sms/auth_code.do
		 */
		String path = contextPath + "/user/sms/auth_code.do";
									 
		
		SendAuthCodeParam param = new SendAuthCodeParam();
		param.setMobile(mobile);
		
		String content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
	public String queryAuthCode(String mobile) throws Exception{
		/**
		 * taw-user-web/user/sms/query_auth_code.do
		 */
		String path = contextPath + "/user/sms/query_auth_code.do";
									 
		
		SendAuthCodeParam param = new SendAuthCodeParam();
		param.setMobile(mobile);
		
		String content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
		
//		{"code":"1","data":{"authCode":"9367"}}
		
		Map<String,Object > map = JsonTools.toObject(result, HashMap.class);
		
		return ((Map<?,?>)map.get("data")).get("authCode").toString();
	}
	


}
