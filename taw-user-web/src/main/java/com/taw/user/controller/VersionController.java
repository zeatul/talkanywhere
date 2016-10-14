package com.taw.user.controller;

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
import com.taw.pub.version.request.AddVersionParam;
import com.taw.pub.version.request.DeleteVersionParam;
import com.taw.pub.version.request.QueryVersionParam;
import com.taw.user.service.VersionService;

@Controller
public class VersionController {
	
	@Autowired
	private VersionService versionService;

	@RequestMapping(value = "/version/query.do", method = RequestMethod.POST)
	public void query(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryVersionParam queryVersionParam = HttpRequestHandler.handle(request, QueryVersionParam.class);
		
		HttpResponseHandler.handle(response, SuccessResponse.build(versionService.queryVersion(queryVersionParam)));
	}
	
	@RequestMapping(value = "/version/create.do", method = RequestMethod.POST)
	public void create(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AddVersionParam addVersionParam = HttpRequestHandler.handle(request, AddVersionParam.class);
		versionService.addVersion(addVersionParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(SuccessResponse.SUCCESS_RESPONSE));
	}
	
	@RequestMapping(value = "/version/delete.do", method = RequestMethod.POST)
	public void delete(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeleteVersionParam deleteVersionParam = HttpRequestHandler.handle(request, DeleteVersionParam.class);	
		versionService.deleteVersion(deleteVersionParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(SuccessResponse.SUCCESS_RESPONSE));
	}
}
