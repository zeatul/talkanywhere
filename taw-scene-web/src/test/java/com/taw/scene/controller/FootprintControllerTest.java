package com.taw.scene.controller;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.scene.request.QueryFootPrintParam;

public class FootprintControllerTest extends AbstractControllerTest{

	public FootprintControllerTest() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testSearch() throws Exception{
		
		System.out.println(new Long(System.currentTimeMillis()).toString().length());
		
		String path = contextPath + "/scene/footprint/search.do";
		QueryFootPrintParam param = new QueryFootPrintParam();
		param.setOffset(0);
		param.setLimit(5);
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}

}
