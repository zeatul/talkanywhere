package com.taw.pub.picture.request;

import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckNull;
import com.taw.pub.picture.enums.EnumThumbType;

public class ThumbPictureParam {
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getThumbType() {
		return thumbType;
	}

	public void setThumbType(String thumbType) {
		this.thumbType = thumbType;
	}

	/**
	 * 图片Id
	 */
	@CheckNull
	private Long picId;
	
	/**
	 * 用户id
	 */
	@CheckNull
	private Long userId;
	
	/**
	 * 点赞类型
	 */
	@CheckNull
	@CheckEnum(parser=EnumThumbType.class)
	private String thumbType;
	
	
	private String nickname;

}
