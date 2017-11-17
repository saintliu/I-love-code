package com.cn.saint.priorityblockingqueue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/** 
* @author saint
* @version 创建时间：2017年10月30日 下午2:24:11 
* 类说明 
*/
public class MainApp {

	public static void main(String[] args) throws InterruptedException {
		PriorityBlockingQueue<Job> queue = 
				new PriorityBlockingQueue<Job>(10, new Comparator<Job>() {

					@Override
					public int compare(Job o1, Job o2) {
                        if(o1.priority < o2.priority) {
                        	    return 1;
                        } else if(o1.priority > o2.priority) {
                        	    return -1;
                        } else {
						return 0;
                        }
					}
				});
	     queue.put(new Job(5));
	     queue.put(new Job(9));
	     
	     for(Job job:queue) {
	    	     System.out.println(job);
	     }
	     
	     queue.put(new Job(7));
	     System.out.println("----------------after put 7--------------------");
	     
	     for(Job job:queue) {
    	     	System.out.println(job);
         }
	     
		Job job = queue.take();
		System.out.println("the element take from queue is"+job);
		
		System.out.println("-----------after take the head element-----------");
		
		 for(Job job1:queue) {
 	     	System.out.println(job1);
         }
	}
}

class Job {
	int priority; 
	
	public Job(int priority) {
		this.priority = priority;
	}
	
	public String toString() {
		return "Job (priority="+priority +")";
	}
}

