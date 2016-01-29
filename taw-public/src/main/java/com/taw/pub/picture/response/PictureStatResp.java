package com.taw.pub.picture.response;

public class PictureStatResp {

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
