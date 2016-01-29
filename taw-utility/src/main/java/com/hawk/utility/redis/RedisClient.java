package com.hawk.utility.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;

public class RedisClient {

	public ShardedJedisPool getPool() {
		return pool;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void setPool(ShardedJedisPool pool) {
		this.pool = pool;
	}

	private ShardedJedisPool pool;

	/**
	 * 加入缓存,无限期
	 * 
	 * @param key
	 * @param value
	 * @param async
	 *            true=异步
	 */
	public void set(final String key, final String value) {

		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedisPipeline pipeline) {

				pipeline.set(key, value);
				return null;
			}

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.set(key, value);
				return null;
			}
		}, false);

		

	}

	/**
	 * 加入缓存
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 *            有效期，单位秒
	 */
	public void set(final String key, final String value, final int expire) {

		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedisPipeline pipeline) {
				pipeline.set(key, value);
				pipeline.expire(key, expire);
				return null;
			}

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.set(key, value);
				shardedJedis.expire(key, expire);
				return null;
			}
		}, false);

	}

	/**
	 * 从缓存取值
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		return execute(new Executor() {

			@SuppressWarnings("unchecked")
			@Override
			public String exec(ShardedJedis shardedJedis) {
				// TODO Auto-generated method stub
				return shardedJedis.get(key);
			}

			@SuppressWarnings("unchecked")
			@Override
			public String exec(ShardedJedisPipeline pipeline) {
				return pipeline.get(key).get();
			}
		}, false);
	}

	/**
	 * 判断key值是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return execute(new Executor() {

			@SuppressWarnings("unchecked")
			@Override
			public Boolean exec(ShardedJedis shardedJedis) {
				// TODO Auto-generated method stub
				return shardedJedis.exists(key);
			}

			@SuppressWarnings("unchecked")
			@Override
			public Boolean exec(ShardedJedisPipeline pipeline) {
				// TODO Auto-generated method stub
				return pipeline.exists(key).get();
			}

		}, false);
	}

	private interface Executor {
		<T> T exec(ShardedJedis shardedJedis);

		<T> T exec(ShardedJedisPipeline pipeline);

	}

	@SuppressWarnings("deprecation")
	private <T> T execute(Executor executor, boolean async) {

		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = pool.getResource();
			if (async) {
				ShardedJedisPipeline pipeline = shardedJedis.pipelined();
				return executor.exec(pipeline);
			} else {
				return executor.exec(shardedJedis);
			}
		} catch (JedisException ex) {
			try {
				pool.returnBrokenResource(shardedJedis);
			} catch (Exception e) {
				logger.error("shardedJedis.disconnect()", e);
			}
			throw ex;
		} finally {
			if (shardedJedis != null) {
				try {
					pool.returnResource(shardedJedis);

				} catch (Exception e) {
					logger.error("shardedJedis.returnResource()", e);
				}
			}

		}
	}

	/**
	 * 删除可以
	 * 
	 * @param key
	 * @param async
	 */
	public void delete(final String key) {

		execute(new Executor() {

			@Override
			public <T> T exec(ShardedJedisPipeline pipeline) {
				pipeline.del(key);
				return null;
			}

			@Override
			public <T> T exec(ShardedJedis shardedJedis) {
				shardedJedis.del(key);
				return null;
			}
		}, false);

	}

}
