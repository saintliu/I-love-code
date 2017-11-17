package com.cn.saint.folkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinTest {

	public static void main(String[] args) throws InterruptedException {
		//引入线程池
		ForkJoinPool pool = new ForkJoinPool();
		//提交可分解的任务
		ForkJoinTask<Integer> task = pool.submit(new ForkJoin(0, 10));
		
		try {
			System.out.println(task.get());
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		//线程阻塞，等待所有任务完成
		pool.awaitTermination(2, TimeUnit.SECONDS);
		//关闭线程池
		pool.shutdown();
	}

}
