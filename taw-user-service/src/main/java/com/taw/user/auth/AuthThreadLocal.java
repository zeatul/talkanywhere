package com.taw.user.auth;

/**
 * 储存线程自有的用户认证变量
 * @author pzhang1
 *
 */
public class AuthThreadLocal {
	
	private static ThreadLocal<Long> threadLoaclUserId = new ThreadLocal<Long>();
	
	private static ThreadLocal<String> threadLocalToken = new ThreadLocal<String>();
	
	public static Long getUserId(){
		return threadLoaclUserId.get();
	}
	
	public static void setUserId(Long userId){
		threadLoaclUserId.set(userId);
	}
	
	public static String getToken(){
		return threadLocalToken.get();
	}
	
	public static void setToken(String token){
		threadLocalToken.set(token);
	}

}
