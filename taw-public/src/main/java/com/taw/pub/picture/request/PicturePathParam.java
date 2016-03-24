package com.taw.pub.picture.request;

import java.util.List;

import com.hawk.utility.check.CheckNull;
import com.hawk.utility.check.CheckSize;

public class PicturePathParam {
	
	

	

	public List<String> getUuids() {
		return uuids;
	}

	public void setUuids(List<String> uuids) {
		this.uuids = uuids;
	}

	@CheckNull
	@CheckSize
	private List<String> uuids;

}
