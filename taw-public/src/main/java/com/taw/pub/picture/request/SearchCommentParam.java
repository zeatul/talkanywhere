package com.taw.pub.picture.request;

import com.hawk.utility.check.CheckNull;

public class SearchCommentParam {
	
	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

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

	

	/**
	 * offset 分页参数
	 */
	@CheckNull
	private Integer offset;
	/**
	 * limit 分页参数
	 */
	@CheckNull
	private Integer limit;
	
	@CheckNull
	private Long picId;
	
	

}
