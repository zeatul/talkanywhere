package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class LeaveSceneParam {
//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public Long getFpdId() {
		return fpdId;
	}

	public void setFpdId(Long fpdId) {
		this.fpdId = fpdId;
	}

	/**
	 * 场景ID
	 */
	@CheckNull
	private Long sceneId;
	
	/**
	 * 用户处于场景的唯一标识ID
	 */
	@CheckNull
	private Long fpdId;
	
	/**
	 * 用户ID
	 */
	@CheckNull
	private Long userId;
	
	private String token;
}
