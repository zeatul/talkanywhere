package com.taw.pub.user.request;

import com.hawk.utility.check.CheckNull;

public class CreateUserRequestParam {
	
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public CreateUserParam getParam() {
		return param;
	}

	public void setParam(CreateUserParam param) {
		this.param = param;
	}

	@CheckNull(allow=false)
	private String authCode;
	
	@CheckNull(allow=false)
	private CreateUserParam param;

}
