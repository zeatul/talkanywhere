package com.taw.pub.scene.response;

import java.util.Date;
import java.util.List;

public class MessageResp {
	
	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Short getOnScene() {
		return onScene;
	}

	public void setOnScene(Short onScene) {
		this.onScene = onScene;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getPicCount() {
		return picCount;
	}

	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}

	public List<PicDescResp> getPicList() {
		return picList;
	}

	public void setPicList(List<PicDescResp> picList) {
		this.picList = picList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	

	public Long getSenderFpdId() {
		return senderFpdId;
	}

	public void setSenderFpdId(Long senderFpdId) {
		this.senderFpdId = senderFpdId;
	}

	public String getSenderNickname() {
		return senderNickname;
	}

	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/*主键*/
	private Long id;
	
	
	/**
	 * 发言者性别
	 */
	private String sex;
	
	/*接收者ID*/
	private Long receiverId;
	
	/*发送者ID*/
	private Long senderId;
	
	
	/*场景ID*/
	private Long sceneId;
	
	/*场景名称*/
	private String sceneName;
	
	/*内容*/
	private String message;
	
	/*发送者者在场景唯一标识*/
	private Long senderFpdId;
	

	
	/*发送者昵称*/
	private String senderNickname;
	
	/*发送时间*/
	private Date sendTime;
	
	private Integer picCount;
	
	private List<PicDescResp> picList;
	
	/**
	 * 发言用户是否正在发言场景
	 */
	private Short onScene;

}
