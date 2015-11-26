package com.taw.pub.scene.request;

import java.util.List;

import com.hawk.utility.check.CheckNull;

public class ComplexMessage {
	
	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}



	/**
	 * 文本消息
	 */
	@CheckNull
	private String textMessage;
	
	/**
	 * 图片集合
	 */
	private List<String> pics;
	
	

}
