package com.taw.pub.scene.enums;

/**
 * 用户离开场景方式
 * @author pzhang1
 *
 */
public enum EnumLeaveType {
	
	/**
	 * 用户主动离开
	 */
	SELF("0");
	
	
	
	private String value;
	
	private EnumLeaveType (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

	
}
