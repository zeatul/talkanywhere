package com.hawk.pub.web;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;



public class RequestHandler {
	
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

}
