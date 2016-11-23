package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class ChangePresentParam {
	

//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}

	

	public Long getInSceneId() {
		return inSceneId;
	}

	public void setInSceneId(Long inSceneId) {
		this.inSceneId = inSceneId;
	}

	public Long getOutSceneId() {
		return outSceneId;
	}

	public void setOutSceneId(Long outSceneId) {
		this.outSceneId = outSceneId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	

	/**
	 * 进入场景
	 */
	private Long inSceneId;
	
	/**
	 * 离开场景
	 */
	private Long outSceneId;
	
	@CheckNull
	private Long userId;
	
	
//	@CheckNull
//	private String token;

}
