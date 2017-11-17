package com.cn.saint.variousTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.ListableBeanFactory;

/** 
* @author saint
* @version 创建时间：2017年9月9日 下午12:15:41 
* 类说明 
*/
public class WriteThread extends Thread{

	private List<Integer> list;
	private List<AtomicInteger> list2;
	
	private Lock lock;
	
	/*public WriteThread(List<Integer> list) {
		this.list = list;
	}*/
	
	public WriteThread(List<AtomicInteger> list2) {
        this.list2 = list2;
	}
	
	public WriteThread(List<Integer> list, Lock lock) {
		this.list = list;
		this.lock = lock;
	}

	/*public void run() {
		int i = 1;
		while (i < 6) {
			System.out.println("写入数据是:" + i);
			list.add(i);
			i++;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}*/
	
  /*public void run() {
	int i = 1;
	while (i < 6) {
		System.out.println("写入数据是:" + i);
		list2.add(new AtomicInteger(i));
		i++;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
	
	public void run() {
		int i = 1;
		while (i < 6) {
			lock.lock();
			System.out.println("写入数据是:" + i);
			list.add(i);
			i++;
			lock.unlock();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				
			}
		}
}
}
