package com.taw.user.configure;

import com.hawk.utility.security.DESTools;

public class UserServiceConfigure {
	
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
	 */
	private String pwdMd5Key;

}