package com.taw.scene.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.scene.com.MapPoint;
import com.taw.pub.scene.request.ChangePresentParam;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.LeaveSceneParam;
import com.taw.pub.scene.request.QueryEnteredUsersOfSceneParam;
import com.taw.pub.scene.request.QuerySceneByNameParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.request.QuerySingleSceneParam;
import com.taw.pub.scene.request.QueryPresentUsersOfSceneParam;

public class SceneControllerTest extends AbstractControllerTest{

	public SceneControllerTest() throws Exception {
		super();
	}
	
//	@Test
	public void testSearch() throws Exception{
		String path = contextPath + "/scene/region/search.do";
		QuerySceneInRegionParam querySceneInRegionParam = new QuerySceneInRegionParam();
		
		querySceneInRegionParam.setLeftBottom(new MapPoint(new BigDecimal(120), new BigDecimal(32)));
		querySceneInRegionParam.setRightTop((new MapPoint(new BigDecimal(122), new BigDecimal(30))));
		querySceneInRegionParam.setBlock(1);
		
		
		String content = JsonTools.toJsonString(querySceneInRegionParam);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
//	@Test
	public void testChangePresent() throws Exception{
		String path = contextPath + "/scene/present/change.do";
		ChangePresentParam param = new ChangePresentParam();
		param.setInSceneId(10L);
		param.setOutSceneId(30L);
		String content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
//	@Test
	public void testIsPresentedInScene() throws Exception{
		String path = contextPath + "/scene/present/exist.do";
		ExistFootPrintParam param = new ExistFootPrintParam();
		param.setSceneId(10l);
		param.setUserId(76l);
		String content = JsonTools.toJsonString(param);
		printSend(content);
        String result = httpClientHelper.post(path, content, null);		
		printResult(result);
		
		
	}
	
//	@Test
	public void testQueryPresentUsersOfScene() throws Exception{
		String path = contextPath + "/scene/present/users.do";
		QueryPresentUsersOfSceneParam param = new QueryPresentUsersOfSceneParam();
		param.setSceneId(31l);
		String content = JsonTools.toJsonString(param);
		printSend(content);
        String result = httpClientHelper.post(path, content, null);		
		printResult(result);
	}
	
	
//	@Test
	public void testSearchByName() throws Exception{
		String path = contextPath + "/scene/name/search.do";
		
		QuerySceneByNameParam param = new QuerySceneByNameParam();
		param.setName("象屿都城");
		
		String content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
//	@Test
	public void testInfo() throws Exception{
		String path = contextPath + "/scene/info.do";
		
		QuerySingleSceneParam param = new QuerySingleSceneParam();
		param.setSceneId(10l);
		
		String content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
		
//		param.setSceneId(2l);
//		
//		content = JsonTools.toJsonString(param);
//		
//		printSend(content);
//		
//		result = httpClientHelper.post(path, content, null);
//		
//		printResult(result);
	}
	
//	@Test
	public void testEnterScene() throws Exception{
		String path = contextPath + "/scene/enter.do";
		EnterSceneParam enterSceneParam = new EnterSceneParam();
		enterSceneParam.setSceneId(10L);
		String content = JsonTools.toJsonString(enterSceneParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
	@Test
	public void testQueryEnteredUsersOfScene() throws Exception{
		String path = contextPath + "/scene/enter/users.do";
		QueryEnteredUsersOfSceneParam param = new QueryEnteredUsersOfSceneParam();
		param.setOffset(0);
		param.setLimit(10);
		param.setSceneId(30L);
		
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
//	@Test
	public void testLeaveScene() throws Exception{
		String path = contextPath + "/scene/leave.do";
		LeaveSceneParam param = new LeaveSceneParam();
		param.setFpdId(85L);
		param.setSceneId(10L);
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());		
		printResult(result);
	}

}
