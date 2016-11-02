package com.taw.user.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.request.LogoutParam;
import com.taw.pub.user.response.LoginResp;
import com.taw.user.domain.LoginDomain;
import com.taw.user.domain.UserDomain;
import com.taw.user.exception.UnMatchUserPasswordException;
import com.taw.user.exception.UserNotFoundException;
import com.taw.user.mapper.LoginMapper;

@Service
public class LoginService {
	
		
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 返回登录token
	 * @param loginParam
	 * @return
	 * @throws Exception 
	 */
	public LoginResp login(LoginParam loginParam) throws Exception{
		/**
		 * 一般校验
		 */
		CheckTools.check(loginParam);
		
		/**
		 * 特殊校验，账号，密码匹配
		 */
		UserDomain userDomain = userService.queryUser(loginParam.getMobile());
		if (userDomain == null)
			throw new UserNotFoundException();
		String signedPassword = userService.signedPassword(loginParam.getPassword());
		if (!signedPassword.equals(userDomain.getPassword()))
			throw new UnMatchUserPasswordException();
		
		/**
		 * 转换为domain
		 */
		LoginDomain loginDomain = new LoginDomain();
		DomainTools.copy(loginParam, loginDomain);
		/**
		 * 赋特殊值
		 */
		Date now = DateTools.now();
		/**
		 * TODO:有效期待考虑
		 */
		loginDomain.setExpireDate(DateTools.addDays(now, 365*20));
		loginDomain.setLastAccessDate(now);
		loginDomain.setLoginDate(now);
		String token = UUID.randomUUID().toString().replace("_", "").replace("-", "");
		loginDomain.setToken(token);
		loginDomain.setUserId(userDomain.getId());		
		loginMapper.insert(loginDomain);
		
		
		
		
		LoginResp loginResp = new LoginResp();
		loginResp.setToken(token);
		loginResp.setUserId(userDomain.getId());
		loginResp.setSex(userDomain.getSex());
		
		/**
		 * token 加入缓存 
		 */
		UserCacheHelper.cacheLoginResp(token, loginResp);
		
		return loginResp;
		
	}

	
	
	/**
	 * 登出
	 * @param logoutParam
	 * @throws Exception
	 */
	public void logout(LogoutParam logoutParam) throws Exception{
		CheckTools.check(logoutParam);
		String token = logoutParam.getToken();
		/**
		 * TODO:从数据库删除 ？ 是物理删除？还是逻辑删除
		 */
		LoginDomain loginDomain = loginMapper.load(token);
		if (loginDomain != null){
			loginDomain.setLogoutDate(DateTools.now());
			loginMapper.update(loginDomain);	
		}
		/**
		 * 从缓存删除 
		 */
		UserCacheHelper.removeCachedLoginResp(token);
	}
	
	/**
	 * 根据token查询用户ID
	 * @param token
	 * @return 用户ID
	 */
	public Long queryUserId(String token){
		if (StringTools.isNullOrEmpty(token))
			return null;
		LoginResp loginResp = UserCacheHelper.getCachedLoginResp(token);
		
		if (loginResp == null){
			LoginDomain loginDomain = loginMapper.load(token);			
			if (loginDomain == null){
				return null;
			}else{
				if (loginDomain.getLogoutDate() != null)
					return null;

				UserDomain userDomain = userService.loadUser(loginDomain.getUserId(), true);
				
				if (userDomain == null)
					return null;
				/**
				 * 加入缓存,24小时
				 */
				loginResp = new LoginResp();
				loginResp.setToken(token);
				loginResp.setSex(userDomain.getSex());
				loginResp.setUserId(userDomain.getId());
				UserCacheHelper.cacheLoginResp(token, loginResp);
			}
		}
		
		return loginResp.getUserId();
		
	}
}
