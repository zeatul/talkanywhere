package com.taw.user.controller;

import org.junit.Test;

import com.hawk.utility.httpclient.HttpClientHelper;

public class HttpsTest {
	
	@Test
	public void testHttps() throws Exception{
		try {
			HttpClientHelper httpClientHelper = new HttpClientHelper();
			
			httpClientHelper.setHostname("www.hawk.com");
			httpClientHelper.setPort(443);
			httpClientHelper.setScheme("https");
			
			String result = httpClientHelper.get("/taw/hello.do", null);
			
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
