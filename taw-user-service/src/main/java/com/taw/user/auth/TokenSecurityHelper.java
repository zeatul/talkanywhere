package com.taw.user.auth;

import com.hawk.utility.DateTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.security.DESTools;
import com.taw.user.configure.UserServiceConfigure;

public class TokenSecurityHelper {

	

	public void setUserServiceConfigure(UserServiceConfigure userServiceConfigure) {
		this.userServiceConfigure = userServiceConfigure;
	}

	private UserServiceConfigure userServiceConfigure;

		

	public TokenSecurityHelper() {
		
	}

	/**
	 * 用输入的参数拼接出加密的前后台交互的key
	 * 
	 * @param token
	 *            登录生成的token
	 * @param time
	 *            当前时间的毫秒数
	 * @param imei
	 *            设备串号
	 * @return
	 */
	public String generate(String token, long time, String imei) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(token).append("|").append(time).append("|").append(imei);
			return DESTools.encrypt(sb.toString(), userServiceConfigure.getTokenKey());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据密文，计算出token
	 * 
	 * @param ticket 前后台交互验证的票据
	 * @return
	 */
	public String computeToken(String ticket) {
		try {

			if (StringTools.isNullOrEmpty(ticket))
				throw new RuntimeException("the ticket is null");

			String[] strArray = DESTools.decrypt(ticket, userServiceConfigure.getTokenKey()).split("\\|");
			long now = DateTools.now().getTime();

			long send = new Long(strArray[1]);

			long diff = Math.abs(now - send);

			if (diff > 1000 * 60 * 5)
				throw new RuntimeException("The time in decryptString is illegal");

			return strArray[0];
		} catch (Exception e) {
			if (e instanceof RuntimeException)
				throw (RuntimeException) e;
			else
				throw new RuntimeException(e);
		}
	}

}
