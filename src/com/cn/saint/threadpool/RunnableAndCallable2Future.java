package com.cn.saint.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/** 
* @author saint
* @version 创建时间：2017年9月10日 下午5:35:34 
* 类说明 
*/

/**
 * Thread、runnable、callable，其中runnable实现的是run方法，
 * callable实现的是call方法，并且可以返回执行结果。其中runnable可以提交
 * thread来包装，直接启动一个线程来执行。而callable一般是交由executorService
 * 来执行。
 * 
 * 简单来说，executor是runnable和callable的调度容器，future就是对于具体调度任务的
 * 结果。而且future可以检查任务是否完成，也可以撤销任务
 *
 */
public class RunnableAndCallable2Future {

	public static void main(String[] args) {
		//创建一个执行任务的线程池
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		//使用runnable通过future返回结果为空
		//创建一个runnable来调度，等待任务执行完毕，取得返回结果
		Future<?> runnable1 = executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("runnable1 is running");
			}
		});
		try {
			System.out.println("runnable1 = "+runnable1.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		//2. 使用callable通过future能得到返回结果
		// 提交并执行任务，任务启动时返回一个future对象
		Future<String> future = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "result = task1";
			}
		});
		//获得任务结果，通过调用get方法
		try {
			System.out.println("task1 = "+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

/**
 * futuretask 实现了runnable接口，所以可以直接被thread 包装执行。
 * futuretask 的构造函数中可以有runnable 和 callable。
 * futuretask 也实现了future接口，所以可以直接交由executors执行
 *
 */
class FutureTaskTest{
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<String> task = new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("Sleep start.");
				Thread.sleep(10);
				System.out.println("Sleep end.");
				return "time=" +System.currentTimeMillis();
			}
		};
		
		//直接使用thread来执行
		FutureTask<String> ft = new FutureTask<String>(task);
		Thread thread = new Thread(ft);
		thread.start();
		
		System.out.println("waiting execute result");
		System.out.println("result = "+ft.get());
		
		//使用executors来执行
		FutureTask<String> ft2 = new FutureTask<>(task);
		Executors.newSingleThreadExecutor().submit(ft2);
		
		System.out.println("waiting execute result");
		System.out.println("result = "+ft2.get());
	}
}
