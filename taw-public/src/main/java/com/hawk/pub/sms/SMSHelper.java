package com.hawk.pub.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSHelper {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void SendMessage(String mobile,String msg){
		logger.info("Success to send msg");
	}
	

}
