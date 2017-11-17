package com.cn.saint.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Queable;

/** 
* @author saint
* @version 创建时间：2017年9月25日 上午10:06:50 
* 类说明 
*/
public class ThreadPoolTest implements Runnable{
	
	@Override
	public void run() {

		synchronized (this) {
			System.out.println(Thread.currentThread().getName()+"正在执行");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4);
		//corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue
		//corePoolSize 核心线程数，指保留的线程池大小（不超过maximumPoolSize值时，线程池中最多有corePoolSize 个线程工作）
		//maximumPoolSize 指的是线程池的最大大小（线程池中最大有corePoolSize 个线程可运行）
		//keepAliveTime 指的是空闲线程结束的超时时间（当一个线程不工作时，过keepAliveTime 长时间将停止该线程）
		//unit 表示 keepAliveTime 的单位
		//workQueue 表示存放任务的队列（存放需要被线程池执行的线程队列）
		/**
		 * handler 拒绝策略（添加任务失败后如何处理该任务）.
			1、线程池刚创建时，里面没有一个线程。任务队列是作为参数传进来的。不过，就算队列里面有任务，线程池也不会马上执行它们。
			2、当调用 execute() 方法添加一个任务时，线程池会做如下判断：
			    a. 如果正在运行的线程数量小于 corePoolSize，那么马上创建线程运行这个任务；
			    b. 如果正在运行的线程数量大于或等于 corePoolSize，那么将这个任务放入队列。
			    c. 如果这时候队列满了，而且正在运行的线程数量小于 maximumPoolSize，那么还是要创建线程运行这个任务；
			    d. 如果队列满了，而且正在运行的线程数量大于或等于 maximumPoolSize，那么线程池会抛出异常，告诉调用者“我不能再接受任务了”。
			3、当一个线程完成任务时，它会从队列中取下一个任务来执行。
			4、当一个线程无事可做，超过一定的时间（keepAliveTime）时，线程池会判断，如果当前运行的线程数大于 corePoolSize，那么这个线程就被停掉。所以线程池的所有任务完成后，它最终会收缩到 corePoolSize 的大小。
		 */
		/**
		 * 当线程数量>maximumPoolSize+queue.size时，多出的线程会根据拒绝策略被拒绝
		 * 
		 */
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.DAYS, queue);
		for(int i=0;i<10;i++) {
			executor.execute(new Thread(new ThreadPoolTest()));
			int threadSize = queue.size();
			System.out.println("线程队列大小为-->"+threadSize);
		}
		executor.shutdown();
	}
	
}
