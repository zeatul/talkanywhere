package com.taw.pub.user.request;

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

	private String authCode;
	
	private CreateUserParam param;

}
