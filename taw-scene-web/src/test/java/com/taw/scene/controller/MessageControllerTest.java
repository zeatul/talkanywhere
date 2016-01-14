package com.taw.scene.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.scene.request.DeleteMessageParam;
import com.taw.pub.scene.request.SearchMessageParam;
import com.taw.pub.scene.request.SendMessageParam;

public class MessageControllerTest extends AbstractControllerTest{

	public MessageControllerTest() throws Exception {
		super();
	}
	
	
//	@Test
	public void testSendMessage() throws Exception{
		
		String path = contextPath + "/scene/message/send.do";
		SendMessageParam sendMessageParam = new SendMessageParam();
		sendMessageParam.setMessage("你好，我是008");
		sendMessageParam.setSceneId(1l);
		
		sendMessageParam.setSenderFpdId(103l);

		sendMessageParam.setSenderNickname("80ad3379-2837-43d5-99f3-932876c6cea9");
		
		sendMessageParam.setReceiverFpdId(109l);
		sendMessageParam.setReceiverNickname("7c96f240-de5f-45fb-b09a-847d0fa78510");
		
		String content = JsonTools.toJsonString(sendMessageParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
//	@Test
	public void testSearchMessage() throws Exception{
		String path = contextPath + "/scene/message/search.do";
		SearchMessageParam param = new SearchMessageParam();
		param.setOffset(0);
		param.setLimit(2);
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
	@Test
	public void testDeleteMessage() throws Exception{
		String path = contextPath + "/scene/message/remove.do";
		DeleteMessageParam param = new DeleteMessageParam();
		List<Long> ids = new ArrayList<Long>();
		ids.add(1000L);
		ids.add(1020L);
		param.setIds(ids);
		
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}

}
