package com.hawk.web.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResponseHandler {
	
	private static Logger logger = LoggerFactory.getLogger(ResponseHandler.class);

	public static void handle(IResponse result,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String r = result.toJson();
		
		env(response).getWriter().print(r);

	}

	public static void handleBasicException(Exception exception,HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExceptionResponse r = new ExceptionResponse(exception);
		String output = r.toJson();
		env(response).getWriter().print(output);
	}
	
	private  static HttpServletResponse env(HttpServletResponse response){
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8"); 
		return response;
	}
	
	public static void printASAP(HttpServletResponse response,Exception ex) throws IOException{
		 ExceptionResponse r = new ExceptionResponse(ex);
		 String output = r.toJson();
		 logger.info("*****output = {}",output);
		 response = env(response);
		 PrintWriter writer = response.getWriter();  
         writer.write(output);  
         writer.flush();
	}
	
	public static void printASAP(HttpServletResponse response,String result) throws IOException{
		
		 response = env(response);
		 PrintWriter writer = response.getWriter();  
		 logger.info("*****output = {}",result);
        writer.write(result);  
        writer.flush();
	}

}
