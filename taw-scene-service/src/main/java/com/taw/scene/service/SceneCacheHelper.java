package com.taw.scene.service;

import java.util.ArrayList;
import java.util.List;

import com.hawk.pub.spring.FrameworkContext;
import com.hawk.utility.redis.RedisClient;

public class SceneCacheHelper {
	
	private static RedisClient redisClient = FrameworkContext.getApplicationContext().getBean(RedisClient.class) ;
	
//	private static String genEnteredSceneKey(String token){
//		return "EnteredSceneList-" + token;
//	}
//	
//	public static List<Long> queryEnteredScene(String token){
//		String str = redisClient.get(genEnteredSceneKey(token));
//		if (str == null)
//			return null;
//		String[] strArray = str.split(",");
//		List<Long> list = new ArrayList<Long>();
//		for (String s : strArray){
//			list.add(Long.parseLong(s));
//		}
//		return list;
//	}

}
