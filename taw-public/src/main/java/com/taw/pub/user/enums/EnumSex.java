package com.taw.pub.user.enums;

/**
 * 用户类型
 * @author pzhang1
 *
 */
public enum EnumSex {
	
	/**
	 * 普通用户
	 */
	MALE("M"),
	
	/**
	 * 管理员
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
