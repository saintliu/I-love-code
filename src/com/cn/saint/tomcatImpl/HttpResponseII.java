package com.cn.saint.tomcatImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author saint
* @version 创建时间：2017年9月8日 下午8:08:28 
* 类说明 
*/
public class HttpResponseII {
	
	private OutputStream outputStream;

	public HttpResponseII(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void writeFile(String filePath) throws IOException {
		//根据客户端请求路径，找到对应文件。这里需要引入一个文件读取流
		FileInputStream fileInputStream = new FileInputStream(filePath);
		
		byte[] buffer = new byte[1024];
		//读取服务端信息
		StringBuffer stringBuffer = new StringBuffer();

		//构建一个响应头信息 200|OK
		stringBuffer.append("HTTP/1.1 200 OK\n");
		stringBuffer.append("Content-Type: text/html;charset=UTF-8\n");
		stringBuffer.append("\r\n");
		//输出响应头
		outputStream.write(stringBuffer.toString().getBytes());
		
		int length = 0;
		while((length=fileInputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, length);
		}
		//代表文件已经读完，需要关闭流
		fileInputStream.close();
		outputStream.flush();
		outputStream.close();
	}
	
}
