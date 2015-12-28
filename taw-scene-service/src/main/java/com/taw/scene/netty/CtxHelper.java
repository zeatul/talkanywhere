package com.taw.scene.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelMatcher;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hawk.pub.spring.FrameworkContext;
import com.hawk.utility.redis.RedisClient;
import com.taw.scene.jms.Notification;
import com.taw.scene.service.SceneService;
import com.taw.user.service.LoginService;

public class CtxHelper {

	public final static int READ_TIMEOUT = 60 * 6;// 秒

	public final static int HEART_BEAT_INTERVAL = 60 * 2 * 1000; // 毫秒

	private static RedisClient redisClient = FrameworkContext.getApplicationContext().getBean(RedisClient.class);

	private static SceneService sceneService = FrameworkContext.getApplicationContext().getBean(SceneService.class);

	private static LoginService loginService = FrameworkContext.getApplicationContext().getBean(LoginService.class);

	/**
	 * 记录每个场景id， 有哪些绑定的通道
	 */
	private static ConcurrentHashMap<Long, ChannelGroup> scenedIdChannelMap = new ConcurrentHashMap<Long, ChannelGroup>();

	/**
	 * 记录每个用户id,有哪些绑定的通道
	 */
	private static ConcurrentHashMap<Long, ChannelGroup> userIdChannelMap = new ConcurrentHashMap<Long, ChannelGroup>();

	/**
	 * channelid 对应的 token
	 */
	private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	private static String genClientLoginKey(String token) {
		return "ctxLogin-" + token;
	}

	public static boolean existClientLogin(String token) {
		return redisClient.exists(genClientLoginKey(token));
	}

	/**
	 * 注册用户连接信息 记录token和channelId对应关系 维护每个场景当前连接的channel 维护用户Id 对应的
	 * channel，不同机器允许重复登录
	 * 
	 * @param token
	 * @param ctx
	 */
	public static void registClientLogin(String token, ChannelHandlerContext ctx) {
		tokenMap.put(ctx.channel().id().toString(), token);
		redisClient.set(genClientLoginKey(token), "0", READ_TIMEOUT, true);
		/**
		 * 计算token对应的场景
		 */
		List<Long> scendIdList = sceneService.queryEnteredScene(token);

		/**
		 * 维护每个场景当前连接的channel
		 */
		if (scendIdList != null && scendIdList.size() > 0) {
			for (Long scenedId : scendIdList) {
				ChannelGroup channelGroup = scenedIdChannelMap.get(scenedId);

				if (channelGroup == null) {
					channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
					scenedIdChannelMap.putIfAbsent(scenedId, channelGroup);
					channelGroup = scenedIdChannelMap.get(scenedId);
				}

				channelGroup.add(ctx.channel());
			}
		}

		/**
		 * 维护用户Id 对应的 channel，不同机器允许重复登录
		 */
		Long userId = loginService.queryUserId(token);
		if (userId != null) {
			ChannelGroup channelGroup = userIdChannelMap.get(userId);
			if (channelGroup == null) {
				channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
				userIdChannelMap.putIfAbsent(userId, channelGroup);
				channelGroup = scenedIdChannelMap.get(userId);
			}
			channelGroup.add(ctx.channel());
		}
	}

	/**
	 * 
	 * @param channelId
	 */
	public static void refreshClientLogin(String channelId) {
		String token = tokenMap.get(channelId);
		if (token != null) {
			redisClient.set(genClientLoginKey(token), "0", READ_TIMEOUT, true);
		}
	}

	/**
	 * 清除channelID对应的token注册的场景对应通道 清除用户id和通道的对应关系 清除channelId和token的对应关系
	 * 
	 * @param channelId
	 */
	public static void removeClientLogin(ChannelHandlerContext ctx) {
		String channelId = ctx.channel().id().toString();
		String token = tokenMap.get(channelId);
		if (token != null) {
			/**
			 * 计算token对应的场景
			 */
			List<Long> scendIdList = sceneService.queryEnteredScene(token);

			/**
			 * 清除这些场景绑定的该用户通道
			 */
			if (scendIdList != null && scendIdList.size() > 0) {
				for (Long scenedId : scendIdList) {
					ChannelGroup channelGroup = scenedIdChannelMap.get(scenedId);

					if (channelGroup != null) {
						channelGroup.remove(ctx.channel());
					}

				}
			}

			/**
			 * 清除用户id和通道的对应关系
			 */
			Long userId = loginService.queryUserId(token);
			if (userId != null) {
				ChannelGroup channelGroup = userIdChannelMap.get(userId);
				if (channelGroup != null) {
					channelGroup.remove(ctx.channel());
				}
			}

			tokenMap.remove(channelId);
		}
	}

	/**
	 * 场景收到消息后，通知所有该场景的在线用户
	 */
	public static void notifyConversationCreate(final Notification notification) {
		ChannelGroup channelGroup = scenedIdChannelMap.get(notification.getSceneId());		

		if (channelGroup != null) {
			String message = EnumMessageType.CONVERSATION_NOTIFICATION.toString() + notification.getSceneId();
			channelGroup.writeAndFlush(message, new ChannelMatcher() {

				@Override
				public boolean matches(Channel channel) {
					String token = tokenMap.get(channel.id().toString());

					/**
					 * 自己发的消息，不通知自己
					 */
					if (token == null || token.equals(notification.getToken()))
						return false;

					return true;
				}
			});
		}
	}

	/**
	 * 发送私信后，通知接收者
	 * @param notification
	 */
	public static void notifyMessageCreate(final Notification notification) {
		ChannelGroup channelGroup = userIdChannelMap.get(notification.getUserId());
		if (channelGroup != null){
			String message = EnumMessageType.MESSAGE_NOTIFICATION.toString();
			channelGroup.writeAndFlush(message);
		}

	}

}
