package com.cn.saint.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo extends Thread{
	
	public void run(){
		super.run();
		System.out.println("extends thread");
	}

	public static void main(String args[]){
		ThreadDemo t = new ThreadDemo();
		t.start();
		/**
		 * start 方法用来启动线程， 如果直接调用run方法，
		 * 他只是一个普通的方法，类似于一个常规方法的调用
		 */
		RunnableDemo r = new RunnableDemo();
		Thread t1 = new Thread(r);
		t1.start();
		/**
		 * runnable没有start 方法，所以需要创建一个线程，然后用此线程来跑方法体run
		 */
		
		/**
		 * runnable callable
		 * 
		 * callable 的call方法可以有返回值和抛出异常
		 * callable可以返回装载有计算结果的future对象
		 * 
		 * 
		 * 但是callable不支持在new Thread()里用，这个只支持runnable， 所以要用到
		 * ExecutorService 线程池
		 * 
		 * Future 接口
		 * 
		 * boolean cancel 试图取消对此任务的执行
		 * boolean isCancelled 是否取消
		 * boolean isDone  任务是否完成
		 * V get() 可以等待计算完成，然后获取结果
		 * V get(long timeout) 如有必要，可以最多等待给定的时间后，获取结果
		 */
		
		Callable<String> callable = new Callable<String>(){

			public String call() throws Exception {
				System.out.println( "<Callable> my name is Saint");
				return "success";
			}
		};
		
		/**
		 * futuretask 实现了 runnable 和future接口，所以在executorservice 和thread中使用
		 * 
		 * 使用 FutureTask 的好处是 FutureTask 是为了弥补 Thread 的不足而设计的，
		 * 它可以让程序员准确地知道线程什么时候执行完成并获得到线程执行完成后返回的结果。
		 * FutureTask 是一种可以取消的异步的计算任务，它的计算是通过 Callable 实现的，它等价于可以携带结果的 Runnable，
		 * 并且有三个状态：等待、运行和完成。完成包括所有计算以任意的方式结束，包括正常结束、取消和异常。
		 */
		FutureTask<String> task = new FutureTask<String>(callable);
		
		Thread t2 = new Thread(task);
		t2.start();
	//	task.cancel(true);
	}
}


class RunnableDemo implements Runnable {

	public void run() {
		System.out.println("implement runnable");
	}
	
}