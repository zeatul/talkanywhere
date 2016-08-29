package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class QuerySceneByNameParam {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@CheckNull
	private String name;
	
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

}
