package com.taw.user.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
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
import com.taw.pub.user.request.AddUserContactParam;
import com.taw.pub.user.request.RemoveUserContactParam;
import com.taw.user.auth.AuthThreadLocal;
import com.taw.user.service.UserContactService;


@Controller
public class UserContactController {
	
	@Autowired
	private UserContactService userContactService;

	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/user/contact/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/user/contact/hello.do");
		return "success";
	}
	
	@RequestMapping(value = "/user/contact/add.do", method = { RequestMethod.POST })
	public void add(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AddUserContactParam addUserContactParam = HttpRequestHandler.handle(request, AddUserContactParam.class);
		addUserContactParam.setUserId(AuthThreadLocal.getUserId());
		userContactService.add(addUserContactParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
	
	@RequestMapping(value = "/user/contact/remove.do", method = { RequestMethod.POST })
	public void remove(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RemoveUserContactParam removeUserContactParam = HttpRequestHandler.handle(request, RemoveUserContactParam.class);
		removeUserContactParam.setUserId(AuthThreadLocal.getUserId());
		userContactService.remove(removeUserContactParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
}
