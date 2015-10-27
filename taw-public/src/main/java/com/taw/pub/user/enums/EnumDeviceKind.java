package com.taw.pub.user.enums;

import com.hawk.utility.EnumTools;

/**
 * 设备类型
 * @author zhang_000
 *
 */
public enum EnumDeviceKind {
	
	ANDROID("0"),IOS("1"),WINPHONE("2"),HTML("3");
	
	private String value;
	
	private EnumDeviceKind(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;		
	}
	
	public EnumDeviceKind parse(Object obj){
		return EnumTools.parse(obj, EnumDeviceKind.class);
	}
	
	
	
	

}
