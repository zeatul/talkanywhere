package com.hawk.pub.sms;

public class SMSConfigure {
	
	
	public boolean isSendSms() {
		return sendSms;
	}

	public void setSendSms(boolean sendSms) {
		this.sendSms = sendSms;
	}

	/**
	 * 是否发送短信
	 */
	private boolean sendSms = true;

}
