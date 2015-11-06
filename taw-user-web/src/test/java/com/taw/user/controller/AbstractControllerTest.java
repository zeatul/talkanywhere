package com.taw.user.controller;

import com.hawk.utility.httpclient.HttpClientHelper;

public abstract class AbstractControllerTest {
	protected HttpClientHelper httpClientHelper;
	protected String contextPath = "/taw-user-web";
	
	public AbstractControllerTest() throws Exception{
		httpClientHelper = new HttpClientHelper();
		httpClientHelper.setHostname("localhost");
		httpClientHelper.setPort(8080);
		httpClientHelper.setScheme("http");
	}
	
	
}
