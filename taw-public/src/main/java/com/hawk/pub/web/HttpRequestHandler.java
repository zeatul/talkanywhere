package com.hawk.pub.web;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.utility.HttpTools;
import com.hawk.utility.HttpTools.HttpClientInfo;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;



public class HttpRequestHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpRequestHandler.class);
	
	/**
	 * 解析输入的请求参数
	 * @param request
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public  static <T> T  handle(HttpServletRequest request, Class<T> clazz) throws Exception {

		String input = null;
		if (request.getMethod().equalsIgnoreCase("get")) {
			input = request.getParameter("query");
		} else {
			InputStream stream = request.getInputStream();
			input = IOUtils.toString(stream);
		}

		if (StringTools.isNullOrEmpty(input)) {
			return null;
		}

		T t = JsonTools.toObject(input, clazz);
	

		return t;
		
	}
	
	/**
	 * 返回http请求信息
	 * @param request
	 * @return
	 */
	public static HttpRequestInfo getRequestInfo(HttpServletRequest request) {

		final String key = "xxx-com.hakw.pub-http_request_info";

		HttpRequestInfo httpRequestInfo = (HttpRequestInfo) request.getAttribute(key);

		if (httpRequestInfo != null)
			return httpRequestInfo;
	
		String path = request.getServletPath();
		String method = request.getMethod();
		String ticket = request.getParameter("t");

		HttpClientInfo httpClientInfo = HttpTools.getHttpClientInfo(request);

		httpRequestInfo = new HttpRequestInfo();
		
		httpRequestInfo.setHttpClientInfo(httpClientInfo);
		httpRequestInfo.setMethod(method);
		httpRequestInfo.setPath(path);
		httpRequestInfo.setTicket(ticket);

		logger.info(JsonTools.toJsonString(httpClientInfo));

		request.setAttribute(key, httpRequestInfo);

		return httpRequestInfo;
	}

}
