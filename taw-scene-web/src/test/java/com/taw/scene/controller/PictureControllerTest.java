package com.taw.scene.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.picture.enums.EnumThumbType;
import com.taw.pub.picture.request.AddCommentParam;
import com.taw.pub.picture.request.PictureInfoParam;
import com.taw.pub.picture.request.RemoveCommentParam;
import com.taw.pub.picture.request.SearchCommentParam;
import com.taw.pub.picture.request.ThumbPictureParam;

public class PictureControllerTest extends AbstractControllerTest{

	public PictureControllerTest() throws Exception {
		super();
		
	}
	
//	@Test
	public void testThumb() throws Exception{
		String path = contextPath + "/pic/thumb.do";
		
		ThumbPictureParam param = new ThumbPictureParam();
		
		param.setNickname("dual");
		param.setPicId(47l);
		param.setThumbType(EnumThumbType.UP.toString());
		
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
	
	public void testAddComment() throws Exception{
		
		String path = contextPath + "/pic/comment/add.do";
		
		AddCommentParam param = new AddCommentParam();
		param.setContent("真不错");
		param.setNickname("helloWorld");
		param.setPicId(47l);
		
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
	
	public void testSearchComment() throws Exception{
		String path = contextPath + "/pic/comment/search.do";
		SearchCommentParam param = new SearchCommentParam();
		param.setOffset(0);
		param.setLimit(5);
		param.setPicId(47l);
		
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
	
	public void testRemoveComment()throws Exception{
		String path = contextPath + "/pic/comment/remove.do";
		RemoveCommentParam param = new RemoveCommentParam();
		param.setCommentId(54l);
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
	@Test
	public void testPictureInfo() throws Exception{
		String path = contextPath + "/pic/info.do";
		PictureInfoParam param = new PictureInfoParam();
		param.setPicId(47l);
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}

}
