package com.hawk.pub.exception;

import com.hawk.exception.BasicException;

public class IncorrectAuthCodeException extends BasicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7517301094166587810L;

	public IncorrectAuthCodeException() {
		super("-101","auth code is incorrect!");
	}

}
