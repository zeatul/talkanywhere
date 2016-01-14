package com.taw.pub.picture.request;

import com.hawk.utility.check.CheckNull;

public class PictureInfoParam {
	
	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	/**
	 * 图片Id
	 */
	@CheckNull
	private Long picId;

}
