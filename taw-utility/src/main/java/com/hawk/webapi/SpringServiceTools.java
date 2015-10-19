package com.hawk.webapi;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringServiceTools implements ApplicationContextAware{
	
	private  ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 this.applicationContext = applicationContext;
	}
	
	public ApiExecutor<?> getService(String beanName){
		return (ApiExecutor<?>)applicationContext.getBean(beanName);
	}

}
