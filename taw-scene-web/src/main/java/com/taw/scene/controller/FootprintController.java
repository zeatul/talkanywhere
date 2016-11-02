package com.taw.scene.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.QueryFootPrintParam;
import com.taw.pub.scene.response.ExistFootPrintResp;
import com.taw.pub.scene.response.FootPrintResp;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.FootPrintDomain;
import com.taw.scene.mapperex.FootPrintDetailExMapper;
import com.taw.scene.service.FootPrintService;
import com.taw.user.auth.AuthThreadLocal;

@Controller
public class FootprintController {
	
	
	@Autowired
	private FootPrintService footPrintService;
	
	@Autowired
	private FootPrintDetailExMapper footPrintDetailExMapper;
	
	
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
		queryFootPrintParam.setUserId(AuthThreadLocal.getUserId());
		List<FootPrintDomain> list = footPrintService.search(queryFootPrintParam);
		
		List<FootPrintResp> result = new ArrayList<FootPrintResp>(list.size());
		
		DomainTools.copy(list, result, FootPrintResp.class);
		
		HttpResponseHandler.handle(response, SuccessResponse.build(result));
	}
	
	@RequestMapping(value = "/scene/footprint/exist.do", method = RequestMethod.POST)
	public void exist(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ExistFootPrintParam existFootPrintParam = HttpRequestHandler.handle(request, ExistFootPrintParam.class); 
		CheckTools.check(existFootPrintParam);
		List<FootPrintDetailDomain> list =  footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains2(existFootPrintParam.getSceneId(), existFootPrintParam.getUserId());
		
		ExistFootPrintResp existFootPrintResp = new ExistFootPrintResp();
		
		if (list == null || list.size() == 0){
			existFootPrintResp.setExist("0");
		}else{
			existFootPrintResp.setExist("1");
		}
		
		HttpResponseHandler.handle(response, SuccessResponse.build(existFootPrintResp));
	}

	
}
