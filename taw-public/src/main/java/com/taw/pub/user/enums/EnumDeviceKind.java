package com.taw.pub.user.enums;

import com.hawk.enums.EnumTools;
import com.hawk.enums.Parsable;

/**
 * 设备类型
 * @author zhang_000
 *
 */
public enum EnumDeviceKind implements Parsable{
	
	ANDROID("0"),IOS("1"),WINPHONE("2"),HTML("3");
	
	private String deviceKind;
	
	private EnumDeviceKind(String deviceKind){
		this.deviceKind = deviceKind;
	}
	
	@Override
	public String toString() {
		return deviceKind;		
	}
	
	public EnumDeviceKind parse(Object obj){
		return EnumTools.parse(obj, EnumDeviceKind.class);
	}
	
	
	
	

}
