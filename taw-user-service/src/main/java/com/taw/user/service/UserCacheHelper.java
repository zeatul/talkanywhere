package com.taw.user.service;

import com.hawk.pub.spring.FrameworkContext;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.user.response.LoginResp;

public class UserCacheHelper {
	
	private static RedisClient redisClient = FrameworkContext.getApplicationContext().getBean(RedisClient.class);
	
	private final static String CACHED_LOGIN_RESP_HEAD = "loginResp_";
	
	public static LoginResp getCachedLoginResp(String token){
		if (StringTools.isNullOrEmpty(token))
			return null;
		token = token.trim();
		String key = CACHED_LOGIN_RESP_HEAD + token;
		
		String jsonStr = redisClient.get(key);
		
		if (StringTools.isNullOrEmpty(jsonStr))
			return null;
		
		return JsonTools.toObject(jsonStr, LoginResp.class);
	}
	
	public static void cacheLoginResp(String token,LoginResp loginResp){
		if (StringTools.isNullOrEmpty(token))
			return;
		if (loginResp == null)
			return;
		
		String key = CACHED_LOGIN_RESP_HEAD + token;
		String jsonStr = JsonTools.toJsonString(loginResp);
		
		redisClient.set(key, jsonStr, 3600*24);
	}
	
	public static void removeCachedLoginResp(String token){
		if (StringTools.isNullOrEmpty(token))
			return ;
		token = token.trim();
		String key = CACHED_LOGIN_RESP_HEAD + token;
		redisClient.delete(key);
	}

}
