	package com.taw.pub.scene.request;

import java.util.List;

import com.hawk.utility.check.CheckMaxLength;
import com.hawk.utility.check.CheckNull;

public class SendMessageParam {
	
	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getReceiverFpdId() {
		return receiverFpdId;
	}

	public void setReceiverFpdId(Long receiverFpdId) {
		this.receiverFpdId = receiverFpdId;
	}

	public Long getSenderFpdId() {
		return senderFpdId;
	}

	public void setSenderFpdId(Long senderFpdId) {
		this.senderFpdId = senderFpdId;
	}

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
	 * 接收者在场景唯一标识
	 */	
	private Long receiverFpdId;
	
	/**
	 * 场景ID
	 */
	@CheckNull
	private Long sceneId;
	
	
	private String message;
	
	/**
	 * 发送者ID
	 */
	@CheckNull
	private Long senderId;
	
	
	/**
	 * 发送者者在场景唯一标识
	 */
	@CheckNull
	private Long senderFpdId;
	
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
	
	
	/**
	 * 发送的图片集合
	 */
	private List<String> pics;
	
	
}
