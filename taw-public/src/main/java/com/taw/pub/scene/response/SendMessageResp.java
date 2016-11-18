package com.taw.pub.scene.response;

import java.util.Date;
import java.util.List;

public class SendMessageResp {
	
	

	public Date getSendTime() {
		return SendTime;
	}

	public void setSendTime(Date sendTime) {
		SendTime = sendTime;
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

	/**
	 * 会话id
	 */
	private Long id;
	
	
	private List<PicDescResp> picList;
	
	/*发言时间*/
	private Date SendTime;

}
