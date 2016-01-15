package com.taw.scene.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.picture.enums.EnumThumbType;
import com.taw.pub.picture.request.ThumbPictureParam;

public class PictureControllerTest extends AbstractControllerTest{

	public PictureControllerTest() throws Exception {
		super();
		
	}
	
	@Test
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

}
