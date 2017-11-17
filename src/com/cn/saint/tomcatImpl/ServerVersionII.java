package com.cn.saint.tomcatImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/** 
* @author saint
* @version 创建时间：2017年9月8日 下午8:06:44 
* 类说明 
*/

/**
 * tomcat 2.0 based on BIO
 * 
 * web版本
 * @author liuyusheng
 *
 */
public class ServerVersionII {

	public static void main(String[] args) {
		//服务器
		ServerSocket serverSocket = null;
		//客户端
		Socket client = null;
		
		int port = 9999;
		
		try {
			serverSocket = new ServerSocket(port);
			System.out.printf("【服务器】初始化完毕, 注册端口是 %s", port);
			
			//监听客户端连接请求
			while (true) {
				client = serverSocket.accept();
				// 处理客户端请求
				InputStream inputStream = client.getInputStream();
				HttpRequestII requestII = new HttpRequestII(inputStream);
				String uri = requestII.getUri();
				// 响应客户端的请求
				OutputStream outputStream = client.getOutputStream();
				HttpResponseII responseII = new HttpResponseII(outputStream);
				
				//传入的uri格式为 /index.html
				responseII.writeFile(uri);
				client.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
