package com.cn.saint.variousTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

import org.springframework.cglib.core.Local;

/** 
* @author saint
* @version 创建时间：2017年9月9日 下午12:13:08 
* 类说明 
*/
public class ReadThread extends Thread{

	private List<Integer> list;
	
	private Lock lock;
	
	private List<AtomicInteger> list2;
	
	/*public ReadThread(List list) {
		this.list = list;
	}*/
	
	public ReadThread(List<AtomicInteger> list2) {
		this.list2 = list2;
	}
	
	public ReadThread(List<Integer> list, Lock lock) {
		this.list = list;
		this.lock = lock;
	}

	/*public void run() {
		int j = 0 ;
		while(true && j<5) {
	        System.out.println("读取的信息是:"+list.get(list.size()-1));
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            j = list.get(list.size()-1);
		}
	}*/
	
	/*public void run() {
		int j = 0;
		while(true && j<5) {
	        System.out.println("读取的信息是:"+list2.get(list2.size()-1));
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            j = list2.get(list2.size()-1).get();
		}
	}*/
	
	public void run() {
		int j = 0;
		while(true && j<5) {
			lock.lock();
	        System.out.println("读取的信息是:"+list.get(list.size()-1));
	        j = list.get(list.size()-1);
	        lock.unlock();
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
			}
		}
	}
}
