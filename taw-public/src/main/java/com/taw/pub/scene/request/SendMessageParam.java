	package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckMaxLength;
import com.hawk.utility.check.CheckNull;

public class SendMessageParam {
	
	public String getReceiverNickname() {
		return receiverNickname;
	}

	public void setReceiverNickname(String receiverNickname) {
		this.receiverNickname = receiverNickname;
	}

	public String getSenderNickname() {
		return senderNickname;
	}

	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}

	public Long getFpdId() {
		return fpdId;
	}

	public void setFpdId(Long fpdId) {
		this.fpdId = fpdId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	/**
	 * 接收者ID
	 */
	@CheckNull
	private Long receiverId;
	
	/**
	 * 场景ID
	 */
	@CheckNull
	private Long sceneId;
	
	/**
	 * 内容
	 */
	@CheckNull
	private String content;
	
	/**
	 * 发送者ID
	 */
	@CheckNull
	private Long senderId;
	
	
	/**
	 * 分配给用户在场景的代理Id ，每次用户进入场景时分配
	 */
	@CheckNull
	private Long fpdId;
	
	/**
	 * 接收者昵称
	 */
	@CheckNull
	@CheckMaxLength(max=50)
	private String receiverNickname;
	
	
	/**
	 * 发送者昵称
	 */
	@CheckNull
	@CheckMaxLength(max=50)
	private String senderNickname;
	
	
}
