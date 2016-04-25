package com.shortener.url.util;

import redis.clients.jedis.Jedis;

/**
 * This class deals with providing interface to Redis API
 * 
 * @author leonidas
 * @date 22nd April
 */
public enum RedisUtil {

	// Instance to get access to functions
	INSTANCE;

	// private Redis connection object
	private Jedis jedis;

	// Function to connect to redis
	public void createRedisConnection(String host, int port) {
		jedis = new Jedis(host, port);
	}

	// Function to close Reids connection
	public void closeRedisConnection() {
		jedis.flushDB();
		jedis.close();
	}

	// Function to push to redis
	public void pushToRedis(int key, String value) {
		jedis.set(String.valueOf(key), value);
	}

	// Function to get the value for a key
	public String fetchFromRedis(int key) {
		return jedis.get(String.valueOf(key));
	}
}
