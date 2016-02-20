package com.taw.pub.scene.request;

import java.util.Date;

import com.hawk.pub.enums.EnumBoolean;
import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckNull;

public class SearchConversationParam {
	



	public Long getPostUserId() {
		return postUserId;
	}


	public void setPostUserId(Long postUserId) {
		this.postUserId = postUserId;
	}


	public Long getSceneId() {
		return sceneId;
	}


	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}


	public Date getMinPostDate() {
		return minPostDate;
	}


	public void setMinPostDate(Date minPostDate) {
		this.minPostDate = minPostDate;
	}


	public Date getMaxPostDate() {
		return maxPostDate;
	}


	public void setMaxPostDate(Date maxPostDate) {
		this.maxPostDate = maxPostDate;
	}


	public Integer getOffset() {
		return offset;
	}


	public void setOffset(Integer offset) {
		this.offset = offset;
	}


	public Integer getLimit() {
		return limit;
	}


	public void setLimit(Integer limit) {
		this.limit = limit;
	}


	/**
	 * 场景主键
	 */
	@CheckNull
	private Long sceneId;
	
	
	/**
	 * 最小发言时间
	 */	
	private Date minPostDate;
	
	/**
	 * 最大发言时间
	 */
	private Date maxPostDate;
	
	
	
	/**
	 * offset 分页参数
	 */
	@CheckNull
	private Integer offset;
	
	
	/**
	 * limit 分页参数
	 */
	@CheckNull
	private Integer limit;
	
	
	/**
	 * 发言者Id
	 */
	private Long postUserId;
	
	
	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}


	/**
	 * true：前进，false :
	 */
	@CheckNull
	@CheckEnum(parser=EnumBoolean.class)
	private Integer order ;
	

}
