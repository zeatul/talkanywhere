package com.taw.pub.user.enums;

/**
 * 用户类型
 * @author pzhang1
 *
 */
public enum EnumSex {
	
	/**
	 * 男
	 */
	MALE("M"),
	
	/**
	 * 女
	 */
	FEMALE("F");

	private String value;
	
	private EnumSex(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;		
	}
}
