package com.taw.user.exception;

import com.hawk.exception.BasicException;

public class UserExistsException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4090220889842629389L;

	public UserExistsException(Throwable cause) {
		super("-10001","用户已注册");
		this.initCause(cause);
	}
	
	

}
