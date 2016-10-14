package com.taw.pub.version.response;

public class QueryVersionResponse {
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public short getForced() {
		return forced;
	}

	public void setForced(short forced) {
		this.forced = forced;
	}

	/**
	 * 最新版本
	 */
	private String latestVersion;
	
	/**
	 * 下载地址	
	 */
	private String downloadUrl;
	
	/**
	 * 强制更新
	 */
	private short forced;
	
	/**
	 * 更新描述
	 */
	private String desc;

}
