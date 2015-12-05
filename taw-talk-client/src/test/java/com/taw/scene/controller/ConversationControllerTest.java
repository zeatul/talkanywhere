package com.taw.scene.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.scene.request.ComplexMessage;
import com.taw.pub.scene.request.SendConverstaionParam;

public class ConversationControllerTest extends AbstractControllerTest{

	public ConversationControllerTest() throws Exception {
		super();
	}
	
	@Test
	public void testSendConversation() throws Exception{
		String path = contextPath + "/scene/conversation/send.do";
		
		SendConverstaionParam sendConverstaionParam = new SendConverstaionParam();
		ComplexMessage message = new ComplexMessage();
		message.setText("hello, come on 6");
		message.setPics(null);
		sendConverstaionParam.setMessage(message);
		sendConverstaionParam.setPostUserFpdId(65l);
		sendConverstaionParam.setPostNickname("80ad3379-2837-43d5-99f3-932876c6cea9");
		sendConverstaionParam.setSceneId(1l);
		

		String content = JsonTools.toJsonString(sendConverstaionParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}

}
