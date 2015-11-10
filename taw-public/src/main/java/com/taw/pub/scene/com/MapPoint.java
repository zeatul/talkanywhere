package com.taw.pub.scene.com;

import com.hawk.utility.check.CheckNull;

/**
 * 地图坐标点
 * @author pzhang1
 *
 */
public class MapPoint {

	public java.math.BigDecimal getLng() {
		return lng;
	}

	public void setLng(java.math.BigDecimal lng) {
		this.lng = lng;
	}

	public java.math.BigDecimal getLat() {
		return lat;
	}

	public void setLat(java.math.BigDecimal lat) {
		this.lat = lat;
	}

	/*经度*/
	@CheckNull
	private java.math.BigDecimal lng;
	
	/*纬度*/
	@CheckNull
	private java.math.BigDecimal lat;
}
