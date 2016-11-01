package com.taw.pub.picture.request;

import com.hawk.utility.check.CheckNull;

public class RemovePictureParam {
	
	

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@CheckNull
	private Long picId;
	
	@CheckNull
	private Long userId;

}
