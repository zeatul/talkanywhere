package com.taw.scene.controller;

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
	
	private boolean test = false;
	private boolean dev = true;

	
	protected HttpClientHelper httpClientHelper;
	
	protected String contextPath = null; 
	
	private String token = null;
	
	public AbstractControllerTest() throws Exception{
		
		httpClientHelper = new HttpClientHelper();
		
		if (test){
			contextPath = "/taw";  //测试环境
			httpClientHelper.setHostname("211.157.19.83");	//测试环境
			token = "febf20697fbb4b94bca60e0796388c0c"; //测试环境
			httpClientHelper.setPort(9080);//测试环境
		}else if (dev){
			contextPath = "/taw-scene-web"; //开发环境
			httpClientHelper.setHostname("localhost");	//开发环境
			httpClientHelper.setPort(8080);//开发环境
			token = "0fc5378f78394964b242cee1a07309c2";  //sender userId=76
//			token = "0658a7ba45fb499eabf3843e49420bab";  //receiver userId=78
		}else{
			throw new RuntimeException("Not Support profile");
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
