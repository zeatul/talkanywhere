package com.taw.pub.picture.request;

import com.hawk.utility.check.CheckNull;

public class AddCommentParam {
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@CheckNull
	private Long userId;
	
	@CheckNull
	private Long picId;
	
	private String nickname;
	
	/**
	 * 内容
	 */
	@CheckNull
	private String content;

}
