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

import com.hawk.pub.web.HttpRequestHandler;
import com.hawk.pub.web.HttpResponseHandler;
import com.hawk.pub.web.SuccessResponse;
import com.hawk.utility.DomainTools;
import com.taw.pub.scene.request.QueryFootPrintParam;
import com.taw.pub.scene.response.FootPrintResp;
import com.taw.scene.domain.FootPrintDomain;
import com.taw.scene.jms.Notification;
import com.taw.scene.jms.SceneConversationProducer;
import com.taw.scene.jms.SceneMessageProducer;
import com.taw.user.auth.AuthThreadLocal;

@Controller
public class JmsTestController {
	
	@Autowired
	private SceneMessageProducer sceneMessageProducer;
	
	@Autowired
	private SceneConversationProducer sceneConversationProducer;

	/**
	 * hello 测试用
	 * @param locale
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/jms/test/hello.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String helloWorld(Locale locale, Model model) throws IOException {
		model.addAttribute("msg", "/jms/test/hello.do");
		return "success";
	}
	
	@RequestMapping(value = "/jms/test/message/send.do", method = RequestMethod.GET)
	public void sendMessage(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Notification notification = new Notification();
		notification.setMsg("SendMessage:"+ System.currentTimeMillis());
		
		sceneMessageProducer.send(notification);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
	
	@RequestMapping(value = "/jms/test/conversation/send.do", method = RequestMethod.GET)
	public void sendConversation(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Notification notification = new Notification();
		notification.setMsg("SendConversation:"+ System.currentTimeMillis());
		sceneConversationProducer.send(notification);
		HttpResponseHandler.handle(response, SuccessResponse.SUCCESS_RESPONSE);
	}
}
