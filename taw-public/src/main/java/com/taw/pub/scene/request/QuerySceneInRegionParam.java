package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;
import com.taw.pub.scene.com.MapPoint;

public class QuerySceneInRegionParam {
	
	public Integer getBlk() {
		return blk;
	}
	public void setBlk(Integer blk) {
		this.blk = blk;
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
	private Integer blk;


}
