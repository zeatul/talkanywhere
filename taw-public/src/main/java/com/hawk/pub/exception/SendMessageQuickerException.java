package com.hawk.pub.exception;

import com.hawk.exception.BasicException;

public class SendMessageQuickerException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6744027509413676485L;

	public SendMessageQuickerException() {
		super("-102","短消息一分钟内只能发送一次");
	}

}
