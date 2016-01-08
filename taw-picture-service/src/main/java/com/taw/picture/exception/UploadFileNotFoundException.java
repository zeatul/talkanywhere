package com.taw.picture.exception;

import com.hawk.exception.BasicException;

public class UploadFileNotFoundException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6122385124389984730L;

	public UploadFileNotFoundException(String uuid) {
		super("-107","未找到uuid="+uuid+"的文件");
	}

}
