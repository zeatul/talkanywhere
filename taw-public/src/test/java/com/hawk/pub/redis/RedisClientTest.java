package com.hawk.pub.redis;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

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
	
	@Test
	public void testNormal() throws InterruptedException{
		String key = "ctxLogin-" + "cde91645dbed4e08b6585aa2a7d4790f";
				
		boolean exist  = redisClient.exists(key);
		System.out.println("exist="+exist);
		
		redisClient.set(key, "0", 20);
		
		String value = redisClient.get(key);
		System.out.println("value2="+value);
		exist  = redisClient.exists(key);
		System.out.println("exist="+exist);
		
		TimeUnit.SECONDS.sleep(21);

		value = redisClient.get(key);
		System.out.println("value3="+value);

	}

}
