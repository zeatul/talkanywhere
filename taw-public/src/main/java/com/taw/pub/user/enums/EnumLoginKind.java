package com.taw.pub.user.enums;

/**
 * 用户类型
 * @author pzhang1
 *
 */
public enum EnumLoginKind {
	
	/**
	 * 会话
	 */
	TEMPORARY("0"),
	
	/**
	 * 永久
	 */
	PERMANENT("1");

	private String value;
	
	private EnumLoginKind(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;		
	}
}
