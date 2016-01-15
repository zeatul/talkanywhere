package com.taw.pub.scene.response;

import java.util.Date;
import java.util.List;

public class SendConverstaionResp {
	
	

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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
	private Date postDate;

}
