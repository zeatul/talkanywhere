package com.hawk.pub.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FrameworkContext implements ApplicationContextAware{

	private static  ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		FrameworkContext.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
}
