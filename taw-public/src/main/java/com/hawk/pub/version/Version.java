package com.hawk.pub.version;

public class Version {
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isForced() {
		return forced;
	}

	public void setForced(boolean forced) {
		this.forced = forced;
	}

	/**
	 * 模块名
	 */
	private String module ;
	
	/**
	 * 版本号
	 */
	private String version;
	
	/**
	 * 描述
	 */
	private String desc;
	
	/**
	 * 强制更新
	 */
	private boolean forced=false;

}
