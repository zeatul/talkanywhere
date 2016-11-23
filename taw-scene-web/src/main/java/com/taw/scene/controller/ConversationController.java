package com.taw.scene.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hawk.pub.enums.EnumBoolean;
import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.DomainTools;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.taw.pub.scene.request.DeleteConversationParam;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.SearchConversationParam;
import com.taw.pub.scene.request.SendConverstaionParam;
import com.taw.pub.scene.response.ConversationResp;
import com.taw.pub.scene.response.PicDescResp;
import com.taw.pub.scene.response.SendConverstaionResp;
import com.taw.scene.domain.ConversationDomain;
import com.taw.scene.service.ConversationService;
import com.taw.scene.service.SceneService;
import com.taw.user.auth.AuthThreadLocal;
import com.taw.user.domain.UserDomain;
import com.taw.user.service.UserService;


@Controller
public class ConversationController {

	@Autowired
	private ConversationService conversationService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private SceneService sceneService;
	
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
		sendConverstaionParam.setToken(AuthThreadLocal.getToken());
		
		SendConverstaionResp sendConverstaionResp = conversationService.send(sendConverstaionParam);
		HttpResponseHandler.handle(response, SuccessResponse.build(sendConverstaionResp));
	}
	
	@RequestMapping(value = "/scene/conversation/search.do", method = RequestMethod.POST)
	public void searchConversation(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchConversationParam searchConverstaionParam =  HttpRequestHandler.handle(request, SearchConversationParam.class);
		List<ConversationDomain> list = conversationService.search(searchConverstaionParam);
		List<ConversationResp> result = new ArrayList<ConversationResp>(list.size());
		for (ConversationDomain conversationDomain : list){
			ConversationResp conversationResp = new ConversationResp();
			DomainTools.copy(conversationDomain, conversationResp);
			
			UserDomain userDomain = userService.loadUser(conversationDomain.getPostUserId(), true);
			if (userDomain != null)
				conversationResp.setSex(userDomain.getSex());
			
			if (StringTools.isNotNullOrEmpty(conversationDomain.getPics())){
				conversationResp.setPicList(JsonTools.toArrayList(conversationDomain.getPics(),PicDescResp.class));
			}
			
			ExistFootPrintParam existFootPrintParam = new ExistFootPrintParam();
			existFootPrintParam.setSceneId(conversationDomain.getSceneId());
			existFootPrintParam.setUserId(conversationDomain.getPostUserId());
			boolean onScene = sceneService.isPresentedInScene(existFootPrintParam);
			conversationResp.setOnScene(onScene?EnumBoolean.TRUE.getValue():EnumBoolean.FALSE.getValue());
			result.add(conversationResp);
		}
		HttpResponseHandler.handle(response, SuccessResponse.build(result));
	}
	
	@RequestMapping(value = "/scene/conversation/remove.do", method = RequestMethod.POST)
	public void deleteConversation(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DeleteConversationParam deleteConversationParam =  HttpRequestHandler.handle(request, DeleteConversationParam.class);
		deleteConversationParam.setUserId(AuthThreadLocal.getUserId());
		conversationService.delete(deleteConversationParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
}
