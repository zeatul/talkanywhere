package com.taw.scene.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hawk.pub.spring.FrameworkContext;
import com.hawk.utility.CollectionTools;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.scene.com.UserOnlineScene;
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
	public final static String CACHED_USER_ONLINE_SCENES = "sceneOnline_";
	
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
	 * 返回用户物理在场的场景id集合
	 * @param userId
	 * @return
	 */
	public static Set<String> getCachedOnlineScenesOfUser(Long userId) {
		if (userId == null)
			return null;

		String key = CACHED_USER_ONLINE_SCENES + userId;

		String jsonStr = redisClient.get(key);

		if (StringTools.isNullOrEmpty(jsonStr)){
			return new HashSet<String>();
		}

		return JsonTools.toHashSet(jsonStr, String.class);
	}
	
	public static String buildOnlineScenesOfUserItem(String token , Long sceneId){
		return token+":"+sceneId;
	}
	
	public static Long parseSceneIdFromOnlineScenesOfUserItem(String onlineScenesOfUserItem){
		String[] str = onlineScenesOfUserItem.split(":");
		return new Long (str[str.length-1]);
	}
	
	public static String parseTokenFromOnlineScenesOfUserItem(String onlineScenesOfUserItem){
		String[] str = onlineScenesOfUserItem.split(":");
		return str[0];
	}

	/**
	 * 修改用户物理在场的场景集合
	 * @param userId
	 * @param sceneIdSet
	 */
	public static void cacheOnineScenesOfUser(Long userId, Set<String> sceneIdSet) {
		if (userId == null)
			return;
		String key = CACHED_USER_ONLINE_SCENES + userId.toString();
		if (CollectionTools.isNullOrEmpty(sceneIdSet)) {
			redisClient.delete(key);
		} else {
			String jsonStr = JsonTools.toJsonString(sceneIdSet);
			redisClient.set(key, jsonStr);			
		}

	}
	
	public static class UserOnScene{
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		private String nickname;
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
		private long fpdId;
		
		/*用户性别*/
		private String sex;
	}
	
	/**
	 * 缓存用户在场景的昵称
	 * @param token
	 * @param scendId
	 * @param nickname
	 */
	public static UserOnScene cacheNickname(String token , Long sceneId ,UserOnScene userOnScene){
		if (StringTools.isNullOrEmpty(token) || sceneId == null || userOnScene == null)
			return null ;
		String key = CACHED_SCENE_NICKNAME + token + "_" + sceneId;
		int expire = 3600 * 24;
		
		redisClient.set(key, JsonTools.toJsonString(userOnScene), expire);
		return userOnScene;
	}
	
	/**
	 * 获取缓存的用户昵称
	 * @param token
	 * @param scendId
	 * @return
	 */
	public static UserOnScene getCachedNickname(String token , Long sceneId){
		if (StringTools.isNullOrEmpty(token) || sceneId == null )
			return null;
		
		String key = CACHED_SCENE_NICKNAME + token + "_" + sceneId;
		
		String jsonStr =  redisClient.get(key);
		
		if (StringTools.isNullOrEmpty(jsonStr))
			return null;
		
		return JsonTools.toObject(jsonStr, UserOnScene.class);
	}

	/**
	 * 缓存指定物理场景的物理在线用户
	 * @param sceneId
	 * @param userOnlineScene
	 */
	public static void cacheSceneOnlineUser(Long sceneId,Long userId,String token,String nickname,String sex ){
		String key = CACHED_SCENE_ONLINE_USERS + sceneId ;
		
		UserOnlineScene userOnlineScene = new UserOnlineScene ();
		
		userOnlineScene.setToken(token);
		userOnlineScene.setUserId(userId);
		String field = JsonTools.toJsonString(userOnlineScene);
		
		userOnlineScene.setSex(sex);
		userOnlineScene.setNickname(nickname);
		String value = JsonTools.toJsonString(userOnlineScene);
		redisClient.hset(key, field, value);
	}
	
	/**
	 * 查询缓存的指定物理场景的全部在线用户
	 * @param sceneId
	 * @return
	 */
	public static List<UserOnlineScene> getCachedSceneOnlineUsers(Long sceneId){
		String key = CACHED_SCENE_ONLINE_USERS + sceneId ;
		
		List<UserOnlineScene> list = new ArrayList<UserOnlineScene>();
		
		List<String> items = redisClient.hGetAllValues(key);
		
		if (items != null){
			for (String item : items){
				UserOnlineScene userOnlineScene = JsonTools.toObject(item, UserOnlineScene.class);
				list.add(userOnlineScene);
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
	public static void removeCachedSceneOnlineUser(Long sceneId , Long userId ,String token){
		String key = CACHED_SCENE_ONLINE_USERS + sceneId ;
		UserOnlineScene userOnlineScene = new UserOnlineScene ();		
		userOnlineScene.setToken(token);
		userOnlineScene.setUserId(userId);
		String field = JsonTools.toJsonString(userOnlineScene);
		redisClient.hdel(key, field);
	}
}
