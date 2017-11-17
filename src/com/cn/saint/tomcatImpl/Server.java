package com.cn.saint.tomcatImpl;
/** 
* @author saint
* @version 创建时间：2017年9月8日 下午7:35:08 
* 类说明 
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * tomcat 1.0 based on BIO
 * @author liuyusheng
 *
 */
public class Server {
	
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
			while(true) {
				client = serverSocket.accept();
                 //处理客户端请求
				InputStream inputStream = client.getInputStream();
				//从输入流中接收字节，到字节数组中
				byte[] buffer = new byte[1024];
				int length = inputStream.read(buffer);
				//如果接收的字节数组大于0
				if(length > 0) {
					//将读取出来的字节信息转化成明文信息
					String msg = new String(buffer, 0, length);
					System.out.println("【从客户端发来的信息】"+msg);
					
					//响应客户端的请求
					OutputStream outputStream = client.getOutputStream();
					//读取服务端信息
					
					StringBuffer stringBuffer = new StringBuffer();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//构建一个响应头信息 200|OK
					stringBuffer.append("HTTP/1.1 200 OK\n");
					stringBuffer.append("Content-Type: text/html;charset=UTF-8\n");
					stringBuffer.append("\r\n");
					//构建响应内容
					String html = "<html><head><title>欢迎各位同学</title></head><body>"
							+ "当前时间是:"
							+ "<font size = 14 color='red'>"
							+ simpleDateFormat.format(new Date())
							+ "</font>"
							+ "</body></html>";
					
					stringBuffer.append(html);
					//输出
					outputStream.write(stringBuffer.toString().getBytes());
					//强制输出
					outputStream.flush();
					outputStream.close();
					
					client.close();
				} 
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

/*
 * 请求连接为 http://localhost:9999/index.html
 * 
 * 【从客户端发来的信息】GET /index.html HTTP/1.1
*	Host: localhost:9999
*	Upgrade-Insecure-Requests: 1
*	Accept: text/html,application/xhtml+xml,application/xml;q=0.9,;q=0.8
*   User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/603.2.4 (KHTML, like Gecko) Version/10.1.1 Safari/603.2.4
*	Accept-Language: zh-cn
*	Accept-Encoding: gzip, deflate
*	Connection: keep-alive
* 
* 此版本的问题：不管请求的地址是什么，返回的内容都没有变化
* 
* 
*/
