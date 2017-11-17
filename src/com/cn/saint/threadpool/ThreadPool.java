package com.cn.saint.threadpool;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * C3P0, DBCP，Druid都是面向于对数据库连接的连接管理
 * @author Lenovo
 *
 *ExecutorService 是Java提供的线程池，实现Executor
 *
 *假设一个服务器完成一项任务所需时间为：T1 创建线程时间，T2 在线程中执行任务的时间，T3 销毁线程时间。

    如果：T1 + T3 远大于 T2，则可以采用线程池，以提高服务器性能。
                一个线程池包括以下四个基本组成部分：
                1、线程池管理器（ThreadPool）：用于创建并管理线程池，包括 创建线程池，销毁线程池，添加新任务；
                2、工作线程（PoolWorker）：线程池中线程，在没有任务时处于等待状态，可以循环的执行任务；
                3、任务接口（Task）：每个任务必须实现的接口，以供工作线程调度任务的执行，它主要规定了任务的入口，任务执行完后的收尾工作，任务的执行状态等；
                4、任务队列（taskQueue）：用于存放没有处理的任务。提供一种缓冲机制
                
 线程池技术正是关注如何缩短或调整T1,T3时间的技术，从而提高服务器程序性能的。
 它把T1，T3分别安排在服务器程序的启动和结束的时间段或者一些空闲的时间段，这样在服务器程序处理客户请求时，不会有T1，T3的开销了。
    线程池不仅调整T1,T3产生的时间段，而且它还显著减少了创建线程的数目，看一个例子：
    假设一个服务器一天要处理50000个请求，并且每个请求需要一个单独的线程完成。
    在线程池中，线程数一般是固定的，所以产生线程总数不会超过线程池中线程的数目，而如果服务器不利用线程池来处理这些请求则线程总数为50000。
    一般线程池大小是远小于50000。所以利用线程池的服务器程序不会为了创建50000而在处理请求时浪费时间，从而提高效率。

    代码实现中并没有实现任务接口，而是把Runnable对象加入到线程池管理器（ThreadPool），然后剩下的事情就由线程池管理器（ThreadPool）来完成了
 *
 * newCachedThreadPool - 在有任务时才创建新线程，空闲线程被保留60秒
 * newFixedThreadPool  - 线程池中包含固定数目的线程，空闲线程会一直保留
 * newSingleThreadExecutor - 线程池中只有一个线程，依次执行每个任务
 * newScheduledThreadPool  - 线程池按时间来执行任务，允许用户设定计划执行任务的时间，参数表示线程池中线程的最小数目，当任务较多时，可能会创建更多的县城来执行
 * newSingleThreadScheduledExecutor - 线程池中只有一个工作线程，按时间计划来执行任务
 */
public class ThreadPool {
	
	public static void main(String args[]) throws InterruptedException{
		
		//Executor <- ExecutorService <- AbstractExecutorService <- ThreadPoolExecutor 
	    //ExecutorService executorService = Executors.newCachedThreadPool();
		//提交任务
		//executorService.submit(task);
		ThreadPoolExecutor executorService = 
				new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), r->{
			Thread thread = new Thread(r);
			return thread;
		}, new ThreadPoolExecutor.AbortPolicy());
		System.out.println("线程池初始化完毕");
		
		while(true) {
			System.out.println(executorService.getActiveCount());
		}
		
	}
}

