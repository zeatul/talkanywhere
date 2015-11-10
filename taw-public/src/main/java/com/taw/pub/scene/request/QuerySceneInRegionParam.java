package com.taw.pub.scene.request;

import com.hawk.utility.check.CheckNull;
import com.taw.pub.scene.com.MapPoint;

public class QuerySceneInRegionParam {
	
	public MapPoint getTopLeft() {
		return topLeft;
	}
	public void setTopLeft(MapPoint topLeft) {
		this.topLeft = topLeft;
	}
	public MapPoint getTopRight() {
		return topRight;
	}
	public void setTopRight(MapPoint topRight) {
		this.topRight = topRight;
	}
	public MapPoint getBottomLeft() {
		return bottomLeft;
	}
	public void setBottomLeft(MapPoint bottomLeft) {
		this.bottomLeft = bottomLeft;
	}
	public MapPoint getBottomRight() {
		return bottomRight;
	}
	public void setBottomRight(MapPoint bottomRight) {
		this.bottomRight = bottomRight;
	}
	/**
	 * 左上角
	 */
	@CheckNull
	private MapPoint topLeft;
	/**
	 * 右上角
	 */
	@CheckNull
	private MapPoint topRight;
	/**
	 * 左下角
	 */
	@CheckNull
	private MapPoint bottomLeft;
	/**
	 * 右下角	
	 */
	@CheckNull
	private MapPoint bottomRight;

}
