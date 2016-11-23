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
import com.taw.user.exception.InvalidTokenException;
import com.taw.user.service.LoginService;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final Set<String> protectedPaths ;
	
	@Autowired
	private TokenSecurityHelper tokenSecurityHelper;
	
	@Autowired
	private LoginService loginService;
	
	public AccessInterceptor(){
		
		/**
		 * 必须登录
		 */
		protectedPaths = new HashSet<String>();
		
		protectedPaths.add("/user/logout.do");
		protectedPaths.add("/user/info.do");
		
		protectedPaths.add("/user/contact/add.do");
		protectedPaths.add("/user/contact/remove.do");
		protectedPaths.add("/user/contact/search.do");
		
		protectedPaths.add("/user/pwd/update.do");
		
		protectedPaths.add("/scene/enter.do");
		protectedPaths.add("/scene/leave.do");
		
		protectedPaths.add("/scene/footprint/search.do");
		
		protectedPaths.add("/scene/message/send.do");
		protectedPaths.add("/scene/message/search.do");
		protectedPaths.add("/scene/message/remove.do");
		
		
		protectedPaths.add("/scene/conversation/send.do");
//		protectedPaths.add("/scene/conversation/search.do");
		protectedPaths.add("/scene/conversation/remove.do");
		
		protectedPaths.add("/scene/bookmark/add.do");
		protectedPaths.add("/scene/bookmark/search.do");
		protectedPaths.add("/scene/bookmark/remove.do");
		
		protectedPaths.add("/scene/footprint/search.do");
		
		protectedPaths.add("/scene/present/change.do");
		
		
		
		protectedPaths.add("/pic/upload.do");
		protectedPaths.add("/pic/length.do");
		protectedPaths.add("/pic/thumb.do");
		protectedPaths.add("/pic/comment/add.do");		
		protectedPaths.add("/pic/comment/remove.do");
		protectedPaths.add("/pic/myself.do");
		protectedPaths.add("/pic/remove.do");
		
		
	
		
		
		
		
		
		
	}


	/**
	 * This implementation always returns {@code true}.
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		HttpRequestInfo httpRequestInfo = HttpRequestHandler.getRequestInfo(request);
		
		logger.info("+++++ receive access http request = {}" ,JsonTools.toJsonString(httpRequestInfo));

		if (protectedPaths.contains(httpRequestInfo.getPath())){
			ddd(httpRequestInfo);
		}else{
			if (httpRequestInfo.getTicket()!=null){
				ddd(httpRequestInfo);
			}
		}
		
		
		/**
		 * TODO:更新用户最后访问时间
		 */
		return true;
	}
	
	private void ddd(HttpRequestInfo httpRequestInfo) throws Exception{
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
			throw new InvalidTokenException();
		}else{
			/**
			 * userId 加入threadlocal
			 */
			AuthThreadLocal.setUserId(userId);
			AuthThreadLocal.setToken(token);
		}
	}
	
	
}
