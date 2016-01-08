package com.taw.pub.scene.response;

import java.util.Date;
import java.util.List;

public class MessageResp {
	
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

}
