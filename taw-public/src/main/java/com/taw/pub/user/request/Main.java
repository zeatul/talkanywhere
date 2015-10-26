package com.taw.pub.user.request;

import com.hawk.utility.check.CheckTools;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println("start");
		CreateUserParam param = new CreateUserParam();
		param.setMobile("1222");
		CheckTools.check(param);
	}

}
