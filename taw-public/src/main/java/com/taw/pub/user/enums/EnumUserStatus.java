package com.taw.pub.user.enums;

/**
 * 用户状态
 * @author pzhang1
 *
 */
public enum EnumUserStatus {
	/**
	 * 无效
	 */
	INVALID("0"),
	/**
	 * 有效
	 */
	VALID("1");

	private String value;
	
	private EnumUserStatus(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;		
	}
}
