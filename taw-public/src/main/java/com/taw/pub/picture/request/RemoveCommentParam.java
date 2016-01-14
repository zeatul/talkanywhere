package com.taw.pub.picture.request;

import com.hawk.utility.check.CheckNull;

public class RemoveCommentParam {
	
	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@CheckNull
	private Long commentId;
	
	@CheckNull
	private Long userId;

}
