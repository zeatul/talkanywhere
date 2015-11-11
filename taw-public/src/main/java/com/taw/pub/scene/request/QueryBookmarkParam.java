package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class QueryBookmarkParam {
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	/**
	 * userId
	 */
	@CheckNull
	private Long userId;

}
