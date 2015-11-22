package com.taw.pub.user.request;

import com.hawk.utility.check.CheckNull;

public class RestPasswordRequestParam {
	
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public ResetPasswordParam getParam() {
		return param;
	}

	public void setParam(ResetPasswordParam param) {
		this.param = param;
	}

	@CheckNull
	private String authCode;
	
	@CheckNull
	private ResetPasswordParam param;

}
