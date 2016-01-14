package com.taw.picture.exception;

import com.hawk.exception.BasicException;

public class PictureNotFoundException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7811179253688300398L;

	public PictureNotFoundException() {
		super("-10001","图片未找到");
	}

}
