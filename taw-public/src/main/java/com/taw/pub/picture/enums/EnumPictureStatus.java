package com.taw.pub.picture.enums;

public enum EnumPictureStatus {
	
	NORMAL("1");

	private String value;
	
	private EnumPictureStatus (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}

}
