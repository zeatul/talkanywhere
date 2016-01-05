package com.taw.user.auth;

import com.hawk.utility.DateTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.security.DESTools;
import com.taw.user.configure.UserServiceConfigure;
import com.taw.user.exception.FailedDecryptTokenException;
import com.taw.user.exception.InvalidTicketTimeException;
import com.taw.user.exception.InvalidTokenException;

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
	 * @throws Exception 
	 */
	public String computeToken(String ticket) throws Exception {
		
		if (StringTools.isNullOrEmpty(ticket))
			throw new InvalidTokenException();
		
		String decryptedTicket ;
		try {
			decryptedTicket = DESTools.decrypt(ticket, userServiceConfigure.getTokenKey());
		} catch (Exception e) {
			throw new FailedDecryptTokenException();
		}

		String[] strArray = decryptedTicket.split("\\|");
		long now = DateTools.now().getTime();

		long send = new Long(strArray[1]);

		long diff = Math.abs(now - send);

		if (diff > 1000 * 60 * 5)
			throw new InvalidTicketTimeException();

		return strArray[0];

	}

}
