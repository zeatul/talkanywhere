package com.taw.pub.scene.enums;

public enum EnumMessageType {
	
	/**
	 * 场景会话
	 */
	CONVERSATION("0"),
	/**
	 * 场景私信
	 */
	MESSAGE("1");
	
	private String value;
	
	private EnumMessageType (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
