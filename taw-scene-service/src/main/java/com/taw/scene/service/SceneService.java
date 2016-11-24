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

import com.hawk.pub.enums.EnumBoolean;
import com.hawk.pub.pkgen.PkGenerator;
import com.hawk.utility.CollectionTools;
import com.hawk.utility.DateTools;
import com.hawk.utility.DomainTools;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.check.CheckTools;
import com.taw.pub.scene.com.MapPoint;
import com.taw.pub.scene.com.PresentUser;
import com.taw.pub.scene.enums.EnumLeaveType;
import com.taw.pub.scene.request.ChangePresentParam;
import com.taw.pub.scene.request.EnterSceneParam;
import com.taw.pub.scene.request.ExistFootPrintParam;
import com.taw.pub.scene.request.LeaveSceneParam;
import com.taw.pub.scene.request.QueryBookmarkParam;
import com.taw.pub.scene.request.QuerySceneByNameParam;
import com.taw.pub.scene.request.QuerySceneInRegionParam;
import com.taw.pub.scene.request.QuerySingleSceneParam;
import com.taw.pub.scene.request.QueryEnteredUsersOfSceneParam;
import com.taw.pub.scene.request.QueryPresentUsersOfSceneParam;
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
import com.taw.scene.exception.UserNotEnterSceneException;
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

	public SceneResp querySingleScene(QuerySingleSceneParam param, Map<Long, BookmarkDomain> bookedSceneIdMap) throws Exception {
		CheckTools.check(param);

		Long sceneId = param.getSceneId();

		SceneDomain sceneDomain = loadSceneDomain(sceneId, true);
		if (sceneDomain == null)
			return null;

		SceneResp sceneResp = new SceneResp();

		DomainTools.copy(sceneDomain, sceneResp);

		sceneResp.setEnterCount(sceneExMapper.queryEnterCount(sceneId));
		sceneResp.setOnlineCount(SceneCacheHelper.getCachedPresentUsersOfScene(sceneId).size());

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

		long sceneId = enterSceneParam.getSceneId();

		SceneDomain sceneDomain = loadSceneDomain(sceneId, true);

		if (sceneDomain == null)
			throw new SceneNotExistsException();

		long userId = enterSceneParam.getUserId();

		/**
		 * 记录用户进入场景历史明细表
		 */
		FootPrintDetailDomain footPrintDetailDomain = null;
		List<FootPrintDetailDomain> list = footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains2(sceneId, userId);
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
			footPrintDetailDomain.setSex(userDomain == null ? null : userDomain.getSex());
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

		/**
		 * TODO:处理present
		 */
		if (enterSceneParam.getPresent() == EnumBoolean.TRUE.getValue()) {
			ChangePresentParam changePresentParam = new ChangePresentParam();
			changePresentParam.setInSceneId(sceneId);
			changePresentParam.setUserId(userId);
			changePresent(changePresentParam);
		}

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

		if (!sceneId.equals(footPrintDetailDomain.getSceneId()))
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
		 * 修改在现场信息
		 */
		ChangePresentParam changePresentParam = new ChangePresentParam();
		changePresentParam.setOutSceneId(sceneId);
		changePresentParam.setUserId(userId);
		changePresent(changePresentParam);

		/**
		 * 通知netty
		 */
		Notification notification = new Notification();
		notification.setSceneId(sceneId);
		notification.setUserId(userId);
		notification.setToken(token);
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

	public UserOnScene queryNickname(long sceneId, long userId) throws UserNotEnterSceneException {

		UserOnScene userOnScene = SceneCacheHelper.getCachedNickname(userId, sceneId);

		if (userOnScene != null)
			return userOnScene;

		List<FootPrintDetailDomain> list = footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains2(sceneId, userId);
		if (list == null || list.size() == 0)
			throw new UserNotEnterSceneException();

		String nickname = list.get(0).getNickname();
		long fpdId = list.get(0).getId();
		String sex = list.get(0).getSex();

		userOnScene = new UserOnScene();
		userOnScene.setNickname(nickname);
		userOnScene.setFpdId(fpdId);
		userOnScene.setSex(sex);

		SceneCacheHelper.cacheNickname(userId, sceneId, userOnScene);

		return userOnScene;
	}

	/**
	 * 查询指定场景的全部物理在线用户
	 * 
	 * @param sceneId
	 * @return
	 * @throws Exception
	 */
	public List<PresentUser> queryPresentUsersOfScene(QueryPresentUsersOfSceneParam queryPresentUsersOfSceneParam) throws Exception {

		CheckTools.check(queryPresentUsersOfSceneParam);

		long sceneId = queryPresentUsersOfSceneParam.getSceneId();

		List<PresentUser> list = SceneCacheHelper.getCachedPresentUsersOfScene(sceneId);
		return list;
	}

	public List<PresentUser> queryEnteredUsersOfScene(QueryEnteredUsersOfSceneParam queryEnteredUsersOfSceneParam) throws Exception {

		CheckTools.check(queryEnteredUsersOfSceneParam);

		long sceneId = queryEnteredUsersOfSceneParam.getSceneId();
		int offset = queryEnteredUsersOfSceneParam.getOffset();
		int limit = queryEnteredUsersOfSceneParam.getLimit();

		List<FootPrintDetailDomain> footPrintDetailDomainsList = footPrintDetailExMapper.queryUnLeavedFootPrintDetailDomains3(sceneId, offset, limit);

		List<PresentUser> list = new ArrayList<PresentUser>();
		for (FootPrintDetailDomain footPrintDetailDomain : footPrintDetailDomainsList) {
			PresentUser presentUser = new PresentUser();
			presentUser.setNickname(footPrintDetailDomain.getNickname());
			presentUser.setFpdId(footPrintDetailDomain.getId());
			presentUser.setUserId(footPrintDetailDomain.getUserId());
			presentUser.setSex(footPrintDetailDomain.getSex());
			list.add(presentUser);
		}
		return list;
	}

	/**
	 * 根据前台通知，修改在场人数
	 * 
	 * @param changeOnlineCountParam
	 * @throws Exception
	 */
	public void changePresent(ChangePresentParam changePresentParam) throws Exception {
		CheckTools.check(changePresentParam);

		Long userId = changePresentParam.getUserId();
		Long inSceneId = changePresentParam.getInSceneId();

		String sex = null;

		String nickname = null;

		Long fpdId = null;

		/**
		 * 用户物理进入场景
		 */
		if (inSceneId != null) {

			/**
			 * 查询用户昵称和性别
			 */
			UserOnScene userOnScene = queryNickname(inSceneId, userId);
			sex = userOnScene.getSex();
			nickname = userOnScene.getNickname();
			fpdId = userOnScene.getFpdId();

			Long cachedPresentSceneId = SceneCacheHelper.getCachedPresentSceneId(userId);

			if (!inSceneId.equals(cachedPresentSceneId)) {
				/**
				 * 缓存用户在现场的场景id
				 */
				SceneCacheHelper.cachePresentSceneId(userId, inSceneId);
				/**
				 * 修改场景的在现场用户集合
				 */
				SceneCacheHelper.cachePresentUserOfScene(inSceneId, userId, nickname, sex, fpdId);

			}

		}

		/**
		 * 用户离开现场
		 */
		Long outSceneId = changePresentParam.getOutSceneId();
		if (outSceneId != null) {

			Long cachedPresentSceneId = SceneCacheHelper.getCachedPresentSceneId(userId);

			if (outSceneId.equals(cachedPresentSceneId)) {

				/**
				 * 去掉缓存的用户在现场的场景Id
				 */
				SceneCacheHelper.removeCachedPresentSceneId(userId);
				/**
				 * 修改场景的物理在场的用户集合
				 */
				SceneCacheHelper.removeCachedPresentUserOfScene(outSceneId, userId);
			}

		}

	}

	public boolean isPresentedInScene(ExistFootPrintParam existFootPrintParam) {
		long userId = existFootPrintParam.getUserId();
		long scenedId = existFootPrintParam.getSceneId();
		
		Long cachedSceneId = SceneCacheHelper.getCachedPresentSceneId(userId);
		
		if (cachedSceneId!=null && cachedSceneId.longValue() == scenedId){
			return true;
		}
		
		return false;
		
		
	}

	public List<FuzziedSceneDomain> testFuzzied() {
		return sceneExMapper.queryFuzziedScene(new BigDecimal(-1000), new BigDecimal(1000), new BigDecimal(-1000), new BigDecimal(1000),
				new BigDecimal(0.91922));
	}
}
