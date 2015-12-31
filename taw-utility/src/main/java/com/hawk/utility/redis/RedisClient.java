package com.hawk.utility.redis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;

public class RedisClient {

	public ShardedJedisPool getPool() {
		return pool;
	}

	public void setPool(ShardedJedisPool pool) {
		this.pool = pool;
	}

	private ShardedJedisPool pool;

	/**
	 * 加入缓存,无限期
	 * 
	 * @param key
	 * @param value
	 * @param async	true=异步
	 */
	public void set(String key, String value) {
		boolean async = false;
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = pool.getResource();
			if (async) {
				ShardedJedisPipeline pipeline = shardedJedis.pipelined();
				pipeline.set(key, value);
			} else {
				shardedJedis.set(key, value);
			}
		}
		finally {

			if (shardedJedis != null) {
				try {
					shardedJedis.close();
					// pool.returnResource(shardedJedis);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * 加入缓存
	 * 
	 * @param key
	 * @param value
	 * @param expire 有效期，单位秒
	 * @param async true=异步
	 */
	public void set(String key, String value, int expire) {
		boolean async = false;
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = pool.getResource();
			if (async) {
				ShardedJedisPipeline pipeline = shardedJedis.pipelined();
				pipeline.set(key, value);
				pipeline.expire(key, expire);
			} else {
				shardedJedis.set(key, value);
				shardedJedis.expire(key, expire);
			}
		} finally {
			if (shardedJedis != null) {
				try {
					shardedJedis.close();
					// pool.returnResource(shardedJedis);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}

	/**
	 * 从缓存取值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = pool.getResource();
			return shardedJedis.get(key);
		} finally {
			if (shardedJedis != null) {
				try {
					shardedJedis.close();
				} catch (Exception e) {

				}
			}

		}
	}

	/**
	 * 判断key值是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = pool.getResource();
			return shardedJedis.exists(key);
		} finally {
			if (shardedJedis != null) {
				try {
					shardedJedis.close();
				} catch (Exception e) {

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
	public void delete(String key, boolean async) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = pool.getResource();
			if (async) {
				ShardedJedisPipeline pipeline = shardedJedis.pipelined();
				pipeline.del(key);
			} else {
				shardedJedis.del(key);
			}
		} finally {
			if (shardedJedis != null) {
				try {
					shardedJedis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

}
