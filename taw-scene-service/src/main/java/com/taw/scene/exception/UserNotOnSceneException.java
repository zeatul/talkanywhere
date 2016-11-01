package com.taw.scene.exception;

import com.hawk.exception.BasicException;

public class UserNotOnSceneException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431758744673654087L;

	public UserNotOnSceneException() {
		super("-10005","用户不在场景");
	}

}
