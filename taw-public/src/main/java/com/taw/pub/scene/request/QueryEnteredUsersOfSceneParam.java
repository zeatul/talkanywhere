package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class QueryEnteredUsersOfSceneParam {
	
	
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

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	@CheckNull
	private Long sceneId;
	
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
