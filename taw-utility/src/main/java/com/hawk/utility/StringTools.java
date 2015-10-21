package com.hawk.utility;

public class StringTools {

	public static  boolean isNullOrEmpty(String c){
		if (c == null || c.trim().length()==0)
			return true;
		return false;
	}
	
	public static  boolean isNotNullOrEmpty(String c){
		return !isNullOrEmpty(c);
	}
}
