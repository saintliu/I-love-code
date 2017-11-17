package com.cn.saint.threadpool;
/** 
* @author saint
* @version 创建时间：2017年11月6日 下午4:58:49 
* 类说明 
*/
/**
 * 实现了线程执行的顺序
 *
 */
public class JoinTest {

	public static void main(String[] args) {
		Thread thread1 = new Myrunner();
		Thread thread2 = new Myrunner();
		thread1.start();
		try {
			//意思是其他线程等待该线程执行完毕。
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread2.start();
		try {
			thread2.join();
		} catch (Exception e) {
		}
		for(int i=0;i<5;i++) {
			System.out.println("main : "+i);
		}
	}
}

class Myrunner extends Thread {
	
	public void run() {
		for(int i=0;i<5;i++) {
			System.out.println("i am "+ getName());
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}