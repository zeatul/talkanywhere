package com.taw.pub.scene.response;

import java.util.Date;

public class MessageResp {
	
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
	
	
	
	/*场景ID*/
	private Long sceneId;
	
	/*场景名称*/
	private String sceneName;
	
	/*内容*/
	private String content;
	
	/*发送者者在场景唯一标识*/
	private Long senderFpdId;
	

	
	/*发送者昵称*/
	private String senderNickname;
	
	/*发送时间*/
	private Date sendTime;

}
