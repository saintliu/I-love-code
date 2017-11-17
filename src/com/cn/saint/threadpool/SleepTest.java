package com.cn.saint.threadpool;
/** 
* @author saint
* @version 创建时间：2017年11月6日 下午5:44:54 
* 类说明 
*/

/**
 * Sleep不会释放锁
 */

/**
 * i:11
	线程:Thread-0进入睡眠状态
	线程Thread-0睡眠结束
	i:12
	i:13
	线程:Thread-1进入睡眠状态
	线程Thread-1睡眠结束
   i:14
 *
 */
public class SleepTest {

	private int i = 10;
	private Object object = new Object();
	
	public static void main(String[] args) {
		SleepTest test = new SleepTest();
		MyThread thread1 = test.new MyThread();
		MyThread thread2 = test.new MyThread();
		
		thread1.start();
		thread2.start();
	}
	
    class MyThread extends Thread {
		
    	    /**
    	     *  i:11
			线程:Thread-0进入睡眠状态
			i:12
			线程:Thread-1进入睡眠状态
    	     * 
    	     * 从结果可以看到wait会释放锁
    	     */
    	
		public void run() {
			synchronized (object) {
				i++;
				System.out.println("i:"+i);
				try {
					System.out.println("线程:"+Thread.currentThread().getName()+"进入睡眠状态");
			//		Thread.currentThread().sleep(100);
					Thread.currentThread().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("线程"+Thread.currentThread().getName()+"睡眠结束");
				i++;
				System.out.println("i:"+i);
			}
		}
	}
}


