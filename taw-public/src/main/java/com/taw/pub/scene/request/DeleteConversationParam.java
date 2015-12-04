package com.taw.pub.scene.request;

import java.util.List;

import com.hawk.utility.check.CheckNull;
import com.hawk.utility.check.CheckSize;


/**
 * 删除我发送的会话消息
 * @author pzhang1
 *
 */
public class DeleteConversationParam {
	

	
	public List<Long> getIds() {
		return ids;
	}
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 消息id集合
	 */
	@CheckNull
	@CheckSize
	private List<Long> ids;
	/**
	 * userId
	 */
	@CheckNull
	private Long userId;

}
