package com.taw.picture.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hawk.utility.DateTools;
import com.hawk.utility.httpclient.HttpClientHelper;
import com.taw.user.auth.TokenSecurityHelper;

public abstract class AbstractControllerTest {
	
	private final  static ClassPathXmlApplicationContext context ;
	
	static{
		String[] configPath = {"classpath*:com/taw/user/spring/applicationContext-user-service-bean.xml"
		};
		
		context = new ClassPathXmlApplicationContext(configPath);
	}
	
	private boolean TEST = false;

	
	protected HttpClientHelper httpClientHelper;
	
	protected String contextPath = null; 
	
	private String token = null;
	
	public AbstractControllerTest() throws Exception{
		
		httpClientHelper = new HttpClientHelper();
		
		if (TEST){
			contextPath = "/taw";  //测试环境
			httpClientHelper.setHostname("211.157.19.83");	//测试环境
			token = "febf20697fbb4b94bca60e0796388c0c"; //测试环境
			httpClientHelper.setPort(9080);//测试环境
		}else{
			contextPath = "/taw-picture-web"; //开发环境
			httpClientHelper.setHostname("localhost");	//开发环境
			httpClientHelper.setPort(8080);//开发环境
			token = "7c9006da62634727a8686b05631a0387";  //sender
//			token = "a8576d6e804d4916a753e03d8a68b2c0";  //receiver
		}
		
		
//		
		
		
		httpClientHelper.setScheme("http");
	}
	
	protected void printSend(String content){
		System.out.println("send="+content.trim());
	}
	
	protected void printResult(String result){
		System.out.println("result="+result.trim());
	}
	
	protected Map<String,String> genAuthMap(){
		TokenSecurityHelper tokenSecurityHelper = context.getBean(TokenSecurityHelper.class);
		
		String imei = "imei001";
		String t = tokenSecurityHelper.generate(token, DateTools.now().getTime(), imei);
		Map<String,String> map = new HashMap<String, String>();
		map.put("t", t);
		return map;
	}
	
}
