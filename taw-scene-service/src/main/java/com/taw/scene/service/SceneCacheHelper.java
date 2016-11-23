package com.taw.scene.service;

import java.util.ArrayList;
import java.util.List;
import com.hawk.pub.spring.FrameworkContext;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.scene.com.PresentUser;
import com.taw.scene.domain.SceneDomain;

public class SceneCacheHelper {

	private static RedisClient redisClient = FrameworkContext.getApplicationContext().getBean(RedisClient.class);

	/**
	 * 缓存SceneDomain
	 */
	public final static String CACHED_SCENE_DOMAIN_KEY_HEAD = "sceneDomain_";

	/**
	 * 缓存SceneStatCount ，场景的enterCount 和 onlineCount
	 */
	public final static String CACHED_SCENE_STAT_COUNT_KEY_HEAD = "sceneResp_";

	/**
	 * 缓存用户物理在线场景 online scene
	 */
	public final static String CACHED_PRESENT_SCENE_ID = "presentSceneId_";
	
	/**
	 * 缓存场景物理在场的用户的id,nickname,token
	 */
	public final static String CACHED_SCENE_ONLINE_USERS = "usersOnlineScene_";
	
	/**
	 * 缓存用户的nickname
	 */
	public final static String CACHED_SCENE_NICKNAME = "nickname_";
	

	public static SceneDomain getCachedSceneDomain(Long sceneId) {

		if (sceneId == null)
			return null;

		String key = CACHED_SCENE_DOMAIN_KEY_HEAD + sceneId.toString();
		String jsonStr = redisClient.get(key);

		if (StringTools.isNullOrEmpty(jsonStr))
			return null;

		return JsonTools.toObject(jsonStr, SceneDomain.class);
	}

	public static void cacheSceneDomain(SceneDomain sceneDomain) {
		if (sceneDomain == null)
			return;
		String jsonStr = JsonTools.toJsonString(sceneDomain);
		String key = CACHED_SCENE_DOMAIN_KEY_HEAD + sceneDomain.getId().toString();
		int expire = 3600 * 24;
		redisClient.set(key, jsonStr, expire);
	}

	/**
	 * 返回现场场景Id
	 * @param userId
	 * @return
	 */
	public static Long getCachedPresentSceneId(long userId) {
		
		String key = CACHED_PRESENT_SCENE_ID + userId;

		String value = redisClient.get(key);

		if (StringTools.isNullOrEmpty(value)){
			return null;
		}

		return Long.parseLong(value);
	}
	

	/**
	 * 修改用户物理在场的场景集合
	 * @param userId
	 * @param sceneIdSet
	 */
	public static void cachePresentSceneId(long userId, long sceneId) {
		
		String key = CACHED_PRESENT_SCENE_ID + userId;
		redisClient.set(key, String.valueOf(sceneId) );

	}
	
	public static void removeCachedPresentSceneId(long userId){
		String key = CACHED_PRESENT_SCENE_ID + userId;
		redisClient.delete(key);
	}
	
	public static class UserOnScene{
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public long getFpdId() {
			return fpdId;
		}
		public void setFpdId(long fpdId) {
			this.fpdId = fpdId;
		}
		
		private String nickname;
		private long fpdId;
		private String sex;
	}
	
	
	private static String buildCacheNickNameKey(long userId,long sceneId){
		return CACHED_SCENE_NICKNAME + userId + "_" + sceneId;
	}
	
	/**
	 * 缓存用户在场景的昵称
	 * @param token
	 * @param scendId
	 * @param nickname
	 */
	public static UserOnScene cacheNickname(long userId , long sceneId ,UserOnScene userOnScene){
		
		
		int expire = 3600 * 24;
		
		redisClient.set(buildCacheNickNameKey(userId,sceneId), JsonTools.toJsonString(userOnScene), expire);
		return userOnScene;
	}
	
	/**
	 * 获取缓存的用户昵称
	 * @param token
	 * @param scendId
	 * @return
	 */
	public static UserOnScene getCachedNickname(long userId , long sceneId){
		
		String jsonStr =  redisClient.get(buildCacheNickNameKey(userId,sceneId));
		
		if (StringTools.isNullOrEmpty(jsonStr))
			return null;
		
		return JsonTools.toObject(jsonStr, UserOnScene.class);
	}

	/**
	 * 缓存指定物理场景的物理在线用户
	 * @param sceneId
	 * @param userOnlineScene
	 */
	public static void cachePresentUserOfScene(long sceneId,long userId,String nickname,String sex,Long fpdId ){
		String key = CACHED_SCENE_ONLINE_USERS + sceneId ;
		
		PresentUser presentUser = new PresentUser ();
		presentUser.setUserId(userId);		
		presentUser.setSex(sex);
		presentUser.setNickname(nickname);
		presentUser.setFpdId(fpdId);
		String value = JsonTools.toJsonString(presentUser);
		redisClient.hset(key, String.valueOf(userId), value);
	}
	
	/**
	 * 查询缓存的指定场景的全部在现场用户
	 * @param sceneId
	 * @return
	 */
	public static List<PresentUser> getCachedPresentUsersOfScene(long sceneId){
		String key = CACHED_SCENE_ONLINE_USERS + sceneId ;
		
		List<PresentUser> list = new ArrayList<PresentUser>();
		
		List<String> items = redisClient.hGetAllValues(key);
		
		if (items != null){
			for (String item : items){
				PresentUser presentUser = JsonTools.toObject(item, PresentUser.class);
				list.add(presentUser);
			}
		}
		
		return list ;
	}
	
	/**
	 * 删除指定物理场景的物理在线用户
	 * @param sceneId
	 * @param userId
	 * @param token
	 */
	public static void removeCachedPresentUserOfScene(long sceneId , long userId ){
		String key = CACHED_SCENE_ONLINE_USERS + sceneId ;
		redisClient.hdel(key, String.valueOf(userId));
	}
}
