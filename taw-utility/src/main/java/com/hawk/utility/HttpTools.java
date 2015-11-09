package com.hawk.utility;

import javax.servlet.http.HttpServletRequest;





public class HttpTools {
	
	
	public static class HttpClientInfo{
		public String getUserAgent() {
			return userAgent;
		}
		public void setUserAgent(String userAgent) {
			this.userAgent = userAgent;
		}
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

		private String userAgent;
		private String ipAddress;

	}
	
	/**
	 * 获取请求的客户端ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {      
	       String ip = request.getHeader("x-forwarded-for");      
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("Proxy-Client-IP");      
	      }      
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	          ip = request.getHeader("WL-Proxy-Client-IP");      
	       }      
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	      }      
	     return ip;      
	}  
	
	/**
	 * 获取请求的客户端信息
	 * @param request
	 * @return
	 */
	public static HttpClientInfo getHttpClientInfo(HttpServletRequest request){
		String userAgentString = request.getHeader("User-Agent");
		String ipAddress = getIpAddr(request);
		HttpClientInfo clientInfo = new HttpClientInfo();
		clientInfo.setIpAddress(ipAddress);
		clientInfo.setUserAgent(userAgentString);
		return clientInfo;
		
		
		
	}

}
