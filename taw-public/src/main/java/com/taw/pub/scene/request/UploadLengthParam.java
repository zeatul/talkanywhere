package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class UploadLengthParam {
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	

	@CheckNull
	/**
	 * yyyyMMddHHmmss + 32位uuid+ .suffix
	 */
	private String uuid;
	
	

}
