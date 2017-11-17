package com.cn.saint.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/** 
* @author saint
* @version 创建时间：2017年9月25日 下午12:03:20 
* 类说明 
*/
public class TestJedisPubSub extends JedisPubSub{

	@Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage: channel["+channel+"], message["+message+"]");
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage: channel["+channel+"], message["+message+"]");
    }

    @Override
    public void onSubscribe(String channel,int subscribedChannels) {
        System.out.println("onSubscribe: channel["+channel+"],"+
                                                "subscribedChannels["+subscribedChannels+"]");
    }

    @Override
    public void onUnsubscribe(String channel,int subscribedChannels) {
        System.out.println("onUnsubscribe: channel["+channel+"], "+
                                               "subscribedChannels["+subscribedChannels+"]");
    }

    @Override
    public void onPUnsubscribe(String pattern,int subscribedChannels) {
        System.out.println("onPUnsubscribe: pattern["+pattern+"],"+
                                               "subscribedChannels["+subscribedChannels+"]");
    }

    @Override
    public void onPSubscribe(String pattern,int subscribedChannels) {
        System.out.println("onPSubscribe: pattern["+pattern+"], "+
                                               "subscribedChannels["+subscribedChannels+"]");
    }
    
    public static void main(String[] args) {
    	    Jedis jedis = null;
    	  try {
		jedis = TestJedis.getInstance();
		TestJedisPubSub test = new TestJedisPubSub();
		
		test.proceed(jedis.getClient(), "first", "second");
		System.out.println(test.getSubscribedChannels());
    	  }catch (Exception e) {
			// TODO: handle exception
		}finally {
			jedis.close();
		}
	}
}
