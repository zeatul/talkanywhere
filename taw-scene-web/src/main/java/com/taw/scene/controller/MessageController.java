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
import com.taw.pub.scene.request.DeleteMessageParam;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.SearchMessageParam;
import com.taw.pub.scene.request.SendMessageParam;
import com.taw.pub.scene.response.MessageResp;
import com.taw.pub.scene.response.PicDescResp;
import com.taw.pub.scene.response.SendMessageResp;
import com.taw.scene.domain.MessageDomain;
import com.taw.scene.service.MessageService;
import com.taw.scene.service.SceneService;
import com.taw.user.auth.AuthThreadLocal;
import com.taw.user.domain.UserDomain;
import com.taw.user.service.UserService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
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
	@RequestMapping(value = "/scene/message/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/scene/message/hello.do");
		return "success";
	}
	
	/**
	 * 发送私人消息
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/message/send.do", method = RequestMethod.POST)
	public void sendMessage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SendMessageParam sendMessageParam = HttpRequestHandler.handle(request, SendMessageParam.class);
		sendMessageParam.setSenderId(AuthThreadLocal.getUserId());/*发信人必须是登录用户*/
		SendMessageResp sendMessageResp = messageService.send(sendMessageParam);		
		HttpResponseHandler.handle(response, SuccessResponse.build(sendMessageResp));
	}
	
	/**
	 * 列出我收到的消息
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/message/search.do", method = RequestMethod.POST)
	public void searchMessage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchMessageParam searchMessageParam = HttpRequestHandler.handle(request, SearchMessageParam.class);
		searchMessageParam.setUserId(AuthThreadLocal.getUserId());/*查询登录用户的消息*/
		List<MessageDomain> list = messageService.search(searchMessageParam);
		List<MessageResp> result = new ArrayList<MessageResp>(list.size());
		for (MessageDomain messageDomain : list){
			MessageResp messageResp = new MessageResp();
			DomainTools.copy(messageDomain, messageResp);
			
			UserDomain userDomain = userService.loadUser(messageDomain.getSenderId(), true);
			if (userDomain != null)
				messageResp.setSex(userDomain.getSex());
			
			
			ExistFootPrintParam existFootPrintParam = new ExistFootPrintParam();
			existFootPrintParam.setSceneId(messageDomain.getSceneId());
			existFootPrintParam.setUserId(messageDomain.getSenderId());
			boolean onScene = sceneService.isPresentedInScene(existFootPrintParam);
			messageResp.setOnScene(onScene?EnumBoolean.TRUE.getValue():EnumBoolean.FALSE.getValue());
			if (StringTools.isNotNullOrEmpty(messageDomain.getPics())){
				messageResp.setPicList(JsonTools.toArrayList(messageDomain.getPics(),PicDescResp.class));
			}
			result.add(messageResp);
		}
		
		HttpResponseHandler.handle(response, SuccessResponse.build(result));
	}
	
	/**
	 * 删除我收到的消息
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/scene/message/remove.do", method = RequestMethod.POST)
	public void deleteMessage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		DeleteMessageParam deleteMessageParam = HttpRequestHandler.handle(request, DeleteMessageParam.class);
		deleteMessageParam.setUserId(AuthThreadLocal.getUserId());/*删除登录用户的消息*/
		messageService.delete(deleteMessageParam);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}

}
