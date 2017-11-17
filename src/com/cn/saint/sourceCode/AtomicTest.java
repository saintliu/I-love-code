package com.cn.saint.sourceCode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	
	/** i++ 是不安全的操作，所以需要原子
	 * 原子操作是线程安全，其底层实现：
	 * 1. 首先使用了Volatile保证了内存可见性
	 * 2. 使用了CAS （compare and swap） 算法，保证原子性
	 * 比较并交换
	 * =========================CAS=======================
	 * CAS 有3个数， 内存值V， 旧值A， 新职B，当且仅当A=V时，才将V改为B
	 * 
	 * 以下方法用于获取属性在内存中的位置
	 * valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));  
	 * 
	 * 这里可以举个例子来说明compareAndSet的作用，如支持并发的计数器，
	 * 在进行计数的时候，首先读取当前的值，假设值为a，对当前值 + 1得到b，但是+1操作完以后，并不能直接修改原值为b，
	 * 因为在进行+1操作的过程中，可能会有其它线程已经对原值进行了修改，所以在更新之前需要判断原值是不是等于a，
	 * 如果不等于a，说明有其它线程修改了，需要重新读取原值进行操作，
	 * 如果等于a，说明在+1的操作过程中，没有其它线程来修改值，我们就可以放心的更新原值了
	 * 
	 * ABA问题 (值的版本)
	 * 
	 */
	
	/**
	 * 只有get和set方法不需要特别处理
	 * 其他方法当需要修改值得时候，都会用unsafe方法的compareandswap，乐观锁
	 */
	static AtomicInteger count = new AtomicInteger(0);
	
	static Integer count1 = new Integer(0);

	public static class AddThread implements Runnable{

		
		public void run() {
             for(int i=0;i<50;i++){
            	 //执行原子操作，自增
            	 count.incrementAndGet();
            	 count1++;
           // 	 System.out.println(Thread.currentThread().getName()+"在执行任务:"+count);
             }
		}
		
	}
	
	public static void AtomicIntShow() throws InterruptedException{
		System.out.println("AtomicIntShow() enter");
		
		//定义一个长度为5的线程池，如果没有空闲的线程，其他任务需要等待。
		ExecutorService threadpool = Executors.newFixedThreadPool(5);
		
		//提交10个任务，分配给相应的线程，最大只有5个线程
		for(int k=0;k<10;k++){
			threadpool.submit(new AddThread());
		}
		
		Thread.sleep(2000);
		
		System.out.println("result of acumlated sum in terms of atomic= "+count);
		System.out.println("result of acumlated sum in terms of non-atomic ="+count1);
		threadpool.shutdown();
		System.out.println("AtomicIntShow() exit");
		return;
		
		/**
		 *  AtomicIntShow() enter
			result of acumlated sum in terms of atomic= 500
			result of acumlated sum in terms of non-atomic =481
			AtomicIntShow() exit
		 */
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		AtomicTest.AtomicIntShow();

	//	AtomicInteger ai = new AtomicInteger(0);
		/*//获取当前值
		int i1 = ai.get();
		v(i1);
		//获取当前值，并更新
		int i2 = ai.getAndSet(5);
		v(i2);
		int i3 = ai.get();
		v(i3);
		//获取当前值，并自增
		int i4 = ai.getAndIncrement();
		v(i4);
		v(ai.get());*/
		
	}

	static void v(int i){
		System.out.println("i="+i);
	}
}
