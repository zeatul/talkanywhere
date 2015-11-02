package com.hawk.pub.version.enums;

public enum EnumModel {
	
	/**
	 * 安卓客户端
	 */
	ANDROID("android"), 
	/**
	 * IOS客户端
	 */
	IOS("ios");
	
	
	
	private String value;
	
	private EnumModel (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
