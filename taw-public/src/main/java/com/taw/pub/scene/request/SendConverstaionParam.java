package com.taw.pub.scene.request;

import java.util.Date;

import com.hawk.utility.check.CheckNull;

public class SendConverstaionParam {
	
	
	
	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public Long getPostUserId() {
		return postUserId;
	}

	public void setPostUserId(Long postUserId) {
		this.postUserId = postUserId;
	}

	public ComplexMessage getMessage() {
		return message;
	}

	public void setMessage(ComplexMessage message) {
		this.message = message;
	}

	public Long getrPostId() {
		return rPostId;
	}

	public void setrPostId(Long rPostId) {
		this.rPostId = rPostId;
	}

	/**
	 * 场景主键
	 */
	@CheckNull
	private Long sceneId;
	
	/**
	 * 发言者ID
	 */
	@CheckNull
	private Long postUserId;
	
	/**
	 * 发言内容	
	 */
	@CheckNull
	private ComplexMessage message;
	
	/**
	 * 被回复的发言ID
	 */
	private Long rPostId;
		
	
	
	

}
