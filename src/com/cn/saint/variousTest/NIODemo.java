package com.cn.saint.variousTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


/** 
* @author saint
* @version 创建时间：2017年11月9日 下午5:15:21 
* 类说明 
*/
public class NIODemo {
	
	//通道选择器
	private Selector selector; 

	public void initServer(int port) throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		channel.socket().bind(new InetSocketAddress(port));
	    //打开选择器
		this.selector = Selector.open();
		//绑定选择器和通道
		channel.register(selector,SelectionKey.OP_ACCEPT); 
		
		System.out.println("服务已启动。。。");
	}
	
	public void listenerSelector() throws IOException {
		//轮询监selector
		while(true) {
			selector.select();
			Iterator<?> itKey = selector.selectedKeys().iterator();
			while(itKey.hasNext()) {
				SelectionKey key = (SelectionKey) itKey.next();
				itKey.remove();
				//处理请求
				handle(key);
			}
		}
	}

	/**
	 * 处理客户端请求
	 * @param itKey
	 * @throws IOException 
	 */
	private void handle(SelectionKey itKey) throws IOException {
         if(itKey.isAcceptable()) {
        	     //如果是客户连接请求
        	    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) itKey.channel();
            SocketChannel socketChannel= serverSocketChannel.accept();
            //接收客户端发送的信息时，需要给通道设置读的权限
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("客户端连接上来了");
         } else if(itKey.isReadable()) {
        	    //处理读的事件
        	    SocketChannel socketChannel = (SocketChannel) itKey.channel();
        	    ByteBuffer buffer = ByteBuffer.allocate(1024);
        	    int readData = socketChannel.read(buffer);
        	    if(readData > 0) {
        	    	   String info = new String(buffer.array(),"GBK").trim();
        	    	   System.out.println("服务端收到数据: "+info);
        	    } else {
        	    	   System.out.println("客户端关闭了");
        	    	   socketChannel.close();
        	    }
         }
	}
	
	public static void main(String[] args) throws IOException {
		NIODemo demo = new NIODemo();
		demo.initServer(8888);
		demo.listenerSelector();
	}
}

/**  启动服务端
 * 1. 打开终端
 * 2. 输入  telnet localhost 8888， 这样就创建了一个客户端
 * 
 */
