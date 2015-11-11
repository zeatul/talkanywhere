package com.taw.scene.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.taw.pub.scene.request.QueryFootPrintParam;
import com.taw.scene.domain.FootPrintDomain;
import com.taw.scene.service.FootPrintService;

@Controller
public class FootprintController {
	
	
	@Autowired
	private FootPrintService footPrintService;
	
	
	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/scene/footprint/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/user/footprint/hello.do");
		return "success";
	}
	
	
	@RequestMapping(value = "/scene/footprint/search.do", method = RequestMethod.POST)
	public void search(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		QueryFootPrintParam queryFootPrintParam = HttpRequestHandler.handle(request, QueryFootPrintParam.class); 
		List<FootPrintDomain> list = footPrintService.search(queryFootPrintParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(list));
	}

}
