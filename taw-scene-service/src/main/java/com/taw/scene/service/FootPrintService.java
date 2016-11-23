package com.taw.scene.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hawk.pub.mybatis.SqlParamHelper;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.QueryFootPrintParam;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.FootPrintDomain;
import com.taw.scene.mapper.FootPrintDetailMapper;
import com.taw.scene.mapper.FootPrintMapper;
import com.taw.scene.mapperex.FootPrintDetailExMapper;

@Service
public class FootPrintService {
	
	@Autowired
	private FootPrintMapper footPrintMapper;
	
	@Autowired
	private FootPrintDetailMapper footPrintDetailMapper;
	
	@Autowired
	private FootPrintDetailExMapper footPrintDetailExMapper;
	
	public List<FootPrintDomain> search(QueryFootPrintParam queryFootPrintParam) throws Exception{
		CheckTools.check(queryFootPrintParam);
		Map<String,Object> params = SqlParamHelper.generatePageParams("last_enter_time desc", queryFootPrintParam.getOffset(),queryFootPrintParam.getLimit());
		params.put("userId", queryFootPrintParam.getUserId());
		return footPrintMapper.loadDynamic(params);
	}
	

	public FootPrintDetailDomain loadFootPrintDetailDomain(Long id,boolean cached){
		if (id == null)
			return null;
		
		FootPrintDetailDomain footPrintDetailDomain = null;
		if (cached){
			/**
			 * TODO:读取缓存
			 */
		}
		
		footPrintDetailDomain = footPrintDetailMapper.load(id);
		
		if (cached && footPrintDetailDomain != null){
			/**
			 * TODO:异步写入缓存
			 */
		}
		
		return footPrintDetailDomain;
	}
	
	
	public FootPrintDomain loadFootPrintDomain(Long userId , Long sceneId){
		if (userId == null || sceneId == null)
			return null;		
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("sceneId", sceneId);
		
		List<FootPrintDomain> list = footPrintMapper.loadDynamic(params);
		
		if (list.size() == 0)
			return null;
		
		if (list.size() != 1)
			throw new RuntimeException("database data error");
		
		return list.get(0);		
		
	}
	
	public FootPrintDomain loadFootPrintDomain(Long id, boolean cached){ 
		if (id == null)
			return null;
		
		FootPrintDomain footPrintDomain = null;
		
		if (cached){
			/**
			 * TODO:读取缓存
			 */
		}
		
		footPrintDomain = footPrintMapper.load(id);
		
		if (cached && footPrintDomain != null){
			/**
			 * TODO:异步写入缓存
			 */
		}
		
		return footPrintDomain;
	}

	public boolean hasEnteredScene(ExistFootPrintParam existFootPrintParam) throws Exception{
		CheckTools.check(existFootPrintParam);
		List<FootPrintDetailDomain> list =  footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains2(existFootPrintParam.getSceneId(), existFootPrintParam.getUserId());
		
		if (list == null || list.size() == 0){
			return false;
		}
		
		return true;
	}
}
