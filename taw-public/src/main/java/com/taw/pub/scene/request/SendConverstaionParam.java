package com.taw.pub.scene.request;

import java.util.List;

import com.hawk.utility.check.CheckNull;

public class SendConverstaionParam {
	


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<String> getPics() {
		return pics;
	}


	public void setPics(List<String> pics) {
		this.pics = pics;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
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


	public String getPostNickname() {
		return postNickname;
	}


	public void setPostNickname(String postNickname) {
		this.postNickname = postNickname;
	}


	


	public Long getRePostId() {
		return rePostId;
	}


	public void setRePostId(Long rePostId) {
		this.rePostId = rePostId;
	}


	public Long getRePostUserId() {
		return rePostUserId;
	}


	public void setRePostUserId(Long rePostUserId) {
		this.rePostUserId = rePostUserId;
	}


	public String getRePostNickname() {
		return rePostNickname;
	}


	public void setRePostNickname(String rePostNickname) {
		this.rePostNickname = rePostNickname;
	}


	public Long getPostUserFpdId() {
		return postUserFpdId;
	}


	public void setPostUserFpdId(Long postUserFpdId) {
		this.postUserFpdId = postUserFpdId;
	}


	public Long getRePostUserFpdId() {
		return rePostUserFpdId;
	}


	public void setRePostUserFpdId(Long rePostUserFpdId) {
		this.rePostUserFpdId = rePostUserFpdId;
	}

	
	private String token;

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
	 * 发言者昵称
	 */
	@CheckNull
	private String postNickname;
	
	
	private String message;
	
	/**
	 * 被回复的发言ID
	 */
	private Long rePostId;
	
	/**
	 * 被回复的发言者ID
	 */
	private Long rePostUserId;
	
	
	/**
	 * 被回复的发言者昵称
	 */
	private String rePostNickname;
		
	/**
	 * 发言者在场景唯一标识
	 */
	@CheckNull
	private Long postUserFpdId;
	
	
	/**
	 * 被回复的发言者在场景唯一标识
	 */
	private Long rePostUserFpdId;
	

	/**
	 * 发送的图片集合
	 */
	private List<String> pics;
}
