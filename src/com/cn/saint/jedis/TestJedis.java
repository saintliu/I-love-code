package com.cn.saint.jedis;

import redis.clients.jedis.Jedis;

/**
 * cd src 
 * ./redis-server   ==> start server 
 * ./redis-cli      ==> start client
 */
public class TestJedis {
	private static Jedis instance; 
	private final static int port = 6379;
	private final static String host = "localhost";
	
	public TestJedis() {
	}
	
	public void sayHello() {
		System.out.println("....this is my first jedis object...");
	}

	public static Jedis getInstance() {
		if(null == instance) {
			synchronized (TestJedis.class) {
				if(null == instance) 
					instance = new Jedis(host, port);
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
          Jedis jedis = TestJedis.getInstance();
          jedis.set("1", "aa");
          
          Jedis jedis2 = TestJedis.getInstance();
          System.out.println("result = "+jedis2.get("1"));
          System.out.println("result1 = "+jedis2.get("2"));
	}
}
