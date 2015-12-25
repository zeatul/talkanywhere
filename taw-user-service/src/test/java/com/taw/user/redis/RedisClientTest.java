package com.taw.user.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hawk.utility.redis.RedisClient;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations={
		"classpath*:com/taw/user/spring/applicationContext-user-service-redis.xml"
		})
public class RedisClientTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private RedisClient redisClient;
	
	@Test
	public void testNormal(){
		String key = "hello";
		String value = "world1111";
		redisClient.set(key, value, false);
		
		String cachedValue = redisClient.get(key);
		
		System.out.println(cachedValue);
		
		assertThat(cachedValue, is(value));
	}

}
