package com.hawk.pub.web;

import com.hawk.utility.HttpTools.HttpClientInfo;

public class HttpRequestInfo {
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public HttpClientInfo getHttpClientInfo() {
		return httpClientInfo;
	}
	public void setHttpClientInfo(HttpClientInfo httpClientInfo) {
		this.httpClientInfo = httpClientInfo;
	}
	
	/**
	 * http请求Path
	 */
	private String path;
	/**
	 * http请求方法
	 */
	private String method;
	/**
	 * http请求客户端信息
	 */
	private HttpClientInfo httpClientInfo;
	/**
	 * 票据，加密的前后台交互的认证信息
	 */
	private String ticket;

}
