package com.taw.scene.exception;

import com.hawk.exception.BasicException;

public class FootPrintDetailNotExistsException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -483444636303517184L;

	public FootPrintDetailNotExistsException() {
		super("-10003","用户进入场景的明细记录不存在");
	}

}
