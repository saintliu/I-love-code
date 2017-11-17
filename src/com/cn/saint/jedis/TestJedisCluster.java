package com.cn.saint.jedis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/** 
* @author saint
* @version 创建时间：2017年11月2日 下午9:28:34 
* 类说明 
*/
public class TestJedisCluster {

	private static JedisCluster jedisCluster;

	public static void main(String[] args) {
		JedisPoolConfig pool = new JedisPoolConfig();
		pool.setMaxTotal(100);
		pool.setMaxIdle(50);
		pool.setMinIdle(20);
		pool.setMaxWaitMillis(1000*6);
		pool.setTestOnBorrow(true);
		
		Set<HostAndPort> jedisClusterNodes = new HashSet<>();
		jedisClusterNodes.add(new HostAndPort("localhost", 7001));
		jedisClusterNodes.add(new HostAndPort("localhost", 7002));
		jedisClusterNodes.add(new HostAndPort("localhost", 7003));
		jedisClusterNodes.add(new HostAndPort("localhost", 7004));
		jedisClusterNodes.add(new HostAndPort("localhost", 7005));
		jedisClusterNodes.add(new HostAndPort("localhost", 7006));
		
		jedisCluster = new JedisCluster(jedisClusterNodes);
		
		//test set 
		jedisCluster.set("nihao", "jiayi");
		
		//test get
		System.out.println(jedisCluster.get("name"));
	}
}
