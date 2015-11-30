package com.taw.scene.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.com.MapPoint;
import com.taw.pub.scene.enums.EnumLeaveType;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.LeaveSceneParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.response.SceneResp;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.FootPrintDomain;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.exception.FootPrintDetailNotExistsException;
import com.taw.scene.exception.FootPrintNotExistsException;
import com.taw.scene.exception.SceneNotExistsException;
import com.taw.scene.mapper.FootPrintDetailMapper;
import com.taw.scene.mapper.FootPrintMapper;
import com.taw.scene.mapper.SceneMapper;
import com.taw.scene.mapperex.SceneExMapper;

@Service
public class SceneService {
	
	@Autowired
	private SceneExMapper sceneExMapper;
	
	@Autowired
	private SceneMapper sceneMapper;
	
	@Autowired
	private NicknameService nicknameService;
	
	@Autowired
	private FootPrintDetailMapper footPrintDetailMapper;
	
	@Autowired
	private FootPrintMapper footPrintMapper;
	
	@Autowired
	private FootPrintService footPrintService;
	
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
	 * @return 数据库记录
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
	 * @return 给前端返回结果用，包括基本信息和统计信息
	 * @throws Exception
	 */
	public List<SceneResp> queryForWeb(QuerySceneInRegionParam querySceneInRegionParam) throws Exception{
		List<SceneResp> sceneRespList = new ArrayList<SceneResp>();
		
		List<SceneDomain> sources = query(querySceneInRegionParam);
		
		DomainTools.copy(sources, sceneRespList, SceneResp.class);
		
		return sceneRespList;
	}
	
	
	
	public SceneDomain loadSceneDomain(Long sceneId,boolean cached){
		if (sceneId == null)
			return null;
		
		if (cached){
			/**
			 * TODO:读取缓存
			 */
		}
		
		SceneDomain sceneDomain = sceneMapper.loadScene(sceneId);
		
		if (cached && sceneDomain != null){
			/**
			 * TODO:加入缓存
			 */
		}
		
		return sceneDomain;
	}
	
	
	/**
	 * 进入场景
	 * @param enterSceneParam
	 * @return 标识用户处在场景的 唯一标识ID
	 * @throws Exception
	 */
	@Transactional
	public Long enterScene(EnterSceneParam enterSceneParam) throws Exception{
		
		CheckTools.check(enterSceneParam);
		
		Long sceneId = enterSceneParam.getSceneId();
		
		SceneDomain sceneDomain = loadSceneDomain(sceneId , true);
		
		if (sceneDomain == null)
			throw new SceneNotExistsException();
		
		Long userId = enterSceneParam.getUserId();
		
		/**
		 * 记录用户进入场景历史明细表
		 */
		FootPrintDetailDomain footPrintDetailDomain = new FootPrintDetailDomain();
		footPrintDetailDomain.setInTime(DateTools.now());
		footPrintDetailDomain.setNickname(nicknameService.genNickName());//TODO:去重
		footPrintDetailDomain.setSceneId(sceneId);
		footPrintDetailDomain.setSceneName(sceneDomain.getName());
		footPrintDetailDomain.setStaySpan(0);
		footPrintDetailDomain.setUserId(userId);
		
		footPrintDetailDomain.setId(PkGenerator.genPk());		
		footPrintDetailMapper.insert(footPrintDetailDomain);
		//TODO: 加入缓存
		
		
		/**
		 * 记录用户进入场景历史表
		 */
		FootPrintDomain footPrintDomain = footPrintService.loadFootPrintDomain(userId, sceneId);
		if (footPrintDomain == null ){
			footPrintDomain = new FootPrintDomain();
			footPrintDomain.setEnterTimes(1);
			footPrintDomain.setLastEnterTime(footPrintDetailDomain.getInTime());
			footPrintDomain.setSceneId(sceneId);
			footPrintDomain.setSceneName(sceneDomain.getName());
			footPrintDomain.setStaySpan(0);
			footPrintDomain.setUserId(userId);
			
			footPrintDomain.setId(PkGenerator.genPk());
			footPrintMapper.insert(footPrintDomain);
		}else{
			footPrintDomain.setLastEnterTime(footPrintDetailDomain.getInTime());
			footPrintDomain.setEnterTimes(footPrintDomain.getEnterTimes()+1);
			footPrintMapper.updateFootPrint(footPrintDomain);
		}
		
		/**
		 * TODO:更新场景在线人数
		 */
		
		return footPrintDetailDomain.getId();
	}
	
	/**
	 * 离开场景
	 * @param leaveSceneParam
	 * @throws Exception 
	 */
	@Transactional
	public void leaveScene(LeaveSceneParam leaveSceneParam) throws Exception{
		CheckTools.check(leaveSceneParam);
		Long userId = leaveSceneParam.getUserId();
		Long sceneId = leaveSceneParam.getSceneId();
		Long fpdId = leaveSceneParam.getFpdId();
		
		FootPrintDetailDomain footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(fpdId, false);
		
		if (footPrintDetailDomain == null)
			throw new FootPrintDetailNotExistsException();
		
		if (userId != footPrintDetailDomain.getUserId())
			throw new RuntimeException("UnMathed UserId");
		
		if (sceneId != footPrintDetailDomain.getSceneId())
			throw new RuntimeException("UnMathed SceneId");
		
		/**
		 * 修改足迹明细
		 */
		footPrintDetailDomain.setLeaveType(EnumLeaveType.SELF.toString());
		footPrintDetailDomain.setOutTime(DateTools.now());
		Long staySpan = (footPrintDetailDomain.getOutTime().getTime() - footPrintDetailDomain.getInTime().getTime())/1000;
		footPrintDetailDomain.setStaySpan(new Integer(staySpan.toString()));
		footPrintDetailMapper.updateFootPrintDetail(footPrintDetailDomain);
		 	
		/**
		 * 修改足迹历史
		 */
		FootPrintDomain footPrintDomain =  footPrintService.loadFootPrintDomain(userId, sceneId);
		if (footPrintDomain == null )
			throw new FootPrintNotExistsException();
		footPrintDomain.setLastLeaveTime(footPrintDetailDomain.getOutTime());
		footPrintDomain.setStaySpan(footPrintDomain.getStaySpan()+footPrintDetailDomain.getStaySpan());
		footPrintMapper.updateFootPrint(footPrintDomain);
		
		/**
		 * 修改场景在线人数
		 */
		
		/**
		 * 异步清除足迹明细缓存
		 */
	}
	
}
