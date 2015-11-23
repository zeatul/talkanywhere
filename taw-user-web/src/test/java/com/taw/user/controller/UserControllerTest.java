package com.taw.user.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.user.enums.EnumChannel;
import com.taw.pub.user.enums.EnumDeviceKind;
import com.taw.pub.user.request.CreateUserParam;
import com.taw.pub.user.request.CreateUserRequestParam;

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
		createUserParam.setMobile("13811998761");
		createUserParam.setOsVersion("1111.11");
		createUserParam.setPassword("123456");

		CreateUserRequestParam createUserRequestParam = new CreateUserRequestParam();
		createUserRequestParam.setParam(createUserParam);
		createUserRequestParam.setAuthCode("9735");

		String content = JsonTools.toJsonString(createUserRequestParam);
		
		System.out.println(content);

		String result = httpClientHelper.post(path, content, null);

		System.out.println(result);
	}

}
