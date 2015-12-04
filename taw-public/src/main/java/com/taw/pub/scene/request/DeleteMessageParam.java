package com.taw.pub.scene.request;

import java.util.List;

import com.hawk.utility.check.CheckNull;
import com.hawk.utility.check.CheckSize;


/**
 * 删除收到的私人消息参数
 * @author pzhang1
 *
 */
public class DeleteMessageParam {
	

	
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
