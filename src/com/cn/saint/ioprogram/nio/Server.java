package com.cn.saint.ioprogram.nio;
/** 
* @author saint
* @version 创建时间：2017年8月28日 上午11:10:59 
* 类说明 
*/

import java.io.IOException;
public class Server {
	
	private static int default_port = 8080;
	private static ServerHandler nio_serverHandler;
	private static void start() throws IOException {
	     start(default_port);
	}
	
	public static synchronized void start(int port) throws IOException {
		if(nio_serverHandler != null) nio_serverHandler.stop();
		
		nio_serverHandler = new ServerHandler(port);
		new Thread(nio_serverHandler, "Server").start();
	}
	
	public static void main(String[] args) throws IOException {
		start();
	}

}
