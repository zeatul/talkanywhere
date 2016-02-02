package com.taw.user.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.user.enums.EnumChannel;
import com.taw.pub.user.enums.EnumDeviceKind;
import com.taw.pub.user.enums.EnumLoginKind;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.request.SendAuthCodeParam;

public class LoginControllerTest extends AbstractControllerTest {

	public LoginControllerTest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	 @Test
	public void testLogin() throws Exception {
		/**
		 * taw-user-web/user/login.do
		 */
		String path = contextPath + "/user/login.do";

		LoginParam param = new LoginParam();
		param.setBrand("brand");
		param.setDeviceKind(EnumDeviceKind.ANDROID.toString());
		param.setImei("hwll");
		param.setIp("127.0.0.1");
		param.setMobile("13311658157");
		param.setOsVersion("1111.11");
		param.setPassword("123456");
		param.setKind(EnumLoginKind.PERMANENT.toString());

		String content = JsonTools.toJsonString(param);

		printSend(content);

		String result = httpClientHelper.post(path, content, null);

		printResult(result);
	}

//	@Test
	public void testLogout() throws Exception {
		String path = contextPath + "/user/logout.do";
		
		String result = httpClientHelper.post(path, "", genAuthMap());

		printResult(result);
	}

}
