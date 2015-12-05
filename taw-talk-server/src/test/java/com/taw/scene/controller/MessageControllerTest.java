package com.taw.scene.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.scene.request.SendMessageParam;

public class MessageControllerTest extends AbstractControllerTest{

	public MessageControllerTest() throws Exception {
		super();
	}
	
	
	@Test
	public void testSendMessage() throws Exception{
		
		String path = contextPath + "/scene/message/send.do";
		SendMessageParam sendMessageParam = new SendMessageParam();
		sendMessageParam.setContent("你好，我是010");
		sendMessageParam.setSceneId(1l);
		
		sendMessageParam.setSenderFpdId(65l);
//		sendMessageParam.setSenderId(senderId);
		sendMessageParam.setSenderNickname("80ad3379-2837-43d5-99f3-932876c6cea9");
		
		sendMessageParam.setReceiverFpdId(67l);
		sendMessageParam.setReceiverNickname("7c96f240-de5f-45fb-b09a-847d0fa78510");
		
		String content = JsonTools.toJsonString(sendMessageParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}

}
