package com.taw.scene.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.utility.JsonTools;
import com.taw.pub.scene.com.MapPoint;
import com.taw.pub.scene.request.ChangeOnlineCountParam;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.LeaveSceneParam;
import com.taw.pub.scene.request.QuerySceneByNameParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.request.QuerySingleSceneParam;

public class SceneControllerTest extends AbstractControllerTest{

	public SceneControllerTest() throws Exception {
		super();
	}
	
//	@Test
	public void testSearch() throws Exception{
		String path = contextPath + "/scene/search.do";
		QuerySceneInRegionParam querySceneInRegionParam = new QuerySceneInRegionParam();
		
		querySceneInRegionParam.setLeftBottom(new MapPoint(new BigDecimal(-10000), new BigDecimal(-1000)));
		querySceneInRegionParam.setRightTop((new MapPoint(new BigDecimal(10000), new BigDecimal(1000))));
		
		String content = JsonTools.toJsonString(querySceneInRegionParam);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
	
	public void testChangeOnlineCount() throws Exception{
		String path = contextPath + "/scene/online/change.do";
		ChangeOnlineCountParam param = new ChangeOnlineCountParam();
		List<Long> inList = new ArrayList<Long>();
		inList.add(1l);
		param.setInList(inList);
		List<Long> outList = new ArrayList<Long>();
		outList.add(2l);
		param.setOutList(outList);
		String content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
	@Test
	public void testSearchByName() throws Exception{
		String path = contextPath + "/scene/name/search.do";
		
		QuerySceneByNameParam param = new QuerySceneByNameParam();
		param.setName("象屿都城");
		
		String content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
	
	public void testInfo() throws Exception{
		String path = contextPath + "/scene/info.do";
		
		QuerySingleSceneParam param = new QuerySingleSceneParam();
		param.setSceneId(1l);
		
		String content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		String result = httpClientHelper.post(path, content, null);
		
		printResult(result);
		
		param.setSceneId(2l);
		
		content = JsonTools.toJsonString(param);
		
		printSend(content);
		
		result = httpClientHelper.post(path, content, null);
		
		printResult(result);
	}
	
	
	public void testEnterScene() throws Exception{
		String path = contextPath + "/scene/enter.do";
		EnterSceneParam enterSceneParam = new EnterSceneParam();
		enterSceneParam.setSceneId(1L);
		String content = JsonTools.toJsonString(enterSceneParam);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());
		
		printResult(result);
	}
	
//	@Test
	public void testLeaveScene() throws Exception{
		String path = contextPath + "/scene/leave.do";
		LeaveSceneParam param = new LeaveSceneParam();
		param.setFpdId(94L);
		param.setSceneId(1L);
		String content = JsonTools.toJsonString(param);
		printSend(content);
		String result = httpClientHelper.post(path, content, genAuthMap());		
		printResult(result);
	}

}
