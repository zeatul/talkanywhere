package com.taw.user.exception;

import com.hawk.exception.BasicException;

public class UnMatchUserPasswordException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 908552455159748221L;

	public UnMatchUserPasswordException() {
		super("-10001","user account doesn't match password");
	}

}
