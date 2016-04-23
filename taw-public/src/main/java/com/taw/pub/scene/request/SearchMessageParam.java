package com.taw.pub.scene.request;

import java.util.Date;

import com.hawk.pub.enums.EnumBoolean;
import com.hawk.utility.check.CheckEnum;
import com.hawk.utility.check.CheckNull;

public class SearchMessageParam {
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * userId,当前用户
	 */
	@CheckNull
	private Long userId;
	
	
	/**
	 * 对话者ID
	 */
	private Long partyId;
	
	

	/**
	 * 场景ID
	 */
	private Long sceneId;
	
	
	/**
	 * 最小发言时间
	 */	
	private Date minPostDate;
	
	/**
	 * 最大发言时间
	 */
	private Date maxPostDate;
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
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
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getIncludeSelf() {
		return includeSelf;
	}
	public void setIncludeSelf(Integer includeSelf) {
		this.includeSelf = includeSelf;
	}

	/**
	 * true：前进，false :
	 */
	@CheckNull
	@CheckEnum(parser=EnumBoolean.class)
	private Integer order ;
	
	/**
	 * 是否包含自己的发言，0，不包含，1，包含
	 */
	@CheckNull
	@CheckEnum(parser=EnumBoolean.class)
	private Integer includeSelf ;


}
