package com.taw.scene.exception;

import com.hawk.exception.BasicException;

public class FootPrintNotExistsException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5000014775326487488L;

	public FootPrintNotExistsException() {
		super("-10002","场景足迹记录不存在");
	}

}
