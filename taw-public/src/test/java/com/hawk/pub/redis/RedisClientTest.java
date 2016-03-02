package com.hawk.pub.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hawk.utility.JsonTools;
import com.hawk.utility.redis.RedisClient;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations={
		"classpath*:com/hawk/pub/spring/applicationContext-pub-redis.xml"
		})
public class RedisClientTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	@Qualifier("redisClient")
	private RedisClient redisClient;
	
	
	public void testNormal() throws InterruptedException{
		String key = "ctxLogin-" + "cde91645dbed4e08b6585aa2a7d4790f";
				
		boolean exist  = redisClient.exist(key);
		System.out.println("exist="+exist);
		
		redisClient.set(key, "0", 20);
		
		String value = redisClient.get(key);
		System.out.println("value2="+value);
		exist  = redisClient.exist(key);
		System.out.println("exist="+exist);
		
		TimeUnit.SECONDS.sleep(21);

		value = redisClient.get(key);
		System.out.println("value3="+value);

	}
	
	@Test
	public  void testHashSet(){
		String key = "@@@_111";
		List<String> values = new ArrayList<String>();
		values.add("111");
		values.add("222");
		values.add("333");
		redisClient.sset(key, values);
		
		Set<String> set = redisClient.sget(key);
		
		System.out.println(JsonTools.toJsonString(set));
		
		 values = new ArrayList<String>();
		values.add("444");
		values.add("444");
		values.add("666");
		
		redisClient.sdel(key, values);
		
		set = redisClient.sget(key);
		System.out.println(JsonTools.toJsonString(set));
	}

}
