package com.taw.pub.user.request;

import com.hawk.utility.check.CheckNull;

public class LogoutParam {
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 登陆token
	 */
	@CheckNull
	private String token;

}
