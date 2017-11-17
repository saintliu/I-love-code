package com.cn.saint.ioprogram.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Random;

/** 
* @author saint
* @version 创建时间：2017年8月28日 上午9:58:57 
* 类说明 
*/
public class AppTest {

	public static void main(String[] args) throws InterruptedException {

		//启动服务器
		new Thread(new Runnable() {
			public void run() {

				try {
					//测试BIO阻塞式服务器
					//Server.start();
					//测试NIO
					  ThreadPool_Server.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		//避免客户端先于服务器启动前执行代码
		Thread.sleep(4000);
		
		final char operation[] = {'+','-','*','/'};
		final Random random = new Random(System.currentTimeMillis());
		//启动客户端
		new Thread(new Runnable() {
			int i=0;
			public void run() {
				while(true && i<10) {
					//随机生成算术表达式
					String expression = 
							random.nextInt(10)+""+operation[random.nextInt(4)]
									+(random.nextInt(10));
					Client.send(8080, expression);
					i++;
				}
			}
		}).start();
	}

}
