package com.taw.pub.user.response;

public class UserContactResp {
	
	public Long getCoUserId() {
		return coUserId;
	}
	public void setCoUserId(Long coUserId) {
		this.coUserId = coUserId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	private Long coUserId;
	private String remark;

}
