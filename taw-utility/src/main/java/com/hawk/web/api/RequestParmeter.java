package com.hawk.web.api;

import java.util.Map;


public class RequestParmeter{
	
	


	


	

	


	


	


	public String getOpenid() {
		return openid;
	}


	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getPassport() {
		return passport;
	}


	public void setPassport(String passport) {
		this.passport = passport;
	}


	public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}




	public Map<String,Object> getRequestParam() {
		return requestParam;
	}


	public void setRequestParam(Map<String,Object> requestParam) {
		this.requestParam = requestParam;
	}




	private String openid;
	private String passport;
	private String appid;
	private String action;
	private Map<String,Object> requestParam;
	
	
	

}
