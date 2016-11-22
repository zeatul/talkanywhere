package com.taw.scene.exception;

import com.hawk.exception.BasicException;

public class UserNotOnLineSceneException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431758744673654087L;

	public UserNotOnLineSceneException() {
		super("-10006","用户物理不在场景");
	}

}
