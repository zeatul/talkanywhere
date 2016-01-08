package com.taw.pub.picture.enums;

public enum EnumPictureHotLevel {

	NORMAL(0),
	SCENE_HOT(10),
	GOLBAL_HOT(100);
	
	
	public int getValue() {
		return value;
	}

	private int value;
	
	private EnumPictureHotLevel (int value){
		this.value = value;
	}
	
	
}
