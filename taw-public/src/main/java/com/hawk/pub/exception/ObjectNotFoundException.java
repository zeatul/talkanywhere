package com.hawk.pub.exception;

import com.hawk.exception.BasicException;

public class ObjectNotFoundException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4324780593408626748L;

	public ObjectNotFoundException(Class<?> clazz) {
		super("-108","Found no record！");
	}

}
