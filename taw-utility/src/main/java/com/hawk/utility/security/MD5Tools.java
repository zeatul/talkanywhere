package com.hawk.utility.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;



public class MD5Tools {

	public static String sign(String message) {
		try {
			return DigestUtils.md5Hex(message.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
//		//TODO:比较和.net加密后的结果是否一致
//		
//		 try {
//			MessageDigest messageDigest  = MessageDigest.getInstance("MD5");
//			if (key==null)
//				key = "";
//			byte[] b = messageDigest.digest((message+key).getBytes("utf-8"));
//			return DESTools.toHexString(b);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}  

		
	}
}
