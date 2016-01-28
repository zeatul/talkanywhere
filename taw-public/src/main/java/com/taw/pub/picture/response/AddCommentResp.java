package com.taw.pub.picture.response;

import java.util.Date;

public class AddCommentResp {
	
	public Integer getForwardCount() {
		return forwardCount;
	}

	public void setForwardCount(Integer forwardCount) {
		this.forwardCount = forwardCount;
	}

	public Integer getUpCount() {
		return upCount;
	}

	public void setUpCount(Integer upCount) {
		this.upCount = upCount;
	}

	public Integer getDownCount() {
		return downCount;
	}

	public void setDownCount(Integer downCount) {
		this.downCount = downCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Date getCrdt() {
		return crdt;
	}

	public void setCrdt(Date crdt) {
		this.crdt = crdt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Long id;
	
	private Date crdt;
	
	/**
	 * 赞数量
	 */
	private Integer upCount;
	/**
	 * 踢数量
	 */
	private Integer downCount;
	/**
	 * 评论数量
	 */
	private Integer commentCount;
	
	/**
	 * 转发数量
	 */
	private Integer forwardCount;

}
