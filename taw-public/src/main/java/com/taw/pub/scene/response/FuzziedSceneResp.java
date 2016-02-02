package com.taw.pub.scene.response;

public class FuzziedSceneResp {
	
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

	public java.math.BigDecimal getRightTopLng() {
		return rightTopLng;
	}

	public void setRightTopLng(java.math.BigDecimal rightTopLng) {
		this.rightTopLng = rightTopLng;
	}

	public java.math.BigDecimal getRightTopLat() {
		return rightTopLat;
	}

	public void setRightTopLat(java.math.BigDecimal rightTopLat) {
		this.rightTopLat = rightTopLat;
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
	 * 右上角经度*/
	private java.math.BigDecimal rightTopLng;
	
	/**
	 * 右上角纬度*/
	private java.math.BigDecimal rightTopLat;
	
	/**
	 * 场景数量
	 */
	private Integer count;

}
