package com.hawk.pub.exception;

import com.hawk.exception.BasicException;

public class UnauthorizedOperateException extends BasicException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4267583883614955009L;

	public UnauthorizedOperateException() {
		super("-108","Unauthorized Operation");
	}

}
