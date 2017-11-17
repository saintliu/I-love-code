package com.cn.saint.tomcatImpl;

import java.io.IOException;
import java.io.InputStream;

/** 
* @author saint
* @version 创建时间：2017年9月8日 下午8:08:16 
* 类说明 
*/
public class HttpRequestII {

	//根据客户端的不同请求uri，而响应不同的资源给客户端
    
	private String uri; 
	
	
	public String getUri() {
		return uri;
	}


	public HttpRequestII(InputStream inputStream) throws IOException {
         resovleRequest(inputStream);
	}


	private void resovleRequest(InputStream inputStream) throws IOException {
		//从输入流中接收字节，到字节数组中
		byte[] buffer = new byte[1024];
		int length = inputStream.read(buffer);
		//如果接收的字节数组大于0
		if(length > 0) {
			//将读取出来的字节信息转化成明文信息
			String msg = new String(buffer, 0, length);
			System.out.println("【从客户端发来的信息】"+msg);
			
			//解析uri  GET /index.html HTTP/1.1
			uri = msg.substring(msg.indexOf("/"), msg.indexOf("HTTP/1.1")-1);
			System.out.println("uri========"+uri);
			
		} else {
			System.out.println("bad request");
		}
	}
}
