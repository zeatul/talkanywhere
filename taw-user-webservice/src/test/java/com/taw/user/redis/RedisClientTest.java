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
		"classpath*:com/taw/pub/spring/applicationContext-pub-*.xml",
		"classpath*:com/taw/user/spring/applicationContext-user-service-*.xml"
		})
public class RedisClientTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	@Qualifier("taw_user_service_redis_client")
	private RedisClient redisClient;
	
	@Test
	public void testNormal(){
		String key = "hello";
		String value = "world";
		redisClient.set(key, value, false);
		
		String cachedValue = redisClient.get(key);
		
		assertThat(cachedValue, is(value));
	}

}
