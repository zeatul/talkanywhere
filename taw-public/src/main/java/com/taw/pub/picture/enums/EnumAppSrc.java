package com.taw.pub.picture.enums;

public enum EnumAppSrc {
	/**
	 * 会话
	 */
	CONVERSATION("0"),
	/**
	 * 私信
	 */
	MESSAGE("1");
	
	public String getValue() {
		return value;
	}

	private String value;
	
	private EnumAppSrc (String value){
		this.value = value;
	}

}
