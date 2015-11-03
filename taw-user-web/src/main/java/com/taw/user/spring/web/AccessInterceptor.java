package com.taw.user.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AccessInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);




	/**
	 * This implementation always returns {@code true}.
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		logger.info("+++++ receive access http request");

		return true;
	}
}
