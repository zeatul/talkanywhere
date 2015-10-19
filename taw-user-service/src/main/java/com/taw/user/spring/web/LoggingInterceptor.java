package com.taw.user.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggingInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);


	/**
	 * This implementation always returns {@code true}.
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.info("+++++ receive logging http request");
		
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		logger.info("+++++ finish dealing with http request");

	}
}
