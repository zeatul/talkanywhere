package com.taw.pub.scene.response;

public class SceneResp {
	

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
	 * 经度
	 */
	private java.math.BigDecimal lng;
	
	/**
	 * 纬度
	 */
	private java.math.BigDecimal lat;
	
	/**
	 * 在线人数
	 */
	private Integer onlineCount;
	
	/**
	 * 进入人数
	 */
	private Integer enterCount;

}
