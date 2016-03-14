package com.taw.pub.scene.response;

public class PicDescResp {
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 图片id ，在图片管理表的主键
	 */
	private Long id;
	/**
	 * 图片uuid
	 */
	private String uuid;
	public PicDescResp(Long id, String uuid) {
		super();
		this.id = id;
		this.uuid = uuid;
	}
	
	public PicDescResp(){
		
	}
	

}
