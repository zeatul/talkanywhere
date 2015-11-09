package com.taw.pub.user.request;

import com.hawk.utility.check.CheckNull;

public class UpdatePasswordRequestParam {
	
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public UpdatePasswordParam getParam() {
		return param;
	}

	public void setParam(UpdatePasswordParam param) {
		this.param = param;
	}

	@CheckNull
	private String authCode;
	
	@CheckNull
	private UpdatePasswordParam param;

}
