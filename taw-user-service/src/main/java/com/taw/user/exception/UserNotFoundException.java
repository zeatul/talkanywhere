package com.taw.user.exception;

import com.hawk.exception.BasicException;

public class UserNotFoundException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7136892353429875345L;

	public UserNotFoundException() {
		super("-10002","user not found");
		
	}

}
