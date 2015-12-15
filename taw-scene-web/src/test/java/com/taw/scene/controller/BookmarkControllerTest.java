package com.taw.scene.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.scene.request.AddBookmarkParam;
import com.taw.pub.scene.request.QueryBookmarkParam;
import com.taw.pub.scene.request.RemoveBookmarkParam;

public class BookmarkControllerTest extends AbstractControllerTest {

	public BookmarkControllerTest() throws Exception {
		super();
	}
	
//	@Test
	public void testAddBookmark() throws Exception{
		String path = contextPath + "/scene/bookmark/add.do";
		AddBookmarkParam addBookmarkParam = new AddBookmarkParam();
		List<Long> list = new ArrayList<Long>();
		list.add(1l);
		list.add(2l);
		addBookmarkParam.setSceneIds(list);
		
		String content = JsonTools.toJsonString(addBookmarkParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
//	@Test
	public void testSearchBookmark() throws Exception{
		String path = contextPath + "/scene/bookmark/search.do";
		QueryBookmarkParam queryBookmarkParam = new QueryBookmarkParam();
		queryBookmarkParam.setLimit(2);
		queryBookmarkParam.setOffset(0);
		
		String content = JsonTools.toJsonString(queryBookmarkParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
	@Test
	public void testRemoveBookmark() throws Exception{
		String path = contextPath + "/scene/bookmark/remove.do";
		RemoveBookmarkParam param = new RemoveBookmarkParam();
		List<Long> list = new ArrayList<Long>();
		list.add(6l);
		list.add(12l);
		param.setSceneIds(list);
		
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
	

}
