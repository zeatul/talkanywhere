package com.taw.pub.scene.response;

public class SceneResp {
	

	public Integer getFavored() {
		return favored;
	}

	public void setFavored(Integer favored) {
		this.favored = favored;
	}

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

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Integer getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(Integer onlineCount) {
		this.onlineCount = onlineCount;
	}

	public Integer getEnterCount() {
		return enterCount;
	}

	public void setEnterCount(Integer enterCount) {
		this.enterCount = enterCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 场景名称
	 */
	private String name;
	
	/**
	 * 场景类型
	 */
	private String kind;
	
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
	 * 半径(米)*/
	private Integer radius;
	
	/**
	 * 在线人数
	 */
	private Integer onlineCount;
	
	/**
	 * 进入人数
	 */
	private Integer enterCount;
	
	/**
	 * 是否收藏 0：未收藏， 1：已收藏
	 */
	private Integer favored = 0;

}
