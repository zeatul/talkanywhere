package com.taw.user.exception;

import com.hawk.exception.BasicException;

public class FailedDecryptTokenException extends BasicException{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2554389964506032287L;

	public FailedDecryptTokenException() {
		super("-104","Failed decrypt tikcet");
	}

}
