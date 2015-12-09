package com.taw.pub.user.request;

import com.hawk.utility.check.CheckNull;

public class SearchUserContactParam {
	
	




	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	@CheckNull
	/**
	 * 用户Id
	 */
	private Long userId;
	

}
