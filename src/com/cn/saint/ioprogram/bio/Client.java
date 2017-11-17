package com.cn.saint.ioprogram.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/** 
* @author saint
* @version 创建时间：2017年8月28日 上午9:53:42 
* 类说明 
*/
public class Client {
	
	//默认的端口号
	private static int DEFAULT_SERVER_PORT = 8080;
	private static String DEFAULT_SERVER_IP = "127.0.0.1";
	private static void send(String expression) {
		send(DEFAULT_SERVER_PORT, expression);
	}
	
	public static void send(int port, String expression) {
		System.out.println("【客户端】算术表达式为:"+expression);
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			out.println(expression);
			System.out.println("【客户端】_结果为:"+in.readLine());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		     
		}
	}

}
