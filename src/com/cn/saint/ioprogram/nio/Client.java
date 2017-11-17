package com.cn.saint.ioprogram.nio;
/** 
* @author saint
* @version 创建时间：2017年8月28日 下午4:05:17 
* 类说明 
*/

import java.io.IOException;

public class Client {
	private static String default_host="127.0.0.1";
	private static int default_port = 8080;
	private static ClientHandler clientHandler;
	
	public static void start() {
		start(default_host,default_port);
	}
	
	public static synchronized void start(String ip, int port) {
		if(clientHandler != null) clientHandler.stop();
		
		clientHandler = new ClientHandler(ip, port);
		new Thread(clientHandler, "Client").start();
	}

	public static boolean sendMsg(String msg) throws IOException {
        if(msg.equals("q")) return false;
        clientHandler.sendMsg(msg);
        return true;
	}
	public static void  main(String[] args) {
		start();
	}
}
