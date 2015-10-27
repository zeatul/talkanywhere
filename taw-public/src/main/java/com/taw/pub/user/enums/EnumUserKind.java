package com.taw.pub.user.enums;

/**
 * 用户类型
 * @author pzhang1
 *
 */
public enum EnumUserKind {
	
	/**
	 * 普通用户
	 */
	NORMAL("0"),
	
	/**
	 * 管理员
	 */
	AMIN("1");

	private String value;
	
	private EnumUserKind(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;		
	}
}
