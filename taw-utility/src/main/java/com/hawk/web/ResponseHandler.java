package com.hawk.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;



public class ResponseHandler {
	
	private  static HttpServletResponse env(HttpServletResponse response){
		response.setContentType("text/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		return response;
	}
	
	/**
	 * 直接向前台输出字符串
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public static void printASAP(HttpServletResponse response,String result) throws IOException{
		
		PrintWriter writer = env(response).getWriter();  
        writer.write(result);  
        writer.flush();
	}

}
