package com.taw.pub.picture.enums;

public enum EnumThumbType {
	/**
	 * 赞
	 */
	UP("1"),
	/**
	 * 踢
	 */
	DOWN("0");

	private String value;
	
	private EnumThumbType (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
