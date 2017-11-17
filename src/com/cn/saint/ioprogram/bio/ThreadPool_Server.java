package com.cn.saint.ioprogram.bio;
/** 
* @author saint
* @version 创建时间：2017年8月28日 上午10:44:30 
* 类说明 
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 为了改变这种一连接一线程的模型，我们可以使用线程池来管理线程，其实也是1线程对应一个请求，能保证线程的使用和销毁，减少资源消耗
 * 但是这样限制了线程数量，如果发生大量并发请求，超过最大数量的线程就只能等待，知道线程池中有空闲的线程可以被服用。
 * 
 * Client 0  -->                    Runnable
 * Client 1  -->      acceptor      task
 * Client 2  -->                    Thread Pool
 * Client 3  -->
 *
 */

public class ThreadPool_Server {

	//默认的端口号
	private static int default_port = 8080; 
	private static ServerSocket serverSocket;
	//线程池
	private static ExecutorService executorService = 
			Executors.newFixedThreadPool(60);
	
	public static void start() throws IOException {
		start(default_port);
	}
	
	public synchronized static void start(int port) throws IOException {
		if(serverSocket != null) return;
		
		serverSocket = new ServerSocket(default_port);
		System.out.println("服务器端已启动，端口号："+port);
		
		while(true) {
			Socket socket = serverSocket.accept();
			executorService.execute(new ServerHandler(socket));
		}
	}
}
