package com.cn.saint.reetrantLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/** 
* @author saint
* @version 创建时间：2017年8月29日 下午2:26:54 
* 类说明 
*/

/**
 * 区别
 * 
 * 1. reentrantLock 的锁在jdk实现，必须手动释放锁，而syn在jvm中实现，会自动释放锁
 * 2. 在竞争不激烈情况下，syn优于reentrantlock。在资源竞争激烈情况下，使用syn争取不到资源的线程会一直等待，而reentrantlock不会
 * 
 * reetrantLock implements Lock 
 * 
 * ReentrantReadWriteLock  implements ReadWriteLock
 * 
 * writeLock  写锁   --> 写锁，其他线程也不允许写
 * readLock   读锁   --> 其他线程只能读，不能写
 *
 */
public class Sync_Compare_ReetrantLock {

	/**
	 * synchronized
	 *
	 */
	static class SynRunner implements Runnable{
		private long v = 0;

		public synchronized void run() {
			 v = v +1;
		}
	}
	
	static class LockRunner implements Runnable{
        private ReentrantLock lock = new ReentrantLock();
        private long v = 0;
		
		public void run() {
			lock.lock();
			try {
			  v = v + 1;
			}finally {
				lock.unlock();
			}
		}
	}
	
	static class Tester{
		private AtomicLong runCount = new AtomicLong(0);
		private AtomicLong start    = new AtomicLong();
		private AtomicLong end      = new AtomicLong();
		
		public  Tester(final Runnable runner, int threadCount) {
			final ExecutorService pool = Executors.newFixedThreadPool(threadCount);
			Runnable task = new Runnable() {
				
				public void run() {
                    while(true) {
                    	   runner.run();
                    	   long count = runCount.incrementAndGet();
                    	   if(count == 1) {
                    		   start.set(System.nanoTime());
                    	   } else if(count>=10000000) {
                    		   if(count == 10000000) {
                    			   end.set(System.nanoTime());
                    			   System.out.println(runner.getClass().getSimpleName()
                    					   +", cost:"+
                    					   (end.longValue()-start.longValue())/10000000 + "ms");
                    			   pool.shutdown();
                    			  return;
                    		   }
                    	   }
                    }
				}
			};
			
			//submit task
			for(int i=0;i<threadCount;i++) {
				pool.submit(task);
			}
		}
	}
	
	public static void main(String[] args) {

		new Tester(new SynRunner(), 400);
		new Tester(new LockRunner(), 400);
	}
}
