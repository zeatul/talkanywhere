package com.taw.pub.user.request;

import com.hawk.utility.check.CheckNull;

public class ResetPasswordParam {
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	@CheckNull
	private String mobile;
	@CheckNull
	private String newPwd;

}
