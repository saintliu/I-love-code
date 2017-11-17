package com.cn.saint.threadLocal;

/**
 * ThreadLocal : 线程局部变量类 ，来自java.lang包
 * 
 * 在并发编程中，成员变量如果不做处理，是线程不安全的，volatile也不能保证线程安全
 * 有一种情况下：每个线程都使用同一个变量的初始值，也就是使用同一个变量的副本，
 * 这时候ThreadLocal就有用了。
 * 比如DAO的数据库连接，我们知道DAO是单利的，那么他的属性就不是一个线程安全的变量，
 * 而我们每个线程都需要用它，并且互不干扰。
 * 
 * 其实现原理：
 * 在ThreadLocal类中有一个Map， 用于存储每个线程的变量副本，Map中
 * 元素的键位线程对象，而值为对应线程的变量副本
 */

public class ThreadLocalDemo {
	
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
		public Integer initialValue(){
			return 0;
		}
	};
	
	//获取下一个序列值
	public int getNextNum(){
		seqNum.set(seqNum.get()+1);
		return seqNum.get();
	}
	
	public static void main(String[] args) {

		ThreadLocalDemo t = new ThreadLocalDemo();
		
		//3个线程共享t，各自产生序列号
		TestClient t1 = new TestClient(t, "t1");
		TestClient t2 = new TestClient(t, "t2");
		TestClient t3 = new TestClient(t, "t3");
		
		t1.start();
		t2.start();
		t3.start();
		
		/**
		 * 
		 *  thread[ Thread-1] -->t[2]
			thread[ Thread-0] -->t[2]
			thread[ Thread-2] -->t[2]
			thread[ Thread-2] -->t[4]
			thread[ Thread-2] -->t[6]
			thread[ Thread-0] -->t[4]
			thread[ Thread-1] -->t[4]
			thread[ Thread-0] -->t[6]
			thread[ Thread-1] -->t[6]

		 */
		/**
		 * ThreadLocal 跟线程同步的区别
		 * 
		 * 在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。这时该变量是多个线程共享的，使用同步机制要求程序慎密地分析什么时候对变量进行读写，
		 * 什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大
		 * 
		 * 而ThreadLocal则从另一个角度来解决多线程的并发访问。ThreadLocal会为每一个线程提供一个独立的变量副本，从主内存中拷贝了一份变量到线程自身的堆中，从而隔离了多个线程对数据的访问冲突。因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。
		 * ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal
		 
		 * 概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。
		 * 前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响
		 *
		 *
		 *
		 *
		 */
	}
	
	private static class TestClient extends Thread {
		private ThreadLocalDemo t;
		private String name;
		
		public TestClient(ThreadLocalDemo t, String name){
			this.t = t;
			this.name = name;
		}
		
	    public void run(){
	    	for(int i=0;i<3;i++){
	    		System.out.println("thread[ "+Thread.currentThread().getName()+"] -->t["+
	    	      t.getNextNum()+"]");
	    	}
	    }
	}
	
}


