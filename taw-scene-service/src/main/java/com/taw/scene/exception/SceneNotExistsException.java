package com.taw.scene.exception;

import com.hawk.exception.BasicException;

public class SceneNotExistsException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5431758744673654087L;

	public SceneNotExistsException() {
		super("-10001","场景不存在");
	}

}
