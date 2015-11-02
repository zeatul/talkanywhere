package com.taw.user.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.request.LogoutParam;
import com.taw.user.domain.LoginDomain;
import com.taw.user.domain.UserDomain;
import com.taw.user.mapper.LoginMapper;

@Service
public class LoginService {
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private UserService userService;
	
	public static class LoginInfo{
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public UserDomain getUserDomain() {
			return userDomain;
		}
		public void setUserDomain(UserDomain userDomain) {
			this.userDomain = userDomain;
		}
		private String token;
		private UserDomain userDomain;
	}
	
	
	/**
	 * 返回登录token
	 * @param loginParam
	 * @return
	 * @throws Exception 
	 */
	public LoginInfo login(LoginParam loginParam) throws Exception{
		/**
		 * 一般校验
		 */
		CheckTools.check(loginParam);
		
		/**
		 * 特殊校验，账号，密码匹配
		 */
		UserDomain userDomain = userService.queryUser(loginParam.getMobile());
		if (userDomain == null)
			throw new Exception("user account doesn't match password");
		String signedPassword = userService.signedPassword(loginParam.getPassword());
		if (!signedPassword.equals(userDomain.getPassword()))
			throw new Exception("user account doesn't match password");
		
		/**
		 * 转换为domain
		 */
		LoginDomain loginDomain = new LoginDomain();
		DomainTools.copy(loginParam, loginDomain);
		/**
		 * 赋特殊值
		 */
		Date now = DateTools.now();
		loginDomain.setExpireDate(DateTools.addDays(now, 365*20));
		loginDomain.setLastAccessDate(now);
		loginDomain.setLoginDate(now);
		String token = UUID.randomUUID().toString().replace("_", "").replace("-", "");
		loginDomain.setToken(token);
		loginDomain.setUserId(userDomain.getId());
		
		loginMapper.insert(loginDomain);
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setToken(token);
		loginInfo.setUserDomain(userDomain);
		return loginInfo;
		
	}

	/**
	 * 登出
	 * @param logoutParam
	 * @throws Exception
	 */
	public void logout(LogoutParam logoutParam) throws Exception{
		CheckTools.check(logoutParam);
		/**
		 * 从数据库删除
		 */
		loginMapper.delete(logoutParam.getToken());
		
		
	}
}
