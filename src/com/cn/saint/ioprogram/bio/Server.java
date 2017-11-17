package com.cn.saint.ioprogram.bio;
/** 
* @author saint
* @version 创建时间：2017年8月28日 上午9:36:09 
* 类说明 
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 采用BIO通信模型的服务端，通常由一个独立的acceptor线程负责监听客户端的连接，他接受到客户端连接请求之后
 * 为每个客户端创建一个新的线程进行链路处理，完成后，将结果输出给客户端，线程销毁
 * 
 * 使用输入/输出流对数据进行处理，单向性。
 * 
 * Client 0  ----->                      <----thread 0
 * Client 1  ----->         Acceptor     <----thread 1              
 * Client 2  ----->                      <----thread 2
 * Client 3  ----->                      <----thread 3
 * 
 * @author liuyusheng
 *
 */

public class Server {
   //默认的端口号
	private static int default_port=8080; 
	//单例的ServerSocket
	private static ServerSocket serverSocket;
	//根据传入参数设置监听端口，如果没有参数调用以下方法就是用默认值
	public static void start() throws IOException {
		start(default_port);
	}
	
	public synchronized static void start(int port) throws IOException {
		if(serverSocket != null) return;
	try {	
		//通过构造函数创建ServerSocket
		serverSocket = new ServerSocket(port);
		System.out.println("【服务器】服务器已启动，端口号:"+port);
		
		//使用无限循环监听客户端连接
		//如果没有端口合法且空闲，服务端就监听成功
		while(true) {
			Socket socket = serverSocket.accept();
			//当有新的客户端接入时，执行下面的代码
			//创建一个新的线程处理这个请求
			new Thread(new ServerHandler(socket)).start();
		}
	}finally {
		//清理
		if(serverSocket != null) {
			System.out.println("服务器已关闭..");
			serverSocket.close();
			serverSocket = null;
		}
	}
 }

}
