package com.taw.user.controller;

import com.hawk.utility.httpclient.HttpClientHelper;

public abstract class AbstractControllerTest {
	
//	http://localhost:8080/taw-user-web/user/create.do
	
	protected HttpClientHelper httpClientHelper;
//	protected String contextPath = "/taw-user-web"; //开发环境
	
	protected String contextPath = "/taw";  //测试环境
	
	public AbstractControllerTest() throws Exception{
		httpClientHelper = new HttpClientHelper();
//		httpClientHelper.setHostname("localhost");	//开发环境
		httpClientHelper.setHostname("211.157.19.70");	//测试环境
		httpClientHelper.setPort(8080);
		httpClientHelper.setScheme("http");
	}
	
	protected void printSend(String content){
		System.out.println("send="+content.trim());
	}
	
	protected void printResult(String result){
		System.out.println("result="+result.trim());
	}
	
}
