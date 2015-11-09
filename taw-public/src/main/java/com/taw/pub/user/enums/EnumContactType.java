package com.taw.pub.user.enums;

/**
 * 用户状态
 * @author pzhang1
 *
 */
public enum EnumContactType {
	/**
	 * 黑名单
	 */
	BLACKLIST("0");

	private String value;
	
	private EnumContactType(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;		
	}
}
