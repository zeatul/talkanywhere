package com.hawk.utility.security;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Tools {
	
	public static String encode(String str){
		Base64 base64 = new Base64();
		try {
			return new String(base64.encode(str.getBytes("utf-8")),"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String decode(String str){
		Base64 base64 = new Base64();
		
		try {
			return new String(base64.decode(str.getBytes("utf-8")),"utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] decode2(String str){
		Base64 base64 = new Base64();
		
		try {
			return base64.decode(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

}
