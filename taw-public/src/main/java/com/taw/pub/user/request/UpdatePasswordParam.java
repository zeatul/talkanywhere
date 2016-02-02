package com.taw.pub.user.request;

import com.hawk.utility.check.CheckNull;

public class UpdatePasswordParam {
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	private String pwd;
	
	@CheckNull
	private String newPwd;

}
