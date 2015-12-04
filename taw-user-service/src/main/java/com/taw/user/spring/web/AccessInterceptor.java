package com.taw.user.spring.web;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpRequestInfo;
import com.hawk.utility.JsonTools;
import com.taw.user.auth.AuthThreadLocal;
import com.taw.user.auth.TokenSecurityHelper;
import com.taw.user.service.LoginService;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final Set<String> protectedPaths ;
	
	@Autowired
	private TokenSecurityHelper tokenSecurityHelper;
	
	@Autowired
	private LoginService loginService;
	
	public AccessInterceptor(){
		protectedPaths = new HashSet<String>();
		protectedPaths.add("/scene/enter.do");
		protectedPaths.add("/scene/message/send.do");
		protectedPaths.add("/scene/message/search.do");
		protectedPaths.add("/scene/message/delete.do");
		
		
		protectedPaths.add("/scene/conversation/send.do");
		protectedPaths.add("/scene/conversation/search.do");
		protectedPaths.add("/scene/conversation/delete.do");
		
		
		
		
	}


	/**
	 * This implementation always returns {@code true}.
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpRequestInfo httpRequestInfo = HttpRequestHandler.getRequestInfo(request);
		
		logger.info("+++++ receive access http request = {}" ,JsonTools.toJsonString(httpRequestInfo));

		if (protectedPaths.contains(httpRequestInfo.getPath())){
			/**
			 * 解密出token
			 */
			String token = tokenSecurityHelper.computeToken(httpRequestInfo.getTicket());
			/**
			 * 根据token计算出用户ID
			 * TODO: 是否需要将用户id做签名 影藏在token里，做验证?
			 */
			Long userId = loginService.queryUserId(token);
			
			if (userId == null){
				throw new RuntimeException("token is invalid!");
			}else{
				/**
				 * userId 加入threadlocal
				 */
				AuthThreadLocal.setUserId(userId);
			}
		}
		
		
		/**
		 * TODO:更新用户最后访问时间
		 */
		return true;
	}
	
	
}
