package com.taw.pub.scene.enums;

public enum EnumAddBookmarkRespResult {
	/**
	 * 收藏成功
	 */
	BOOKED(1),
	/**
	 * 已经收藏
	 */
	BOOKED_BEFORE(2),
	/**
	 * 场景不存在
	 */
	SCENE_NOTEXIST(3);
	
	
	private int value;
	
	private EnumAddBookmarkRespResult (int value){
		this.value = value;
	}
	
	
	public int getValue() {
		return value;
	}
}
