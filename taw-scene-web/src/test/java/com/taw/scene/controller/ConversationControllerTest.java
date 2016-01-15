package com.taw.scene.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.utility.DateTools;
import com.hawk.utility.JsonTools;
import com.taw.pub.scene.request.DeleteConversationParam;
import com.taw.pub.scene.request.SearchConversationParam;
import com.taw.pub.scene.request.SendConverstaionParam;

public class ConversationControllerTest extends AbstractControllerTest{

	public ConversationControllerTest() throws Exception {
		super();
	}
	
	@Test
	public void testSendConversation() throws Exception{
		String path = contextPath + "/scene/conversation/send.do";
		
		SendConverstaionParam sendConverstaionParam = new SendConverstaionParam();
		String message = "hello, come on 6 with pictures";
		List<String> pics = new ArrayList<String>();
		pics.add("20160115141407c1a9d874ca894c958b3dafbccb3c4b54.jpg");
		pics.add("2016011515353837b5b970c2f1450a931312e13f0d6b5a.jpg");
		
		sendConverstaionParam.setPics(pics);
		sendConverstaionParam.setMessage(message); 
		sendConverstaionParam.setPostUserFpdId(27l);
		sendConverstaionParam.setPostNickname("ab246f68-6617-493b-aab5-305e2e03aacb");
		sendConverstaionParam.setSceneId(1l);
		

		String content = JsonTools.toJsonString(sendConverstaionParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
//	@Test
	public void testSearchConversation() throws Exception{
		String path = contextPath + "/scene/conversation/search.do";
		SearchConversationParam param = new SearchConversationParam();
		param.setOffset(0);
		param.setLimit(2);
		param.setSceneId(1L);
		param.setMinPostDate(DateTools.convert("2015-09-09 12:10:20.111", DateTools.DATETIME_SSS_PATTERN));
		
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
//	@Test
	public void testDeleteConverstaion()throws Exception{
		String path = contextPath + "/scene/conversation/remove.do";
		DeleteConversationParam param = new DeleteConversationParam();
		List<Long> ids = new ArrayList<Long>();
		ids.add(100L);
		ids.add(101L);
		
		param.setIds(ids);
		
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}

}
