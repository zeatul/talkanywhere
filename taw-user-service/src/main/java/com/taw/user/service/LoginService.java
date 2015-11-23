package com.taw.user.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.request.LoginParam;
import com.taw.pub.user.request.LogoutParam;
import com.taw.user.domain.LoginDomain;
import com.taw.user.domain.UserDomain;
import com.taw.user.exception.UnMatchUserPasswordException;
import com.taw.user.mapper.LoginMapper;

@Service
public class LoginService {
	
		
	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	@Qualifier("taw_user_service_redis_client")
	private RedisClient redisClient;
	
	private final int expire = 3600 *72 ; //token缓存72小时
	
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
	 * 计算token在缓存里key
	 * @param token
	 * @param userId
	 * @return
	 */
	private String computeCacheKey(String token){
		return "token_xx_" + token;
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
			throw new UnMatchUserPasswordException();
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
		
		/**
		 * token 加入缓存 
		 */
		redisClient.set(computeCacheKey(token), userDomain.getId().toString(),expire, true);
		
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
		String token = logoutParam.getToken();
		/**
		 * TODO:从数据库删除 ？ 是物理删除？还是逻辑删除
		 */
		loginMapper.delete(token);		
		/**
		 * 从缓存删除 
		 */
		redisClient.delete(computeCacheKey(token), true);
	}
	
	/**
	 * 根据token查询用户ID
	 * @param token
	 * @return 用户ID
	 */
	public Long queryUserId(String token){
		if (StringTools.isNullOrEmpty(token))
			return null;
		String useIdStr = redisClient.get(token);
		
		if (StringTools.isNullOrEmpty(useIdStr)){
			LoginDomain loginDomain = loginMapper.loadLogin(token);			
			if (loginDomain == null){
				return null;
			}else{
				useIdStr = loginDomain.getUserId().toString();
				/**
				 * 加入缓存,72小时
				 */
				redisClient.set(computeCacheKey(token), useIdStr,expire, true);
			}
		}
		
		return new Long(useIdStr);
		
	}
}
