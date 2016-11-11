package com.taw.pub.version.enums;

public enum EnumModel {
	
	/**
	 * 安卓
	 */
	ANDROID("0"),
	/**
	 * 苹果
	 */
	IOS("1");
	
	public String getValue() {
		return value;
	}

	private String value;
	
	private EnumModel (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
