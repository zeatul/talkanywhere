package com.taw.scene.domainex;

public class FuzziedSceneDomain {
	public java.math.BigDecimal getLeftBottomLng() {
		return leftBottomLng;
	}


	public void setLeftBottomLng(java.math.BigDecimal leftBottomLng) {
		this.leftBottomLng = leftBottomLng;
	}


	public java.math.BigDecimal getLeftBottomLat() {
		return leftBottomLat;
	}


	public void setLeftBottomLat(java.math.BigDecimal leftBottomLat) {
		this.leftBottomLat = leftBottomLat;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	/**
	 * 左下角经度*/
	private java.math.BigDecimal leftBottomLng;
	
	/**
	 * 左下角纬度*/
	private java.math.BigDecimal leftBottomLat;
	

	/**
	 * 场景数量
	 */
	private Integer count;
}
