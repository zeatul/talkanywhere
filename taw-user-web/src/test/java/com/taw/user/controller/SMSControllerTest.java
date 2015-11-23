package com.taw.user.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.user.request.SendAuthCodeParam;

public class SMSControllerTest extends AbstractControllerTest{
	
	public SMSControllerTest() throws Exception {
		super();
	}


	
	
	@Test
	public void testAuthCode() throws Exception{
		/**
		 * taw-user-web/user/sms/auth_code.do
		 */
		String path = contextPath + "/user/sms/auth_code.do";
									 
		
		SendAuthCodeParam param = new SendAuthCodeParam();
		param.setMobile("13811998761");
		
		String content = JsonTools.toJsonString(param);
		
		System.out.println(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		System.out.println(result);
	}

}
