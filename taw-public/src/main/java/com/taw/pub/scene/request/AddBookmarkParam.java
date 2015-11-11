package com.taw.pub.scene.request;

import java.util.List;

import com.hawk.utility.check.CheckNull;
import com.hawk.utility.check.CheckSize;

public class AddBookmarkParam {
	
	public List<Long> getSceneIds() {
		return sceneIds;
	}

	public void setSceneIds(List<Long> sceneIds) {
		this.sceneIds = sceneIds;
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
	private List<Long> sceneIds;
	
	@CheckNull
	/**
	 * 用户id
	 */
	private Long userId;

}
