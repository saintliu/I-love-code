package com.cn.saint.folkJoin;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJoin extends RecursiveTask<Integer>{
	
	//最小执行任务数量
	private static final int threshold = 4;

	private int start;
	
	private int end; 
	
	public ForkJoin(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {

		//当end与start之间的差小于阈值，直接开始执行
		if(end - start < threshold){
			return  IntStream.rangeClosed(start, end).sum();
		} else {
			//当end与start之间的差大于阈值，将任务进行重新切割
			int middle = (start+end)/2;
			ForkJoin left = new ForkJoin(start, middle);
			ForkJoin right = new ForkJoin(middle+1, end);
			
			//并行执行小任务
			left.fork();
			right.fork();
			
			return left.join()+right.join();
			//返回结果可以用
			// sum = left.join+right.join.
		}
	}

}
