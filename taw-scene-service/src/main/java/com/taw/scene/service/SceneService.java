package com.taw.scene.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.com.MapPoint;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.response.SceneResp;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.mapper.SceneMapper;
import com.taw.scene.mapperex.SceneExMapper;

@Service
public class SceneService {
	
	@Autowired
	private SceneExMapper sceneExMapper;
	
	@Autowired
	private SceneMapper sceneMapper;
	
	private java.math.BigDecimal min(java.math.BigDecimal p1,java.math.BigDecimal p2 ){
		if (p1.compareTo(p2)==-1)
			return p1;
		else
			return p2;
	}
	
	private java.math.BigDecimal max(java.math.BigDecimal p1,java.math.BigDecimal p2 ){
		if (p1.compareTo(p2)==1)
			return p1;
		else
			return p2;
	}
	
	public SceneDomain query(long sceneId){
		return sceneMapper.loadScene(sceneId);
	}

	/**
	 * 查询指定区域范围内的场景
	 * @param querySceneInRegionParam
	 * @return
	 * @throws Exception
	 */
	public List<SceneDomain> query(QuerySceneInRegionParam querySceneInRegionParam) throws Exception{
		
		CheckTools.check(querySceneInRegionParam);
		/**
		 * 找出四个角的最大最小经纬度
		 */
		MapPoint mapPoint = querySceneInRegionParam.getTopLeft();
		java.math.BigDecimal minLng = mapPoint.getLng();
		java.math.BigDecimal maxLng = mapPoint.getLng();
		java.math.BigDecimal minLat = mapPoint.getLat();
		java.math.BigDecimal maxLat = mapPoint.getLat();
		
		mapPoint = querySceneInRegionParam.getTopRight();
		minLng = min(minLng,mapPoint.getLng());
		maxLng = max(maxLng,mapPoint.getLng());
		minLat = min(minLat,mapPoint.getLat());
		maxLat = max(maxLat,mapPoint.getLat());		
		
		mapPoint = querySceneInRegionParam.getBottomRight();
		minLng = min(minLng,mapPoint.getLng());
		maxLng = max(maxLng,mapPoint.getLng());
		minLat = min(minLat,mapPoint.getLat());
		maxLat = max(maxLat,mapPoint.getLat());	
		
		mapPoint = querySceneInRegionParam.getTopRight();
		minLng = min(minLng,mapPoint.getLng());
		maxLng = max(maxLng,mapPoint.getLng());
		minLat = min(minLat,mapPoint.getLat());
		maxLat = max(maxLat,mapPoint.getLat());	
		
		return sceneExMapper.querySceneInRegion(minLng, maxLng, minLat, maxLat);
		
		
		
	}

	
	/**
	 * 查询指定区域范围内的场景
	 * @param querySceneInRegionParam
	 * @return 给前端返回结果用
	 * @throws Exception
	 */
	public List<SceneResp> queryForWeb(QuerySceneInRegionParam querySceneInRegionParam) throws Exception{
		List<SceneResp> sceneRespList = new ArrayList<SceneResp>();
		
		List<SceneDomain> sources = query(querySceneInRegionParam);
		
		DomainTools.copy(sources, sceneRespList, SceneResp.class);
		
		return sceneRespList;
	}
	
	public void enterScene(EnterSceneParam enterSceneParam) throws Exception{
		
		CheckTools.check(enterSceneParam);
		
		
		
	}
	
}
