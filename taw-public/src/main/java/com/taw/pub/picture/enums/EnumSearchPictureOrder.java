package com.taw.pub.picture.enums;

public enum EnumSearchPictureOrder {
	/**
	 * UP_COUNT
	 */
	UP("0"),
	/**
	 * COMMENT_COUNT
	 */
	COMMENT("1"),
	/**
	 * FORWARD_COUNT
	 */
	FORWARD("2"),
	/**
	 * CRDT
	 */
	CRDT("3");

	private String value;
	
	private EnumSearchPictureOrder (String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public static String toOrderByString(String orderBy){
		if (orderBy.equals("0"))
			return "up_count desc";
		else if (orderBy.equals("1"))
			return "comment_count desc";
		else if (orderBy.equals("2"))
			return "forward_count desc";
		else if (orderBy.equals("3"))
			return "crdt desc";
		else
			throw new RuntimeException("orderBy should be one of (0,1,2,3)");
	}
	
}
