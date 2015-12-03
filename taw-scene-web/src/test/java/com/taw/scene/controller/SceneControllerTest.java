package com.taw.scene.controller;

import java.math.BigDecimal;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.scene.com.MapPoint;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;

public class SceneControllerTest extends AbstractControllerTest{

	public SceneControllerTest() throws Exception {
		super();
	}
	
//	@Test
	public void testSearch() throws Exception{
		String path = contextPath + "/scene/search.do";
		QuerySceneInRegionParam querySceneInRegionParam = new QuerySceneInRegionParam();
		
		querySceneInRegionParam.setBottomLeft(new MapPoint(new BigDecimal(-10000), new BigDecimal(-1000)));
		querySceneInRegionParam.setBottomRight(new MapPoint(new BigDecimal(10000), new BigDecimal(-1000)));
		querySceneInRegionParam.setTopLeft(new MapPoint(new BigDecimal(-10000), new BigDecimal(1000)));
		querySceneInRegionParam.setTopRight((new MapPoint(new BigDecimal(10000), new BigDecimal(1000))));
		
		String content = JsonTools.toJsonString(querySceneInRegionParam);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
	@Test
	public void testEnterScene() throws Exception{
		String path = contextPath + "/scene/enter.do";
		EnterSceneParam enterSceneParam = new EnterSceneParam();
		enterSceneParam.setSceneId(1L);
		String content = JsonTools.toJsonString(enterSceneParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}

}
