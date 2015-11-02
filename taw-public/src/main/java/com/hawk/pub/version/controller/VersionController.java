package com.hawk.pub.version.controller;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.spring.FrameworkContext;
import com.hawk.pub.version.request.QueryVersionParam;
import com.hawk.pub.web.RequestHandler;
import com.hawk.pub.web.ResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.check.CheckTools;

@Controller
public class VersionController {
	
	

	@RequestMapping(value = "/version.do", method = {RequestMethod.POST,RequestMethod.GET})
	public void version(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QueryVersionParam queryVersionParam = RequestHandler.handle(request, QueryVersionParam.class);
		CheckTools.check(queryVersionParam);
		
		HashMap<?,?> map = FrameworkContext.getApplicationContext().getBean("versionMap", HashMap.class);
		
		ResponseHandler.handle(response, SuccessResponse.build(map.get(queryVersionParam.getModule())));
	}
}
