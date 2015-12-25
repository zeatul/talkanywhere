package com.taw.scene.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hawk.pub.spring.FrameworkContext;
import com.hawk.utility.redis.RedisClient;
import com.taw.scene.service.SceneService;

public class CtxHelper {
	
	public final static int READ_TIMEOUT = 60 * 6 ;// 秒
	
	public final static int HEART_BEAT_INTERVAL = 60 *2 *1000 ; //毫秒
	
	private static RedisClient redisClient = FrameworkContext.getApplicationContext().getBean(RedisClient.class) ;
	
	private static SceneService sceneService = FrameworkContext.getApplicationContext().getBean(SceneService.class) ;
	
	/**
	 * 记录每个场景id， 有哪些绑定的通道
	 */
	private static ConcurrentHashMap<Long,ChannelGroup> scenedIdChannelMap = new ConcurrentHashMap<Long, ChannelGroup>(); //ChannelGroup channelGroup
	
	/**
	 * channelid map token
	 */
	private static Map<String,String> tokenMap = new ConcurrentHashMap<String,String>();
	
	private static String genClientLoginKey(String token){
		return  "ctxLogin-" + token;
	}
	
	public static boolean existClientLogin(String token){
		return redisClient.exists(genClientLoginKey(token));
	}
	
	public static void registClientLogin(String token, ChannelHandlerContext ctx){
		tokenMap.put(ctx.channel().id().toString(), token);
		redisClient.set(genClientLoginKey(token), "0", READ_TIMEOUT, true);
		/**
		 * 汇总
		 */
		List<Long> scendIdList = sceneService.queryEnteredScene(token);
		
		if (scendIdList != null && scendIdList.size() > 0){
			for (Long scenedId : scendIdList){
				ChannelGroup channelGroup = scenedIdChannelMap.get(scenedId);
				
				if ( channelGroup == null )
					channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
				
				scenedIdChannelMap.putIfAbsent(scenedId, channelGroup);
				
				channelGroup.add(ctx.channel());
			}
		}
	}
	
	
	public static void refreshClientLogin(String channelId){
		String token = tokenMap.get(channelId);
		if (token != null){
			redisClient.set(genClientLoginKey(token), "0", READ_TIMEOUT, true);
		}
	}
	
	public static void removeClientLogin(String channelId){
		String token = tokenMap.get(channelId);
		
		if (token != null){
			tokenMap.remove(channelId);
		}
	}

}
