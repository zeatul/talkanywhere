package com.taw.pub.user.request;

import com.hawk.utility.check.CheckMaxLength;
import com.hawk.utility.check.CheckNull;
import com.hawk.utility.check.CheckRegex;

public class SendAuthCodeParam {
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@CheckNull
	@CheckMaxLength(max = 20)
	@CheckRegex(pattern="[0-9]{1,20}")
	private String mobile;

}
