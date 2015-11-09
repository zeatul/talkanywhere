package com.hawk.pub.sms;

import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckMaxLength;
import com.hawk.utility.check.CheckNull;
import com.hawk.utility.check.CheckRegex;

public class SendMessageParam {
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 手机号
	 */
	@CheckNull
	@CheckMaxLength(max = 20)
	@CheckRegex(pattern="[0-9]{1,20}")
	private String mobile;
	
	/**
	 * 消息内容
	 */
	@CheckNull
	@CheckMaxLength(max = 250)
	private String message;
	
	
	/**
	 * 消息类型
	 */
	@CheckNull
	@CheckEnum(parser=EnumMessageKind.class)
	private String kind;

}
