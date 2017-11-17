package com.cn.saint.reetrantLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.bcel.internal.generic.NEW;

/** 
* @author saint
* @version 创建时间：2017年8月28日 下午5:54:58 
* 类说明 
*/
public class ReetrantLockTest implements Runnable{

	private PrintQueue printQueue;
	private CountDownLatch countDownLatch;
	
	public ReetrantLockTest(PrintQueue printQueue, CountDownLatch countDownLatch) {
		this.printQueue = printQueue;
		this.countDownLatch = countDownLatch;
	}
	
	public void run() {
		System.out.printf("%s: Going to print a doc\n", Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The doc has been printed\n", Thread.currentThread().getName());
		//计数器，当任务做完时，执行--
		countDownLatch.countDown();
	}
	
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(10);
		
         PrintQueue printQueue = new PrintQueue();
         Thread thread[] = new Thread[10];
         for(int i=0;i<10;i++) {
        	   thread[i] = new Thread(new ReetrantLockTest(printQueue, countDownLatch), "Thread "+i);
         }
         for(int i=0;i<10;i++) {
	        	 thread[i].start();
         }
         try {
        	   //当计数器为不0时，所有线程必须等待
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
         System.out.println(".......all completed........");
	}
}

class PrintQueue {
	private final Lock lock = new ReentrantLock();
	
	public void printJob(Object doc) {
		lock.lock();
		Long duration = (long) (Math.random()*2000);
		
		System.out.println(Thread.currentThread().getName()+
				":PrintQueue: Printing a job during "+
				(duration/1000)+" seconds");
		try {
			Thread.sleep(duration);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
