package com.taw.pub.picture.response;

public class PicturePathResp {
	
	

	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 缩略图地址
	 */
	private String surl;
	
	/**
	 * 图片地址
	 */
	private String url;

}
