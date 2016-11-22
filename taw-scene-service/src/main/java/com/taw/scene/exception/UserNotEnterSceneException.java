package com.taw.scene.exception;

import com.hawk.exception.BasicException;

public class UserNotEnterSceneException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431758744673654087L;

	public UserNotEnterSceneException() {
		super("-10005","用户未进入在场景");
	}

}
