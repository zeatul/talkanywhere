package com.taw.pub.picture.request;

import com.hawk.utility.check.CheckNull;

public class PicturePathParam {
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@CheckNull
	private String uuid;

}
