package com.taw.pub.picture.response;

import java.util.Date;

public class PictureCommentInfoResp {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getCrdt() {
		return crdt;
	}

	public void setCrdt(Date crdt) {
		this.crdt = crdt;
	}

	/*主键*/
	private Long id;
	
	/*评论者昵称*/
	private String nickname;
	
	/*评论内容*/
	private String content;
	
	/*评论时间*/
	private Date crdt;
}
