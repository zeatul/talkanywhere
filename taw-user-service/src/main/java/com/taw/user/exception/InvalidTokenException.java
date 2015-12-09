package com.taw.user.exception;

import com.hawk.exception.BasicException;

public class InvalidTokenException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6291476759753691880L;

	public InvalidTokenException() {
		super("-101","invalid token");
	}

}
