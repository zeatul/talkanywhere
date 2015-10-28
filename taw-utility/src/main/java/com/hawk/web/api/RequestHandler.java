package com.hawk.web.api;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;



public class RequestHandler {

	private static Logger logger = LoggerFactory.getLogger(RequestHandler.class);

	public static String handle(HttpServletRequest request) throws IOException {
		String input = null;
		if (request.getMethod().equalsIgnoreCase("get")) {
			input = request.getParameter("query");
		} else {
			InputStream stream = request.getInputStream();
			input = IOUtils.toString(stream);
		}

		if (StringTools.isNullOrEmpty(input)) {
			logger.debug("the request's input = null");
			return null;
		}

		return input;
	}

	public static <T> T handle(HttpServletRequest request, Class<?> clazz) throws Exception {

		String input = handle(request);

		if (StringTools.isNullOrEmpty(input)) {
			return null;
		}

		T t = JsonTools.toObject(input, clazz);

		return t;
	}

}
