package com.taw.scene.exception;

import com.hawk.exception.BasicException;

public class InvalidFootPrintDetailException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -483444636303517184L;

	public InvalidFootPrintDetailException() {
		super("-10004","fpdId已过期,用户已离开场景");
	}

}
