package com.taw.scene.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.CollectionTools;
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
import com.taw.pub.scene.response.QuerySceneInRegionResp;
import com.taw.pub.scene.response.SceneResp;
import com.taw.scene.configure.SceneServiceConfigure;
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
import com.taw.user.service.LoginService;

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
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SceneServiceConfigure sceneServiceConfigure;

	private java.math.BigDecimal min(java.math.BigDecimal p1, java.math.BigDecimal p2) {
		if (p1.compareTo(p2) == -1)
			return p1;
		else
			return p2;
	}

	private java.math.BigDecimal max(java.math.BigDecimal p1, java.math.BigDecimal p2) {
		if (p1.compareTo(p2) == 1)
			return p1;
		else
			return p2;
	}

	/**
	 * 查询指定区域范围内的场景
	 * 
	 * @param querySceneInRegionParam
	 * @return 数据库记录
	 * @throws Exception
	 */
	public QuerySceneInRegionResp query(QuerySceneInRegionParam querySceneInRegionParam) throws Exception {

		CheckTools.check(querySceneInRegionParam);
		
		QuerySceneInRegionResp querySceneInRegionResp = new QuerySceneInRegionResp();
		
		/**
		 * 找出四个角的最大最小经纬度
		 */
		MapPoint mapPoint = querySceneInRegionParam.getLeftBottom();
		java.math.BigDecimal minLng = mapPoint.getLng();
		java.math.BigDecimal maxLng = mapPoint.getLng();
		java.math.BigDecimal minLat = mapPoint.getLat();
		java.math.BigDecimal maxLat = mapPoint.getLat();

		mapPoint = querySceneInRegionParam.getRightTop();
		minLng = min(minLng, mapPoint.getLng());
		maxLng = max(maxLng, mapPoint.getLng());
		minLat = min(minLat, mapPoint.getLat());
		maxLat = max(maxLat, mapPoint.getLat());
		
		int count = sceneExMapper.countSceneInRegion(minLng, maxLng, minLat, maxLat);
		
		querySceneInRegionResp.setSceneCount(0);
		querySceneInRegionResp.setFuzziedSceneCount(0);
		
		if (count > sceneServiceConfigure.getMaxSceneCountOfQueryOnRegion()){
			
		}else{

			List<SceneDomain> sceneDomainList = sceneExMapper.querySceneInRegion(minLng, maxLng, minLat, maxLat);
			
			querySceneInRegionResp.setSceneResps(convert(sceneDomainList));
			querySceneInRegionResp.setSceneCount(querySceneInRegionResp.getSceneResps().size());
		}

		
		return querySceneInRegionResp;
		
	}
	
	private List<SceneResp> convert(List<SceneDomain> sceneDomainList) throws Exception{
		if (CollectionTools.isNullOrEmpty(sceneDomainList)) {
			return new ArrayList<SceneResp>();
		}
		
		List<SceneResp> sceneRespsList = new ArrayList<SceneResp>(sceneDomainList.size());
		for (SceneDomain sceneDomain : sceneDomainList) {
			QuerySingleSceneParam querySingleSceneParam = new QuerySingleSceneParam();
			querySingleSceneParam.setSceneId(sceneDomain.getId());
			SceneResp sceneResp = querySingleScene(querySingleSceneParam);
			if (sceneResp != null)
				sceneRespsList.add(sceneResp);
		}
		
		return sceneRespsList;
	}

	public List<SceneResp> query(QuerySceneByNameParam querySceneByNameParam) throws Exception {
		List<SceneDomain> sceneDomainList = sceneExMapper.querySceneByName("%" + querySceneByNameParam.getName() + "%");

		return convert(sceneDomainList);
	}

	public SceneDomain loadSceneDomain(Long sceneId, boolean cached) {
		if (sceneId == null)
			return null;

		SceneDomain sceneDomain = null;

		if (cached) {
			sceneDomain = SceneCacheHelper.getCachedSceneDomain(sceneId);
		}

		if (sceneDomain == null)
			sceneDomain = sceneMapper.load(sceneId);

		if (cached && sceneDomain != null) {
			SceneCacheHelper.cacheSceneDomain(sceneDomain);
		}

		return sceneDomain;
	}

	public SceneResp querySingleScene(QuerySingleSceneParam param) throws Exception {
		CheckTools.check(param);

		Long sceneId = param.getSceneId();

		SceneDomain sceneDomain = loadSceneDomain(sceneId, true);
		if (sceneDomain == null)
			return null;

		SceneStatCount sceneStatCount = SceneCacheHelper.getCachedSceneStatCount(sceneId);

		SceneResp sceneResp = new SceneResp();

		DomainTools.copy(sceneDomain, sceneResp);

		if (sceneStatCount == null) {
			sceneStatCount = new SceneStatCount();
			sceneStatCount.setEnterCount(sceneExMapper.queryEnterCount(sceneId));
			sceneStatCount.setOnlineCount(0);
			SceneCacheHelper.cacheSceneStatCount(sceneId, sceneStatCount);
		}

		sceneResp.setEnterCount(sceneStatCount.getEnterCount());
		sceneResp.setOnlineCount(sceneStatCount.getOnlineCount());

		return sceneResp;
	}

	private void changeEnterCount(Long sceneId, int step) throws Exception {
		SceneStatCount sceneStatCount = querySceneStatCount(sceneId);
		if (sceneStatCount != null) {
			sceneStatCount.setEnterCount(sceneStatCount.getEnterCount() + step);
			SceneCacheHelper.cacheSceneStatCount(sceneId, sceneStatCount);
		}
	}

	/**
	 * 进入场景
	 * 
	 * @param enterSceneParam
	 * @return 标识用户处在场景的 唯一标识ID
	 * @throws Exception
	 */

	public EnterSceneResp enterScene(EnterSceneParam enterSceneParam) throws Exception {

		CheckTools.check(enterSceneParam);

		Long sceneId = enterSceneParam.getSceneId();

		SceneDomain sceneDomain = loadSceneDomain(sceneId, true);

		if (sceneDomain == null)
			throw new SceneNotExistsException();

		Long userId = enterSceneParam.getUserId();

		/**
		 * 记录用户进入场景历史明细表
		 */
		FootPrintDetailDomain footPrintDetailDomain = null;
		List<FootPrintDetailDomain> list = footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains(enterSceneParam.getToken(),
				enterSceneParam.getSceneId(), enterSceneParam.getUserId());
		if (list != null && list.size() > 0) {
			footPrintDetailDomain = list.get(0);
		} else {
			footPrintDetailDomain = new FootPrintDetailDomain();
			footPrintDetailDomain.setInTime(DateTools.now());
			footPrintDetailDomain.setNickname(nicknameService.genNickName());// TODO:去重
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
			changeEnterCount(sceneId, 1);

			/**
			 * 记录用户进入场景历史表
			 */
			FootPrintDomain footPrintDomain = footPrintService.loadFootPrintDomain(userId, sceneId);
			if (footPrintDomain == null) {
				footPrintDomain = new FootPrintDomain();
				footPrintDomain.setEnterTimes(1);
				footPrintDomain.setLastEnterTime(footPrintDetailDomain.getInTime());
				footPrintDomain.setSceneId(sceneId);
				footPrintDomain.setSceneName(sceneDomain.getName());
				footPrintDomain.setStaySpan(0);
				footPrintDomain.setUserId(userId);

				footPrintDomain.setId(PkGenerator.genPk());
				footPrintMapper.insert(footPrintDomain);
			} else {
				footPrintDomain.setLastEnterTime(footPrintDetailDomain.getInTime());
				footPrintDomain.setEnterTimes(footPrintDomain.getEnterTimes() + 1);
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
	 * 
	 * @param leaveSceneParam
	 * @throws Exception
	 */

	public void leaveScene(LeaveSceneParam leaveSceneParam) throws Exception {
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
		Long staySpan = (footPrintDetailDomain.getOutTime().getTime() - footPrintDetailDomain.getInTime().getTime()) / 1000;
		footPrintDetailDomain.setStaySpan(new Integer(staySpan.toString()));
		footPrintDetailMapper.update(footPrintDetailDomain);

		/**
		 * 修改足迹历史
		 */
		FootPrintDomain footPrintDomain = footPrintService.loadFootPrintDomain(userId, sceneId);
		if (footPrintDomain == null)
			throw new FootPrintNotExistsException();
		footPrintDomain.setLastLeaveTime(footPrintDetailDomain.getOutTime());
		footPrintDomain.setStaySpan(footPrintDomain.getStaySpan() + footPrintDetailDomain.getStaySpan());
		footPrintMapper.update(footPrintDomain);

		/**
		 * 更新场景EnterCount
		 */
		changeEnterCount(sceneId, -1);

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
	 * 
	 * @param token
	 * @return
	 */
	public List<Long> queryEnteredScene(String token) {
		if (token == null)
			return null;
		return footPrintDetailExMapper.queryUnLeavedSceneId(token);
	}

	/**
	 * 根据前台通知，修改在场人数
	 * 
	 * @param changeOnlineCountParam
	 * @throws Exception
	 */
	public void ChangeOnlineCount(ChangeOnlineCountParam changeOnlineCountParam) throws Exception {
		if (changeOnlineCountParam == null)
			return;
		
		Long userId = changeOnlineCountParam.getUserId();
		
		List<Long> scendIdList = changeOnlineCountParam.getInList();
		
		Set<Long> onlineSceneIdSet = SceneCacheHelper.getCachedOnlineScenes(userId);
		
		/**
		 * 用户物理进入场景
		 */
		if (scendIdList != null) {
			for (Long sceneId : scendIdList) {

				SceneStatCount sceneStatCount = querySceneStatCount(sceneId);

				if (sceneStatCount != null) {
					if (onlineSceneIdSet == null  ){
						//非登录用户只能直接修改统计值
						sceneStatCount.setOnlineCount(sceneStatCount.getOnlineCount() + 1);
					}else{
						if (!onlineSceneIdSet.contains(sceneId)){  //登录用户维护online状态和 场景online 统计值
							onlineSceneIdSet.add(sceneId);
							sceneStatCount.setOnlineCount(sceneStatCount.getOnlineCount() + 1);
						}
					}
					SceneCacheHelper.cacheSceneStatCount(sceneId, sceneStatCount);
				}

			}
		}

		/**
		 * 用户物理离开场景
		 */
		scendIdList = changeOnlineCountParam.getOutList();
		if (scendIdList != null) {
			for (Long sceneId : scendIdList) {

				SceneStatCount sceneStatCount = querySceneStatCount(sceneId);

				if (sceneStatCount != null) {
					if (onlineSceneIdSet == null  ){
						//非登录用户只能直接修改统计值
						sceneStatCount.setOnlineCount(sceneStatCount.getOnlineCount() - 1);
					}else{
						if (onlineSceneIdSet.contains(sceneId)){  //登录用户维护online状态和 场景online 统计值
							onlineSceneIdSet.remove(sceneId);
							sceneStatCount.setOnlineCount(sceneStatCount.getOnlineCount() - 1);
						}
					}
					SceneCacheHelper.cacheSceneStatCount(sceneId, sceneStatCount);
				}

			}
		}
		
		
		/**
		 * 缓存用户的online 场景 id 集合
		 */
		
		if (onlineSceneIdSet!=null){
			SceneCacheHelper.cacheOnineScenes(userId, onlineSceneIdSet);
		}
	}

	private SceneStatCount querySceneStatCount(Long sceneId) throws Exception {
		SceneStatCount sceneStatCount = SceneCacheHelper.getCachedSceneStatCount(sceneId);
		if (sceneStatCount == null) {
			QuerySingleSceneParam querySingleSceneParam = new QuerySingleSceneParam();
			querySingleSceneParam.setSceneId(sceneId);
			SceneResp sceneResp = querySingleScene(querySingleSceneParam);
			if (sceneResp == null)
				return null;

			sceneStatCount = new SceneStatCount();
			sceneStatCount.setEnterCount(sceneResp.getEnterCount());
			sceneStatCount.setOnlineCount(sceneResp.getOnlineCount());
			
			SceneCacheHelper.cacheSceneStatCount(sceneId, sceneStatCount);
		}

		return sceneStatCount;
	}

	
}
