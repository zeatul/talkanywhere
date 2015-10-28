package com.hawk.utility.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisShardInfo;

/**
 * 
 * @author pzhang1
 *
 */
public class ShardsFactory {

	public static List<JedisShardInfo> produce(String param){
		//192.168.107.128:6379:PASSWORD;192.168.107.129:6379:PASSWORD
		String[] nodeStrArray = param.split(";");
		List<JedisShardInfo> list = new ArrayList<JedisShardInfo>(nodeStrArray.length);
		for (String nodeStr : nodeStrArray){
			String[] strArray = nodeStr.split(":");
			
			JedisShardInfo jedisShardInfo = new JedisShardInfo(strArray[0], strArray[1]);
			if (strArray.length >2){
				jedisShardInfo.setPassword(strArray[2]);
			}
			
			list.add(jedisShardInfo);
		}
		
		return list;
		
	}
}
