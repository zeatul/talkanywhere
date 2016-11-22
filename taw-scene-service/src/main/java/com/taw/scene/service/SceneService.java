package com.taw.scene.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.CollectionTools;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.com.MapPoint;
import com.taw.pub.scene.com.UserOnlineScene;
import com.taw.pub.scene.enums.EnumLeaveType;
import com.taw.pub.scene.request.ChangeOnlineCountParam;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.LeaveSceneParam;
import com.taw.pub.scene.request.QueryBookmarkParam;
import com.taw.pub.scene.request.QuerySceneByNameParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.request.QuerySingleSceneParam;
import com.taw.pub.scene.request.QueryUsersOnSceneParam;
import com.taw.pub.scene.request.QueryUsersOnlineSceneParam;
import com.taw.pub.scene.response.EnterSceneResp;
import com.taw.pub.scene.response.FuzziedSceneResp;
import com.taw.pub.scene.response.QuerySceneInRegionResp;
import com.taw.pub.scene.response.SceneResp;
import com.taw.scene.domain.BookmarkDomain;
import com.taw.scene.domain.FootPrintDetailDomain;
import com.taw.scene.domain.FootPrintDomain;
import com.taw.scene.domain.SceneDomain;
import com.taw.scene.domainex.FuzziedSceneDomain;
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
import com.taw.scene.service.SceneCacheHelper.UserOnScene;
import com.taw.user.auth.AuthThreadLocal;
import com.taw.user.domain.UserDomain;
import com.taw.user.service.UserService;

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
	private BookmarkService bookmarkService;
	
	@Autowired
	private UserService userService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

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

		querySceneInRegionResp.setSceneCount(0);
		querySceneInRegionResp.setFuzziedSceneCount(0);

		int level = querySceneInRegionParam.getLevel();
		if (level >= 19 /* >=19,不需要模糊化 */) {
			List<SceneDomain> sceneDomainList = sceneExMapper.querySceneInRegion(minLng, maxLng, minLat, maxLat);
			querySceneInRegionResp.setSceneResps(convert(sceneDomainList));
			querySceneInRegionResp.setSceneCount(querySceneInRegionResp.getSceneResps().size());
		} else if (level <= 10 /* <=10 ,按照城市分组模糊化 */) {
			List<FuzziedSceneDomain> fuzziedSceneDomainList = sceneExMapper.querySceneGroupByCity(minLng, maxLng, minLat, maxLat);
			List<FuzziedSceneResp> fuzziedSceneRespsList = convert2(fuzziedSceneDomainList);
			querySceneInRegionResp.setFuzziedSceneCount(fuzziedSceneRespsList.size());
			querySceneInRegionResp.setFuzziedSceneResps(fuzziedSceneRespsList);
		} else {

			int limitPerBlock = 500;

			int block = querySceneInRegionParam.getBlock();

			int count = sceneExMapper.countSceneInRegion(minLng, maxLng, minLat, maxLat);

			logger.info("count1={}", count);

			/**
			 * if ( 10<blk<19) 计算 density @density = Round( $DensityBase *
			 * power(2, (20.0 - @level)) \ $DensityFactor, 5)
			 */
			double densityBase = 0.005;
			double densityFactor = 100;
			BigDecimal density = new BigDecimal(new DecimalFormat("#.00000").format((densityBase * Math.pow(2, 20 - level)) / densityFactor));

			/**
			 * step1
			 */
			List<FuzziedSceneDomain> fuzziedSceneDomainList = sceneExMapper.queryFuzziedScene(minLng, maxLng, minLat, maxLat, density);

			/**
			 * Step 2 计算Step 1 中GroupSize列的AVG，及SUM
			 */
			long sum = 0;
			long rowCount = 0;
			for (FuzziedSceneDomain fuzziedSceneDomain : fuzziedSceneDomainList) {
				sum = sum + fuzziedSceneDomain.getCount();
				rowCount++;
			}

			/**
			 * 如果AVG为1，或SUM < $LimitPerBlock * @block （其中$LimitPerBlock是预设常数，
			 * 现定为500，@block是客户端提交的数据）， 直接返回真实场景数据，无需执行任何模糊集群化。返回的数据应携带场景热度级别值
			 */
			if (sum == rowCount || sum <= limitPerBlock * block) {
				List<SceneDomain> sceneDomainList = sceneExMapper.querySceneInRegion(minLng, maxLng, minLat, maxLat);
				querySceneInRegionResp.setSceneResps(convert(sceneDomainList));
				querySceneInRegionResp.setSceneCount(querySceneInRegionResp.getSceneResps().size());
			} else
			/**
			 * 计算 @minGroupSize = Round(@avg * 2.0, 0)
			 * （其中@avg为Step1中计算出的GroupSize平均值） 排除Step 1结果中GroupSize
			 * < @minGroupSize的所有行，并直接予以返回。无需携带场景热度值
			 */
			{
				long minGroupSize = Math.round(((sum * 1.0) / rowCount) * 2.0);
				List<FuzziedSceneDomain> result = new ArrayList<FuzziedSceneDomain>();
				for (FuzziedSceneDomain fuzziedSceneDomain : fuzziedSceneDomainList) {
					if (fuzziedSceneDomain.getCount() >= minGroupSize) {
						result.add(fuzziedSceneDomain);
					}
				}

				List<FuzziedSceneResp> fuzziedSceneRespsList = convert2(result);
				querySceneInRegionResp.setFuzziedSceneCount(fuzziedSceneRespsList.size());
				querySceneInRegionResp.setFuzziedSceneResps(fuzziedSceneRespsList);
			}

		}

		return querySceneInRegionResp;

	}

	public Map<Long, BookmarkDomain> bookedSceneIdMap(Long userId) throws Exception {
		Map<Long, BookmarkDomain> bookedSceneIdMap = new HashMap<Long, BookmarkDomain>();
		if (userId != null) {
			QueryBookmarkParam queryBookmarkParam = new QueryBookmarkParam();
			queryBookmarkParam.setLimit(10000);
			queryBookmarkParam.setOffset(0);
			queryBookmarkParam.setUserId(userId);
			List<BookmarkDomain> bookedSceneList = bookmarkService.search(queryBookmarkParam);

			if (bookedSceneList != null) {
				for (BookmarkDomain bookmarkDomain : bookedSceneList) {
					bookedSceneIdMap.put(bookmarkDomain.getSceneId(), bookmarkDomain);
				}
			}
		}
		return bookedSceneIdMap;
	}

	private List<SceneResp> convert(List<SceneDomain> sceneDomainList) throws Exception {

		Long userId = AuthThreadLocal.getUserId();

		if (CollectionTools.isNullOrEmpty(sceneDomainList)) {
			return new ArrayList<SceneResp>();
		}

		List<SceneResp> sceneRespsList = new ArrayList<SceneResp>(sceneDomainList.size());

		Map<Long, BookmarkDomain> bookedSceneIdMap = bookedSceneIdMap(userId);

		for (SceneDomain sceneDomain : sceneDomainList) {
			QuerySingleSceneParam querySingleSceneParam = new QuerySingleSceneParam();
			querySingleSceneParam.setSceneId(sceneDomain.getId());
			SceneResp sceneResp = querySingleScene(querySingleSceneParam, bookedSceneIdMap);
			if (sceneResp != null) {
				sceneRespsList.add(sceneResp);
			}
		}

		return sceneRespsList;
	}

	private List<FuzziedSceneResp> convert2(List<FuzziedSceneDomain> fuzziedSceneDomainList) {
		if (CollectionTools.isNullOrEmpty(fuzziedSceneDomainList)) {
			return new ArrayList<FuzziedSceneResp>();
		}

		List<FuzziedSceneResp> fuzziedSceneRespList = new ArrayList<FuzziedSceneResp>();

		DomainTools.copy(fuzziedSceneDomainList, fuzziedSceneRespList, FuzziedSceneResp.class);

		return fuzziedSceneRespList;
	}

	public List<SceneResp> query(QuerySceneByNameParam querySceneByNameParam) throws Exception {

		List<SceneDomain> sceneDomainList = sceneExMapper.querySceneByName("%" + querySceneByNameParam.getName() + "%", querySceneByNameParam.getOffset(),
				querySceneByNameParam.getLimit());

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

	public SceneResp querySingleScene(QuerySingleSceneParam param,Map<Long, BookmarkDomain> bookedSceneIdMap) throws Exception {
		CheckTools.check(param);

		Long sceneId = param.getSceneId();

		SceneDomain sceneDomain = loadSceneDomain(sceneId, true);
		if (sceneDomain == null)
			return null;

		SceneResp sceneResp = new SceneResp();

		DomainTools.copy(sceneDomain, sceneResp);

		sceneResp.setEnterCount(sceneExMapper.queryEnterCount(sceneId));
		sceneResp.setOnlineCount(SceneCacheHelper.getCachedSceneOnlineUsers(sceneId).size());

		if (bookedSceneIdMap != null && bookedSceneIdMap.get(sceneResp.getId()) != null)
			sceneResp.setFavored(1);

		return sceneResp;
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
		List<FootPrintDetailDomain> list = footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains2(enterSceneParam.getSceneId(),enterSceneParam.getUserId());
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
			UserDomain userDomain = userService.loadUser(userId, true);
			footPrintDetailDomain.setSex(userDomain==null?null:userDomain.getSex());	
			footPrintDetailDomain.setId(PkGenerator.genPk());
			footPrintDetailMapper.insert(footPrintDetailDomain);

			

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
		String token = leaveSceneParam.getToken();

		FootPrintDetailDomain footPrintDetailDomain = footPrintService.loadFootPrintDetailDomain(fpdId, false);

		if (footPrintDetailDomain == null)
			throw new FootPrintDetailNotExistsException();

		if (!userId.equals(footPrintDetailDomain.getUserId()))
			throw new RuntimeException("UnMathed UserId");

		if (!sceneId.equals (footPrintDetailDomain.getSceneId()))
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
		
		
		
//		/**
//		 * 修改场景的物理在场的用户集合
//		 */		
//		SceneCacheHelper.removeCachedSceneOnlineUser(sceneId, userId, token);
//		
//		/**
//		 * 修改用户物理在场的场景集合
//		 */
//		String onlineScenesOfUserItem= SceneCacheHelper.buildOnlineScenesOfUserItem(token,sceneId);
//		Set<String> onlineSceneIdSet = SceneCacheHelper.getCachedOnlineScenesOfUser(userId);
//		onlineSceneIdSet.remove(onlineScenesOfUserItem);
//		SceneCacheHelper.cacheOnineScenesOfUser(userId, onlineSceneIdSet);
		

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

	public UserOnScene queryNickname(String token, Long sceneId, Long userId) {

		if (StringTools.isNullOrEmpty(token) || sceneId == null || userId == null)
			return null;

		UserOnScene userOnScene = SceneCacheHelper.getCachedNickname(token, sceneId);

		if (userOnScene != null)
			return userOnScene;

		List<FootPrintDetailDomain> list = footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains(token, sceneId, userId);
		if (list == null || list.size() == 0)
			return null;

		String nickname = list.get(0).getNickname();
		long fpdId = list.get(0).getId();
		String sex = list.get(0).getSex();

		userOnScene = new UserOnScene();
		userOnScene.setNickname(nickname);
		userOnScene.setFpdId(fpdId);
		userOnScene.setSex(sex);
		
		/**
		 * 用户如果没有进入场景，则没有昵称，但是需要查询性别
		 */
		if(sex == null){
			UserDomain userDomain = userService.loadUser(userId, true);
			if (userDomain !=null){
				userOnScene.setSex(sex);
			}
		}

		SceneCacheHelper.cacheNickname(token, sceneId, userOnScene);

		return userOnScene;
	}

	/**
	 * 查询指定场景的全部物理在线用户
	 * 
	 * @param sceneId
	 * @return
	 * @throws Exception
	 */
	public List<UserOnlineScene> queryUsersOnlineScene(QueryUsersOnlineSceneParam queryUsersOnlineSceneParam) throws Exception {

		CheckTools.check(queryUsersOnlineSceneParam);

		Long sceneId = queryUsersOnlineSceneParam.getSceneId();

		List<UserOnlineScene> list = SceneCacheHelper.getCachedSceneOnlineUsers(sceneId);
		return list;
	}
	
	public List<UserOnlineScene> queryUsersOnScene(QueryUsersOnSceneParam queryUsersOnSceneParam) throws Exception {

		CheckTools.check(queryUsersOnSceneParam);

		Long sceneId = queryUsersOnSceneParam.getSceneId();
		Integer offset = queryUsersOnSceneParam.getOffset();
		Integer limit = queryUsersOnSceneParam.getLimit();
		
		
		List<FootPrintDetailDomain> footPrintDetailDomainsList =  footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains3(sceneId, offset, limit);

		List<UserOnlineScene> list = new ArrayList<UserOnlineScene>();
		for (FootPrintDetailDomain footPrintDetailDomain : footPrintDetailDomainsList) {
			UserOnlineScene userOnlineScene = new UserOnlineScene();
			userOnlineScene.setToken(null);
			userOnlineScene.setNickname(footPrintDetailDomain.getNickname());
			userOnlineScene.setFpdId(footPrintDetailDomain.getId());
			userOnlineScene.setUserId(footPrintDetailDomain.getUserId());
			userOnlineScene.setSex(footPrintDetailDomain.getSex());
		}
		return list;
	}

	/**
	 * 根据前台通知，修改在场人数
	 * 
	 * @param changeOnlineCountParam
	 * @throws Exception
	 */
	public void ChangeOnlineCount(ChangeOnlineCountParam changeOnlineCountParam) throws Exception {
		logger.info("++++ChangeOnlineCount+++,changeOnlineCountParam={}",JsonTools.toJsonString(changeOnlineCountParam));		

		Long userId = changeOnlineCountParam.getUserId();
		String token = changeOnlineCountParam.getToken();
		List<Long> inList = changeOnlineCountParam.getInList();
		

		Set<String> onlineSceneIdSet = SceneCacheHelper.getCachedOnlineScenesOfUser(userId);
		
		String sex = null;
		
		String nickname = null;
		
		Long fpdId = null;

		/**
		 * 用户物理进入场景
		 */
		if (inList != null && inList.size() >0) {
			for (Long sceneId : inList) {
				
				/**
				 * 查询可能的用户昵称和性别
				 */
				UserOnScene userOnScene = queryNickname(token, sceneId, userId);
				if (userOnScene!=null){
					sex = userOnScene.getSex();
					nickname = userOnScene.getNickname();
					fpdId = userOnScene.getFpdId();
				}
				
				String onlineScenesOfUserItem= SceneCacheHelper.buildOnlineScenesOfUserItem(token,sceneId);
				if (!onlineSceneIdSet.contains(onlineScenesOfUserItem)){
					/**
					 * 修改用户物理在场的场景集合
					 */
					onlineSceneIdSet.add(onlineScenesOfUserItem);
					SceneCacheHelper.cacheOnineScenesOfUser(userId, onlineSceneIdSet);
					/**
					 * 修改场景的物理在场的用户集合
					 */
					SceneCacheHelper.cacheSceneOnlineUser(sceneId, userId, token, nickname, sex,fpdId);
					
					/**
					 * 记录日志
					 */
					logger.info("++++ChangeOnlineCount+++, add sceneId={},userId={},token={},nickname={},sex={}",sceneId,userId,token,nickname,sex);
				}else{
					logger.info("++++ChangeOnlineCount+++, user is online ,add none,sceneId={},userId={},token={},nickname={},sex={}",sceneId,userId,token,nickname,sex);
				}

			}
		}

		/**
		 * 用户物理离开场景
		 */
		List<Long> outList = changeOnlineCountParam.getOutList();
		if (outList != null && outList.size() > 0) {
			for (Long sceneId : outList) {
				String onlineScenesOfUserItem= SceneCacheHelper.buildOnlineScenesOfUserItem(token,sceneId);
				if (!onlineSceneIdSet.contains(onlineScenesOfUserItem)){
					logger.info("++++ChangeOnlineCount+++, user is not online ,remove none,sceneId={},userId={},token={},nickname={},sex={}",sceneId,userId,token,nickname,sex);
				}else{
					/**
					 * 修改用户物理在场的场景集合
					 */
					onlineSceneIdSet.remove(onlineScenesOfUserItem);
					SceneCacheHelper.cacheOnineScenesOfUser(userId, onlineSceneIdSet);
					/**
					 * 修改场景的物理在场的用户集合
					 */
					SceneCacheHelper.removeCachedSceneOnlineUser(sceneId, userId, token);
					/**
					 * 记录日志
					 */
					logger.info("++++ChangeOnlineCount+++, remove sceneId={},userId={},token={}",sceneId,userId,token);
				}

			}
		}

		
	}
	
	public boolean isOnlineInScene(Long userId , Long sceneId){
		Set<String> sceneIdSet = SceneCacheHelper.getCachedOnlineScenesOfUser(userId);
		if (sceneIdSet != null){
			for (String onlineScenesOfUserItem : sceneIdSet){
				if (sceneId.equals( SceneCacheHelper.parseSceneIdFromOnlineScenesOfUserItem(onlineScenesOfUserItem))){
					return true;
				}
			}
		}
		
		return false;
	}

	

	public List<FuzziedSceneDomain> testFuzzied() {
		return sceneExMapper.queryFuzziedScene(new BigDecimal(-1000), new BigDecimal(1000), new BigDecimal(-1000), new BigDecimal(1000),
				new BigDecimal(0.91922));
	}
}
