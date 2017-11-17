package com.cn.saint.folkJoin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

/** 
* @author saint
* @version 创建时间：2017年8月27日 上午11:17:54 
* 类说明 
*/

/**
 * RecursiveTask 有返回值ForkJoinTask，RecursiveAction 无返回值
 * @author liuyusheng
 *
 */
public class ForkJoinDemo {

	public static void main(String[] args) {

		ForkJoinPool pool= new ForkJoinPool();
		
		List<String> names = new ArrayList<String>();
		
		for(int i=0;i<10;i++) {
			names.add("xx小区"+i);
		}
		ForkJoinTask<Integer> task = pool.submit(new HousePrice(names, names.size(), 0));
		//合计结果
		System.out.println(task.join());
        pool.shutdown();		
	}

}

class HousePrice extends  RecursiveTask<Integer>{
	
	private static final long serialVersionUID = 1L;
	private List<String> names;
	private int length; 
	private int start; 
	
	public HousePrice(List<String> names, int length, int start) {
		super();
		this.names = names;
		this.length = length;
		this.start = start;
	}

	@Override
	protected Integer compute() {
		Integer sum = 0;
        if(length<4) {
        	    for(int i=start;i<start+length;i++) {
        	    	   try {
						sum += evalPrice(names.get(i));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
        	    }
        	    
        	    return sum;
        } else {
        	    Integer sum2 = 0;
        	    int split = length / 2;
        	    List<HousePrice> tasks = new ArrayList<HousePrice>();
        	    
        	    tasks.add(new HousePrice(names, split, start));
        	    tasks.add(new HousePrice(names, length - split, start+split));
        	    Collection<HousePrice> collection = invokeAll(tasks);

			for (HousePrice hp : collection) {
				try {
					sum2 += hp.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
			return sum2;
        }
	}
	
	private Integer evalPrice(String name) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println(name+"估价");
		return 1;
	}
}