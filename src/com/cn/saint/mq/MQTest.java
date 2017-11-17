package com.cn.saint.mq;
/** 
* @author saint
* @version 创建时间：2017年9月1日 下午12:24:27 
* 类说明 
*/
/**
 * 一个中心： 以消息服务器为中心：接受服务器的消息，发送给客户端
 * 两种模式： 点对点， 发布订阅
 * 三步实现 
 *
 */
public class MQTest {

	public static void main(String[] args) {
		 new Thread(new Runnable() {  
	            public void run() {  
	                for(int i=0;i<5;i++){  
	                    try {  
	                        Thread.currentThread().sleep(i*10000);  
	                        System.out.println("睡了"+i*10+"秒");  
	                    } catch (InterruptedException e) {  
	                        System.out.println("干嘛吵醒我");  
	                    }  
	                }  
	            }  
	        }).start();   
	          
	        for(int i=0;i<50;i++){  
	                System.out.print(i);  
	        }  
	}
}
