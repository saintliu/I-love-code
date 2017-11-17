package com.cn.saint.ioprogram.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/** 
* @author saint
* @version 创建时间：2017年9月9日 上午11:02:03 
* 类说明 
*/
/**
 * 实现文件复制功能
 * @author liuyusheng
 *
 */
public class ChannelTest {
	
	private static FileInputStream fileInputStream;
	private static FileOutputStream fileOutputStream;

	public static void main(String[] args) throws IOException {
		//文件
		File file = new File("/Users/liuyusheng/1.txt");
		fileInputStream = new FileInputStream(file);
		
		fileOutputStream = new FileOutputStream("/Users/liuyusheng/2.txt");
		
		//需要一个buffer
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		
		//创建管道
		FileChannel fileChannel = fileInputStream.getChannel();
		//输出管道
		FileChannel fileChannel2 = fileOutputStream.getChannel();
		
		//从管道中读取数据,并放入到buffer中
		int bytesRead = fileChannel.read(byteBuffer);

		while((fileChannel.read(byteBuffer)) != -1) {
			//切换成写模式(目的是在读写操作过程中，只使用一个缓冲区)
			byteBuffer.flip();
			//写入到另外一个文件
			fileChannel2.write(byteBuffer);
			byteBuffer.clear();
		}
	}

}
