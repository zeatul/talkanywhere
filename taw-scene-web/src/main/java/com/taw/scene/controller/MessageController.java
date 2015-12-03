package com.taw.scene.controller;

import java.io.IOException;
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
import com.taw.pub.scene.request.SendMessageParam;
import com.taw.pub.scene.response.SendMessageResp;
import com.taw.scene.service.MessageService;
import com.taw.user.auth.AuthThreadLocal;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
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
		Long id = messageService.send(sendMessageParam);
		SendMessageResp sendMessageResp = new SendMessageResp();
		sendMessageResp.setId(id);
		HttpResponseHandler.handle(response, SuccessResponse.build(sendMessageResp));
	}

}
