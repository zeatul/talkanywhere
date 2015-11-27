package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;

public class SendConverstaionParam {
	
	
	
	public String getrPostNickname() {
		return rPostNickname;
	}

	public void setrPostNickname(String rPostNickname) {
		this.rPostNickname = rPostNickname;
	}

	public Long getFpdId() {
		return fpdId;
	}

	public void setFpdId(Long fpdId) {
		this.fpdId = fpdId;
	}

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
	
	
	/**
	 * 被回复的发言者昵称
	 */
	private String rPostNickname;
		
	/**
	 * 分配给用户在场景的代理Id ，每次用户进入场景时分配
	 */
	@CheckNull
	private Long fpdId;
	

}
