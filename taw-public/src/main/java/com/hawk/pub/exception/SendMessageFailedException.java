package com.hawk.pub.exception;

import com.hawk.exception.BasicException;

public class SendMessageFailedException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6701086760483302573L;

	public SendMessageFailedException(String errmsg) {
		super("-103",errmsg);
	}

}
