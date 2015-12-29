package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class EnterSceneParam {
	
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

	/**
	 * 用户ID
	 */
	@CheckNull
	private Long userId;
	
	/**
	 * 登录token
	 */
	@CheckNull
	private String token;
	
	/**
	 * 场景ID
	 */
	@CheckNull
	private Long sceneId;
	
	

}
