package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;
import com.taw.pub.scene.com.MapPoint;

public class QuerySceneInRegionParam {
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getBlock() {
		return block;
	}
	public void setBlock(Integer block) {
		this.block = block;
	}
	public Long getCity() {
		return city;
	}
	public void setCity(Long city) {
		this.city = city;
	}
	public MapPoint getRightTop() {
		return rightTop;
	}
	public void setRightTop(MapPoint rightTop) {
		this.rightTop = rightTop;
	}
	public MapPoint getLeftBottom() {
		return leftBottom;
	}
	public void setLeftBottom(MapPoint leftBottom) {
		this.leftBottom = leftBottom;
	}
	/**
	 * 右上角
	 */
	@CheckNull
	private MapPoint rightTop;
	/**
	 * 左下角
	 */
	@CheckNull
	private MapPoint leftBottom;
	
	/**
	 * 城市
	 */
	private Long city;
	
	/**
	 * 地图缩放比率
	 */
	@CheckNull
	private Integer level;
	
	/**
	 * 客户端提交的数据
	 */
	@CheckNull
	private Integer block;
	
	/**
	 * 用户id
	 */
	private long userId;


}
