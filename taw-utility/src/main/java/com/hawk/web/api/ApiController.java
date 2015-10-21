package com.hawk.web.api;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.hawk.utility.JsonTools;


@Controller
public class ApiController {
	
	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	@Autowired
	private SpringServiceTools springServiceTools;
	
	
	@RequestMapping(value = "/api.do", method = {RequestMethod.POST,RequestMethod.GET})	
	public void exec(@RequestParam(value="j",required=true) String j ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		logger.info("j = {}",j);
		
		RequestParmeter requestParmeter = JsonTools.toObject(j, RequestParmeter.class);		
		/**
		 * 执行
		 */
		String beanName = requestParmeter.getAction();
		
		ApiExecutor<?> exec = springServiceTools.getService(beanName);
		
		IResponse result =  exec.execute(requestParmeter);
		
		
		
		ResponseHandler.handle(result,request, response);
	}
	
	
}
