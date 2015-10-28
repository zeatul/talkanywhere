package com.hawk.utility;

import java.util.Random;

public class StringTools {

	/**
	 * 判断string是否为空
	 * @param str
	 * @return
	 */
	public static  boolean isNullOrEmpty(String str){
		if (str == null || str.trim().length()==0)
			return true;
		return false;
	}
	
	/**
	 * 判断string是否非空
	 * @param str
	 * @return
	 */
	public static  boolean isNotNullOrEmpty(String str){
		return !isNullOrEmpty(str);
	}
	
	private static Random random = new Random();
	/**
	 * 产生随机的纯数字的字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static String randomNumberString(int length){
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<length ; i++){			
			int t = random.nextInt(10);				
			sb.append(t);
		}
		return sb.toString();
	}
}
