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
import com.taw.pub.scene.request.DeleteConversationParam;
import com.taw.pub.scene.request.SearchConversationParam;
import com.taw.pub.scene.request.SendConverstaionParam;
import com.taw.pub.scene.response.SendConverstaionResp;
import com.taw.scene.domain.ConversationDomain;
import com.taw.scene.service.ConversationService;
import com.taw.user.auth.AuthThreadLocal;


@Controller
public class ConversationController {

	@Autowired
	private ConversationService conversationService;
	
	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/scene/conversation/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/scene/conversation/hello.do");
		return "success";
	}
	
	/**
	 * 发送会话消息
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/conversation/send.do", method = RequestMethod.POST)
	public void sendConversation(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SendConverstaionParam sendConverstaionParam = HttpRequestHandler.handle(request, SendConverstaionParam.class);
		sendConverstaionParam.setPostUserId(AuthThreadLocal.getUserId());/*发信人必须是登录用户*/
		Long id = conversationService.send(sendConverstaionParam);
		SendConverstaionResp sendConverstaionResp = new SendConverstaionResp();
		sendConverstaionResp.setId(id);
		HttpResponseHandler.handle(response, SuccessResponse.build(sendConverstaionResp));
	}
	
	@RequestMapping(value = "/scene/conversation/search.do", method = RequestMethod.POST)
	public void searchConversation(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchConversationParam searchConverstaionParam =  HttpRequestHandler.handle(request, SearchConversationParam.class);
		List<ConversationDomain> list = conversationService.search(searchConverstaionParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(list));
	}
	
	@RequestMapping(value = "/scene/conversation/delete.do", method = RequestMethod.POST)
	public void deleteConversation(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DeleteConversationParam deleteConversationParam =  HttpRequestHandler.handle(request, DeleteConversationParam.class);
		conversationService.delete(deleteConversationParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
}
