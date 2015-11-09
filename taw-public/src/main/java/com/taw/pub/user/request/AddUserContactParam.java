package com.taw.pub.user.request;

import java.util.List;

import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckNull;
import com.hawk.utility.check.CheckSize;
import com.taw.pub.user.enums.EnumContactType;

public class AddUserContactParam {
	
	


	public List<Long> getCoUserIds() {
		return coUserIds;
	}

	public void setCoUserIds(List<Long> coUserIds) {
		this.coUserIds = coUserIds;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@CheckNull
	@CheckSize
	/**
	 * 联系人Id
	 */
	private List<Long> coUserIds;
	
	@CheckNull
	/**
	 * 用户Id
	 */
	private Long userId;
	
	/**
	 * 关系类型
	 */
	@CheckNull
	@CheckEnum(parser=EnumContactType.class)
	private String contactType;

}
