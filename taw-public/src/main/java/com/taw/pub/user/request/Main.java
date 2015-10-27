package com.taw.pub.user.request;

import com.hawk.utility.check.CheckTools;
import com.taw.pub.user.enums.EnumChannel;
import com.taw.pub.user.enums.EnumDeviceKind;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("start");
		CreateUserParam param = new CreateUserParam();
		param.setMobile("1222");
		param.setPassword("password");
		param.setChannel(EnumChannel.REGISTERED.toString());
		param.setBrand("Samsung");
		param.setDeviceKind(EnumDeviceKind.ANDROID.toString());
		param.setDeviceModel("A11A11");
		param.setImei("11111111");
		param.setIp("171.168.1.1");
		param.setOsVersion("5.0");
		CheckTools.check(param);
	}

}
