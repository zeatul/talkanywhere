package com.hawk.utility.redis;

import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Example {

	/**
	 * 普通同步方式,每次set之后都可以返回结果，标记是否成功
	 */
	// @Test
	public void testNormal() {
	
		
		Jedis jedis = new Jedis("192.168.17.101", 6379);
		String result = jedis.set("foo", "bar");
		System.out.println("result=" + result);
		String value = jedis.get("foo");
		System.out.println(value);
		jedis.close();
	}

	/**
	 * redis的事务很简单，他主要目的是保障，一个client发起的事务中的命令可以连续的执行，而中间不会插入其他client的命令。
	 * 我们调用jedis.watch(…)方法来监控key，如果调用后key值发生变化，则整个事务会执行失败。另外，事务中某个操作失败，
	 * 并不会回滚其他操作。 这一点需要注意。还有，我们可以使用discard()方法来取消事务
	 * 
	 */
	// @Test
	public void testTrans() {
		Jedis jedis = new Jedis("192.168.17.101", 6379);
		Transaction tx = jedis.multi();
		for (int i = 0; i < 10000; i++) {
			tx.set("t" + i, "t" + i);
		}
		List<Object> results = tx.exec();

		for (Object object : results) {
			System.out.println(object);
		}

		jedis.close();
	}

	/**
	 * 有时，我们需要采用异步方式，一次发送多个指令，不同步等待其返回结果。这样可以取得非常好的执行效率。这就是管道
	 */
	// @Test
	public void testPipeline() {
		Jedis jedis = new Jedis("192.168.17.101", 6379);
		Pipeline pipeline = jedis.pipelined();
		for (int i = 0; i < 10000; i++) {
			pipeline.set("p" + i, "p" + i);
		}
		List<Object> results = pipeline.syncAndReturnAll();
		for (Object object : results) {
			System.out.println(object);
		}

		jedis.close();
	}

	/**
	 * 管道中使用事务
	 */
//	@Test
	public void testPipelineTrans() {
		Jedis jedis = new Jedis("192.168.17.101", 6379);
		Pipeline pipeline = jedis.pipelined();
		pipeline.multi();
		for (int i = 0; i < 10000; i++) {
			pipeline.set("pt" + i, "pt" + i);
		}
		pipeline.exec();
		List<Object> results = pipeline.syncAndReturnAll();
		for (Object object : results) {
			System.out.println(object);
		}

		jedis.close();

	}

	/**
	 * 分布式直连同步调用
	 */
//	@Test
	public void testShardNormal() {
		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.17.101", 6379));

		ShardedJedis sharding = new ShardedJedis(shards);

		for (int i = 0; i < 100000; i++) {
			String result = sharding.set("sn" + i, "n" + i);
			System.out.println(result);
			;
		}

		sharding.close();

	}

	/**
	 * 分布式直连异步调用
	 */
//	@Test
	public void testShardPipeline() {
		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.17.101", 6379));

		ShardedJedis sharding = new ShardedJedis(shards);

		ShardedJedisPipeline pipeline = sharding.pipelined();

		for (int i = 0; i < 100000; i++) {
			pipeline.set("sp" + i, "p" + i);
		}
		List<Object> results = pipeline.syncAndReturnAll();

		for (Object object : results) {
			System.out.println(object);
		}

		sharding.close();

	}

	/**
	 * 分布式连接池同步调用
	 * 你的分布式调用代码是运行在线程中，那么上面两个直连调用方式就不合适了，因为直连方式是非线程安全的，这个时候，你就必须选择连接池调用
	 */
	@Test
	public void testShardPoolNormal() {
//		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.17.101", 6379));
		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.107.128", 6379));
		
		ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
		ShardedJedis one = pool.getResource();
		for (int i = 0; i < 10000; i++) {
			String result = one.set("spn" + i, "spn" + i);
			System.out.println(result);
		}
		pool.returnResource(one);
		// one.close();
		pool.destroy();

	}

	/**
	 * 分布式连接池异步调用
	 */
//	@Test
	public void testShardPipelinePool() {
		List<JedisShardInfo> shards = Arrays.asList(new JedisShardInfo("192.168.17.101", 6379));
		ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
		ShardedJedis one = pool.getResource();
		ShardedJedisPipeline pipeline = one.pipelined();
		for (int i = 0; i < 100000; i++) {
			pipeline.set("sppn" + i, "n" + i);
		}
		pool.returnResource(one);
		pool.destroy();

	}

}
