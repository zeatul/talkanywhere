package com.taw.pub.picture.request;

import com.hawk.utility.check.CheckNull;

public class SearchGlobalHotPictureParam {
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	@CheckNull
	private Integer offset;
	@CheckNull
	private Integer limit;
}
