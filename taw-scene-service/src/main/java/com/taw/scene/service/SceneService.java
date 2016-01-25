package com.taw.scene.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.com.MapPoint;
import com.taw.pub.scene.enums.EnumLeaveType;
import com.taw.pub.scene.request.ChangeOnlineCountParam;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.LeaveSceneParam;
import com.taw.pub.scene.request.QuerySceneByNameParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.request.QuerySingleSceneParam;
import com.taw.pub.scene.response.EnterSceneResp;
import com.taw.pub.scene.response.SceneResp;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.FootPrintDomain;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.exception.FootPrintDetailNotExistsException;
import com.taw.scene.exception.FootPrintNotExistsException;
import com.taw.scene.exception.SceneNotExistsException;
import com.taw.scene.jms.Notification;
import com.taw.scene.jms.SceneEnterProducer;
import com.taw.scene.jms.SceneLeaveProducer;
import com.taw.scene.mapper.FootPrintDetailMapper;
import com.taw.scene.mapper.FootPrintMapper;
import com.taw.scene.mapper.SceneMapper;
import com.taw.scene.mapperex.FootPrintDetailExMapper;
import com.taw.scene.mapperex.SceneExMapper;
import com.taw.scene.service.SceneCacheHelper.SceneStatCount;

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
	
	@Autowired
	private SceneEnterProducer sceneEnterProducer;
	
	@Autowired
	private SceneLeaveProducer sceneLeaveProducer;
	
	@Autowired
	private FootPrintDetailExMapper footPrintDetailExMapper;
	
	
	
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
		MapPoint mapPoint = querySceneInRegionParam.getLeftBottom();
		java.math.BigDecimal minLng = mapPoint.getLng();
		java.math.BigDecimal maxLng = mapPoint.getLng();
		java.math.BigDecimal minLat = mapPoint.getLat();
		java.math.BigDecimal maxLat = mapPoint.getLat();
		
		mapPoint = querySceneInRegionParam.getRightTop();
		minLng = min(minLng,mapPoint.getLng());
		maxLng = max(maxLng,mapPoint.getLng());
		minLat = min(minLat,mapPoint.getLat());
		maxLat = max(maxLat,mapPoint.getLat());	
		
		return sceneExMapper.querySceneInRegion(minLng, maxLng, minLat, maxLat);
		
		
		
	}
	
	public List<SceneResp> query(QuerySceneByNameParam querySceneByNameParam) throws Exception{
		List<SceneDomain> sceneDomanList = sceneExMapper.querySceneByName("%"+querySceneByNameParam.getName()+"%");
		
		List<SceneResp> sceneRespsList = new ArrayList<SceneResp>(1);
		
		if (sceneDomanList == null)
			return sceneRespsList;
		
		for (SceneDomain sceneDomain : sceneDomanList){
			QuerySingleSceneParam querySingleSceneParam = new QuerySingleSceneParam();
			querySingleSceneParam.setSceneId(sceneDomain.getId());
			SceneResp sceneResp = querySingleScene(querySingleSceneParam);
			if (sceneResp!=null)
				sceneRespsList.add(sceneResp);
		}
		
		return sceneRespsList;
	}

	

	
	
	
	public SceneDomain loadSceneDomain(Long sceneId,boolean cached){
		if (sceneId == null)
			return null;
		
		SceneDomain sceneDomain = null;
		
		if (cached){
			sceneDomain = SceneCacheHelper.getCachedSceneDomain(sceneId);
		}
		
		if (sceneDomain == null)
			sceneDomain = sceneMapper.load(sceneId);
		
		if (cached && sceneDomain != null){
			SceneCacheHelper.cacheSceneDomain(sceneDomain);
		}
		
		return sceneDomain;
	}
	
	public SceneResp querySingleScene(QuerySingleSceneParam param) throws Exception{
		CheckTools.check(param);
		
		Long sceneId = param.getSceneId();
		
		SceneDomain sceneDomain =  loadSceneDomain(sceneId,true);
		if (sceneDomain == null)
			return null;
		
		SceneStatCount sceneStatCount = SceneCacheHelper.getCachedSceneStatCount(sceneId);
		
		SceneResp sceneResp = new SceneResp();
		
		DomainTools.copy(sceneDomain, sceneResp);
		
		if (sceneStatCount == null){
			sceneStatCount = new SceneStatCount();
			sceneStatCount.setEnterCount(sceneExMapper.queryEnterCount(sceneId));
			sceneStatCount.setOnlineCount(0);
			SceneCacheHelper.cacheSceneStatCount(sceneId, sceneStatCount);
		}
		
		sceneResp.setEnterCount(sceneStatCount.getEnterCount());
		sceneResp.setOnlineCount(sceneStatCount.getOnlineCount());
		
		return sceneResp;
	}
	
	private void changeEnterCount(Long sceneId,int step) throws Exception{
		SceneStatCount sceneStatCount = querySceneStatCount(sceneId);
		if (sceneStatCount != null){
			sceneStatCount.setEnterCount(sceneStatCount.getEnterCount()+step);
			SceneCacheHelper.cacheSceneStatCount(sceneId, sceneStatCount);
		}
	}
	
	
	/**
	 * 进入场景
	 * @param enterSceneParam
	 * @return 标识用户处在场景的 唯一标识ID
	 * @throws Exception
	 */
	
	public EnterSceneResp enterScene(EnterSceneParam enterSceneParam) throws Exception{
		
		CheckTools.check(enterSceneParam);
		
		Long sceneId = enterSceneParam.getSceneId();
		
		SceneDomain sceneDomain = loadSceneDomain(sceneId , true);
		
		if (sceneDomain == null)
			throw new SceneNotExistsException();
		
		Long userId = enterSceneParam.getUserId();
		
		/**
		 * 记录用户进入场景历史明细表
		 */
		FootPrintDetailDomain footPrintDetailDomain = null;
		List<FootPrintDetailDomain> list = footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains(enterSceneParam.getToken(), enterSceneParam.getSceneId(), enterSceneParam.getUserId());
		if (list!=null && list.size()>0){
			footPrintDetailDomain = list.get(0);
		}else{
			footPrintDetailDomain = new FootPrintDetailDomain();
			footPrintDetailDomain.setInTime(DateTools.now());
			footPrintDetailDomain.setNickname(nicknameService.genNickName());//TODO:去重
			footPrintDetailDomain.setSceneId(sceneId);
			footPrintDetailDomain.setSceneName(sceneDomain.getName());
			footPrintDetailDomain.setStaySpan(0);
			footPrintDetailDomain.setUserId(userId);
			footPrintDetailDomain.setToken(enterSceneParam.getToken());
			
			footPrintDetailDomain.setId(PkGenerator.genPk());		
			footPrintDetailMapper.insert(footPrintDetailDomain);
			
			/**
			 * 更新场景EnterCount
			 */
			changeEnterCount(sceneId,1);
			
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
				footPrintMapper.update(footPrintDomain);
			}
		}		
		
		
		
		/**
		 * 通知netty
		 */
		Notification notification = new Notification();
		notification.setSceneId(sceneId);
		notification.setUserId(userId);
		notification.setToken(enterSceneParam.getToken());
		sceneEnterProducer.send(notification);
		
		EnterSceneResp enterSceneResp = new EnterSceneResp();
		enterSceneResp.setFpdId(footPrintDetailDomain.getId());
		enterSceneResp.setNickName(footPrintDetailDomain.getNickname());
		return enterSceneResp;
	}
	
	/**
	 * 离开场景
	 * @param leaveSceneParam
	 * @throws Exception 
	 */
	
	public void leaveScene(LeaveSceneParam leaveSceneParam) throws Exception{
		CheckTools.check(leaveSceneParam);
		Long userId = leaveSceneParam.getUserId();
		Long sceneId = leaveSceneParam.getSceneId();
		Long fpdId = leaveSceneParam.getFpdId();
		
		FootPrintDetailDomain footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(fpdId, false);
		
		if (footPrintDetailDomain == null)
			throw new FootPrintDetailNotExistsException();
		
		if (!userId.equals(footPrintDetailDomain.getUserId()))
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
		footPrintDetailMapper.update(footPrintDetailDomain);
		 	
		/**
		 * 修改足迹历史
		 */
		FootPrintDomain footPrintDomain =  footPrintService.loadFootPrintDomain(userId, sceneId);
		if (footPrintDomain == null )
			throw new FootPrintNotExistsException();
		footPrintDomain.setLastLeaveTime(footPrintDetailDomain.getOutTime());
		footPrintDomain.setStaySpan(footPrintDomain.getStaySpan()+footPrintDetailDomain.getStaySpan());
		footPrintMapper.update(footPrintDomain);
		
		/**
		 * 更新场景EnterCount
		 */
		changeEnterCount(sceneId,-1);
		
		/**
		 * 通知netty
		 */
		Notification notification = new Notification();
		notification.setSceneId(sceneId);
		notification.setUserId(userId);
		notification.setToken(leaveSceneParam.getToken());
		sceneLeaveProducer.send(notification);
	}
	
	/**
	 * 查询用户当前进入了哪些场景
	 * @param token
	 * @return
	 */
	public List<Long> queryEnteredScene(String token){
		if (token == null)
			return null;
		return footPrintDetailExMapper.queryUnLeavedSceneId(token);
	}
	
	
	/**
	 * 根据前台通知，修改在场人数
	 * @param changeOnlineCountParam
	 * @throws Exception
	 */
	public void ChangeOnlineCount(ChangeOnlineCountParam changeOnlineCountParam) throws Exception{
		if (changeOnlineCountParam == null)
			return ;
		
		changeOnlineCount(changeOnlineCountParam.getInList(),1);
		
		changeOnlineCount(changeOnlineCountParam.getOutList(),-1);
		
	}
	
	private SceneStatCount querySceneStatCount(Long sceneId) throws Exception{
		SceneStatCount  sceneStatCount = SceneCacheHelper.getCachedSceneStatCount(sceneId);
		if (sceneStatCount == null){
			QuerySingleSceneParam querySingleSceneParam = new QuerySingleSceneParam();
			querySingleSceneParam.setSceneId(sceneId);
			SceneResp sceneResp = querySingleScene(querySingleSceneParam);	
			if (sceneResp==null)
				return null;
			
			sceneStatCount = new SceneStatCount();
			sceneStatCount.setEnterCount(sceneResp.getEnterCount());
			sceneStatCount.setOnlineCount(sceneResp.getOnlineCount());
			
		}
		
		return sceneStatCount;
	}
	
	private void changeOnlineCount(List<Long> scendIdList,int step) throws Exception{

		if (scendIdList != null){
			for (Long sceneId : scendIdList){
				 
				SceneStatCount sceneStatCount = querySceneStatCount(sceneId);
				
				if (sceneStatCount != null){
					sceneStatCount.setOnlineCount(sceneStatCount.getOnlineCount()+step);
					SceneCacheHelper.cacheSceneStatCount(sceneId, sceneStatCount);
				}
				
			}
		}
	}
}
