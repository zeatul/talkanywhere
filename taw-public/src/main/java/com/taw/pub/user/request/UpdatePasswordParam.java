package com.taw.pub.user.request;

import com.hawk.utility.check.CheckNull;

public class UpdatePasswordParam {
	
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
	@CheckNull(allow=false)
	private String mobile;
	@CheckNull(allow=false)
	private String newPwd;

}
