package com.taw.user.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.user.enums.EnumChannel;
import com.taw.pub.user.enums.EnumDeviceKind;
import com.taw.pub.user.enums.EnumSex;
import com.taw.pub.user.request.CreateUserParam;
import com.taw.pub.user.request.CreateUserRequestParam;
import com.taw.pub.user.request.ResetPasswordParam;
import com.taw.pub.user.request.RestPasswordRequestParam;

public class UserControllerTest extends AbstractControllerTest {

	public UserControllerTest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testCreateUser() throws Exception {
		/**
		 * taw-user-web/user/create.do
		 */
		String path = contextPath + "/user/create.do";

		CreateUserParam createUserParam = new CreateUserParam();
		createUserParam.setBrand("brand");
		createUserParam.setChannel(EnumChannel.REGISTERED.toString());
		createUserParam.setDeviceKind(EnumDeviceKind.ANDROID.toString());
		createUserParam.setImei("hwll");
		createUserParam.setIp("127.0.0.1");
		createUserParam.setMobile("13311658157");
		createUserParam.setOsVersion("1111.11");
		createUserParam.setPassword("123456");
		createUserParam.setSex(EnumSex.MALE.toString());

		CreateUserRequestParam createUserRequestParam = new CreateUserRequestParam();
		createUserRequestParam.setParam(createUserParam);
		
		SMSControllerTest smsControllerTest = new SMSControllerTest();
		smsControllerTest.testAuthCode(createUserParam.getMobile());
		String authCode = smsControllerTest.queryAuthCode(createUserParam.getMobile());
		System.out.println("authCode="+authCode);
		createUserRequestParam.setAuthCode(authCode);

		String content = JsonTools.toJsonString(createUserRequestParam);
		
		printSend(content);

		String result = httpClientHelper.post(path, content, null);

		printResult(result);
	}
	
//	@Test
	public void testRestPassowrd() throws Exception{
		String path = contextPath + "/user/reset.do";
		
		ResetPasswordParam resetPasswordParam = new ResetPasswordParam();
		resetPasswordParam.setMobile("13311658157");
		resetPasswordParam.setNewPwd("newpwd");
		
		RestPasswordRequestParam param = new RestPasswordRequestParam();
		param.setAuthCode("123456");
		param.setParam(resetPasswordParam);
		
		String content = JsonTools.toJsonString(param);
		
		printSend(content);

		String result = httpClientHelper.post(path, content, null);

		printResult(result);
	}

}
