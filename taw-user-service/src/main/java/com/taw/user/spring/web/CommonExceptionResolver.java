package com.taw.user.spring.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.hawk.pub.web.ExceptionResponse;





public class CommonExceptionResolver implements HandlerExceptionResolver {

	private static Logger logger = LoggerFactory.getLogger(CommonExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {


		logger.error("caught exception", ex);
		ExceptionResponse r = new ExceptionResponse(ex);
		String output = r.toJson();
		
		

		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("errorMsg", output);
		
		modelAndView.setViewName("error");
		
		
		return modelAndView;
		
	}
	
	

}
