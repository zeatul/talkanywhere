package com.taw.user.controller;

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
	
	protected HttpClientHelper httpClientHelper;
	protected String contextPath = "/taw-user-web"; //开发环境
	
//	protected String contextPath = "/taw";  //测试环境
	
	public AbstractControllerTest() throws Exception{
		httpClientHelper = new HttpClientHelper();
		httpClientHelper.setHostname("localhost");	//开发环境
//		httpClientHelper.setHostname("211.157.19.83");	//测试环境
		httpClientHelper.setPort(8080);
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
		String token = "cde91645dbed4e08b6585aa2a7d4790f";  //sender
//		String token = "a8576d6e804d4916a753e03d8a68b2c0";  //receiver
		String imei = "imei001";
		String t = tokenSecurityHelper.generate(token, DateTools.now().getTime(), imei);
		Map<String,String> map = new HashMap<String, String>();
		map.put("t", t);
		return map;
	}
	
}
