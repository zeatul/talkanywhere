package com.hawk.utility.check;

import com.hawk.exception.BasicException;

public class CheckParamFailedException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 399006023127159179L;

	public CheckParamFailedException(String errmsg) {
		super("-100",errmsg);
		
	}

	

}
