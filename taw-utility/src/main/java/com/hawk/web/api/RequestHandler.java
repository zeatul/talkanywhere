package com.hawk.web.api;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.utility.JsonTools;



public class RequestHandler {

	private static Logger logger = LoggerFactory.getLogger(RequestHandler.class);

	public static String handle(HttpServletRequest request) throws IOException {
		InputStream stream = request.getInputStream();
		String input = IOUtils.toString(stream, "utf-8");

		if (input == null || input.trim().length() == 0) {
			logger.debug("the request's input = null");
		}

		return input;
	}

	public static <T> T handle(HttpServletRequest request, Class<?> clazz) throws Exception {

		String input = handle(request);

		if (input == null || input.trim().length() == 0)
			return null;

		T t = JsonTools.toObject(input, clazz);

		return t;
	}

}
