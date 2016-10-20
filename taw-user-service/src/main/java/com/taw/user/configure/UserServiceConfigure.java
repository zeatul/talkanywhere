package com.taw.user.configure;

import com.hawk.utility.security.DESTools;

public class UserServiceConfigure {
	
	

	public String getDownloadPrefix() {
		return downloadPrefix;
	}

	public void setDownloadPrefix(String downloadPrefix) {
		this.downloadPrefix = downloadPrefix;
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public boolean isProd() {
		return prod;
	}

	public void setProd(boolean prod) {
		this.prod = prod;
	}

	public String getPwdMd5Key() {
		return pwdMd5Key;
	}

	public void setPwdMd5Key(String pwdMd5Key) {
		try {
			this.pwdMd5Key =   DESTools.decrypt(pwdMd5Key, "taw_hawk") ;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成密码加密字符串的key
	 * TODO: 改成加密数据放在配置文件里
	 */
	private String pwdMd5Key;
	
	/**
	 * 生成token加密的key
	 * TODO: 改成加密数据放在配置文件里
	 */
	private String tokenKey;
	
	
	/**
	 * 是否为生成环境，默认为false
	 */
	private boolean prod=false;
	
	private String downloadPrefix;

}
