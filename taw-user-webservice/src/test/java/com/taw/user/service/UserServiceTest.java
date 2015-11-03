package com.taw.user.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.taw.pub.user.enums.EnumChannel;
import com.taw.pub.user.enums.EnumDeviceKind;
import com.taw.pub.user.request.CreateUserParam;
import com.taw.user.service.UserService;

@ContextConfiguration(locations={
		"classpath*:com/hawk/pub/spring/applicationContext-pub-*.xml",
		"classpath*:com/taw/user/spring/applicationContext-user-service-*.xml"
		})
public class UserServiceTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testCreateUser() throws Exception{
		CreateUserParam param = new CreateUserParam();
		param.setMobile("13311658157");
		param.setPassword("password");
		param.setChannel(EnumChannel.REGISTERED.toString());
		param.setBrand("Samsung");
		param.setDeviceKind(EnumDeviceKind.ANDROID.toString());
		param.setDeviceModel("A11A11");
		param.setImei("11111111");
		param.setIp("171.168.1.1");
		param.setOsVersion("5.0");
		
		userService.createUser(param);
	}

}
