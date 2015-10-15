package com.hawk.utility.redis;

import redis.clients.jedis.Jedis;

public class Example {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.17.101", 6379);
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println(value);
	} 

}
