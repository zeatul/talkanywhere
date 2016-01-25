package com.taw.scene.service;

import com.hawk.pub.spring.FrameworkContext;
import com.hawk.utility.JsonTools;
import com.hawk.utility.StringTools;
import com.hawk.utility.redis.RedisClient;
import com.taw.pub.scene.response.SceneResp;
import com.taw.scene.domain.SceneDomain;

public class SceneCacheHelper {
	
	private static RedisClient redisClient = FrameworkContext.getApplicationContext().getBean(RedisClient.class) ;
	
	
	public final static String CACHED_SCENE_DOMAIN_KEY_HEAD = "sceneDomain_";
	
	public final static String CACHED_SCENE_STAT_COUNT_KEY_HEAD = "sceneResp_";
	
	public static class SceneStatCount{
		public Integer getEnterCount() {
			return enterCount;
		}
		public void setEnterCount(Integer enterCount) {
			this.enterCount = enterCount;
		}
		public Integer getOnlineCount() {
			return onlineCount;
		}
		public void setOnlineCount(Integer onlineCount) {
			this.onlineCount = onlineCount;
		}
		private Integer enterCount = 0;
		private Integer onlineCount = 0;
	}

	
	public static SceneStatCount getCachedSceneStatCount(Long sceneId){
		
		if (sceneId == null)
			return null;
		
		String key = CACHED_SCENE_STAT_COUNT_KEY_HEAD+sceneId.toString();
		String jsonStr = redisClient.get(key);
		
		if (StringTools.isNullOrEmpty(jsonStr))
			return null;
		
		return JsonTools.toObject(jsonStr, SceneStatCount.class);
		
	}
	
	public static void cacheSceneStatCount(Long sceneId , SceneStatCount  sceneStatCount){
		if (sceneStatCount == null || sceneId == null)
			return ;
		
		String key = CACHED_SCENE_STAT_COUNT_KEY_HEAD+sceneId.toString();
		String jsonStr = JsonTools.toJsonString(sceneStatCount);
		redisClient.set(key, jsonStr);
	}
	
	public static SceneDomain getCachedSceneDomain(Long sceneId){

		if (sceneId == null)
			return null;
		
		String key = CACHED_SCENE_DOMAIN_KEY_HEAD+sceneId.toString();
		String jsonStr = redisClient.get(key);
		
		if (StringTools.isNullOrEmpty(jsonStr))
			return null;
		
		return JsonTools.toObject(jsonStr, SceneDomain.class);
	}
	
	public static void cacheSceneDomain(SceneDomain sceneDomain){
		if (sceneDomain == null)
			return;
		String jsonStr = JsonTools.toJsonString(sceneDomain);
		String key = CACHED_SCENE_DOMAIN_KEY_HEAD+sceneDomain.getId().toString();
		int expire = 3600*24;
		redisClient.set(key, jsonStr, expire);
	}

}
