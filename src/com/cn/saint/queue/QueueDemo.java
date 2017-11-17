package com.cn.saint.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/** 
* @author saint
* @version 创建时间：2017年9月9日 下午3:49:42 
* 类说明 
*/

/**
 * 使用ArrayDeque队列，实现先进先出
 *
 */
public class QueueDemo {

	public static void deal(Queue<Request> queue) {
		Request request = null;
		
		while(null != (request=queue.poll())) {
			request.deposite();
		}
	}
	
	public static void main(String[] args) {
		Queue<Request> queue = new ArrayDeque<Request>();
		
		for(int i=0;i<10;i++) {
			final int num = i;
			queue.offer(new Request() {
				
				@Override
				public void deposite() {
	                System.out.println("第"+num+"个人办理业务，存款为"+(Math.random()));				
				}
			});
		}
		deal(queue);
	}
}

interface Request{
	void deposite();
}


