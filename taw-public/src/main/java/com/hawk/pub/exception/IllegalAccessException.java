package com.hawk.pub.exception;

import com.hawk.exception.BasicException;

public class IllegalAccessException extends BasicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4267583883614955009L;

	public IllegalAccessException() {
		super("-108","Unauthorized Operation");
	}

}
