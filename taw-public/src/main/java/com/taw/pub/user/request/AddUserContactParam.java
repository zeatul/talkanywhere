package com.taw.pub.user.request;

import java.util.List;

import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckMaxLength;
import com.hawk.utility.check.CheckNull;
import com.hawk.utility.check.CheckSize;
import com.taw.pub.user.enums.EnumContactType;

public class AddUserContactParam {
	

	

	public List<CoUser> getCoUsers() {
		return coUsers;
	}

	public void setCoUsers(List<CoUser> coUsers) {
		this.coUsers = coUsers;
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
	private List<CoUser> coUsers;
	
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
	
	public static class CoUser{
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
		@CheckNull
		private Long coUserId;
		@CheckMaxLength(max=50)
		private String remark;
	}

}
