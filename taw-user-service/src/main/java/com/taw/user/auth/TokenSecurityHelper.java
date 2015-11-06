package com.taw.user.auth;

import org.springframework.beans.factory.annotation.Autowired;

import com.hawk.utility.DateTools;
import com.hawk.utility.security.DESTools;
import com.taw.user.configure.UserServiceConfigure;

public class TokenSecurityHelper {
	
	@Autowired
	private UserServiceConfigure userServiceConfigure;
	
	private final String  encryptKey ;
	
	private String computeTokenKey(){
		String tokenKey = userServiceConfigure.getTokenKey();
		StringBuilder sb = new StringBuilder();
		sb.append(tokenKey.charAt(0));
		sb.append(tokenKey.charAt(2));
		sb.append(tokenKey.charAt(7));
		sb.append("#");
		sb.append(tokenKey.charAt(3));
		sb.append(tokenKey.charAt(18));
		sb.append("!");		
		sb.append(tokenKey.charAt(19));
		return sb.toString();		
	}
	
	public TokenSecurityHelper(){
		this.encryptKey = computeTokenKey();
	}
	

	/**
	 * 用输入的参数拼接出加密的前后台交互的key
	 * @param token 登录生成的token
	 * @param time  当前时间的毫秒数
	 * @param imei  设备串号
	 * @return
	 */
	public String generate(String token , long time , String imei) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(token).append("|")
				.append(time).append("|")
				.append(imei);
			return DESTools.encrypt(sb.toString(), this.encryptKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 根据密文，计算出token
	 * @param decryptString
	 * @return
	 */
	public String computeToken(String decryptString){
		try {
			String[] strArray =  DESTools.decrypt(decryptString, this.encryptKey).split("|");
			long now = DateTools.now().getTime();
			
			long send = new Long(strArray[1]);
			
			long diff = Math.abs(now - send);
			
			if (diff > 1000*60*5)
				throw new RuntimeException("The time in decryptString is illegal");
			
			return strArray[0];
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
