package com.cn.saint.nio.onlineImpl;
/** 
* @author saint
* @version 创建时间：2017年9月9日 下午12:51:36 
* 类说明 
*/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class NIOServer {
	//服务端
	ServerSocketChannel server; 
	private int port = 9000; 
//	InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8080);
	
	//多路复用选择器
	Selector selector; 
	//接收缓冲区
	ByteBuffer receiveByteBuffer = ByteBuffer.allocate(1024);
	ByteBuffer sendByteBuffer = ByteBuffer.allocate(1024);
	
	//缓存服务器发送信息的队列
	Map<SelectionKey, String> serverSessionMsg = new HashMap<>();
	//缓存客户端发送信息的队列
	Map<SelectionKey, String> clientSessionMsg = new HashMap<>();
	
	private static int client_id = 666;
	
	public NIOServer(int port) throws IOException {
		this.port = port;
		server = ServerSocketChannel.open();
		//设置非阻塞
		server.configureBlocking(false);
		server.bind(new InetSocketAddress(this.port));
		
		//加入多路复用选择器
		selector = Selector.open();
		//注册服务器
		server.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("【服务端】服务器初始化完毕！ 监听端口号为: "+this.port);
	}
	
	private void listener() throws IOException {
		while(true) {
			//使用select()查询selector有多少事件
			int eventCount = selector.select();
			if(eventCount == 0) 
				continue;
			//轮询满足状态条件的事件
		//	SelectionKey.OP_ACCEPT
		//	SelectionKey.OP_CONNECT
		//	SelectionKey.OP_READ
		//	SelectionKey.OP_WRITE
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();
			while(iterator.hasNext()) {
				process(iterator.next());
				iterator.remove();
			}
		}
	}
	
	/**
	 * 根据key的不同类型，处理不同的业务逻辑
	 * @throws IOException 
	 */
	public void process(SelectionKey key) {
		SocketChannel client = null;
	try {
		//判断key是否有效,是否可接收状态
		if(key.isValid() && key.isAcceptable()) {
			client = server.accept();
			++client_id;
			client.configureBlocking(false);
			//将客户端设置为可读
			client.register(selector, SelectionKey.OP_READ);
		} else if(key.isValid() && key.isReadable()) {
			receiveByteBuffer.clear();
			//建立通道
			client = (SocketChannel) key.channel();
			int len = client.read(receiveByteBuffer);
			if(len > 0) {
				String msg = new String(receiveByteBuffer.array(), 0, len);
				serverSessionMsg.put(key, msg);
				System.out.println("当前线程ID："+Thread.currentThread().getName()
						+ " 读取来自客户端编号为: "+client_id + "，信息为："+msg);
				
				client.register(selector, SelectionKey.OP_WRITE);
			}
		} else if(key.isValid() && key.isWritable()) {
			if(!serverSessionMsg.containsKey(key)) {
				return;
			}
			client = (SocketChannel) key.channel();
			sendByteBuffer.clear();
			sendByteBuffer.put((serverSessionMsg.get(key)+"您好，我已经完成你的请求").getBytes());
			sendByteBuffer.flip();
			client.write(sendByteBuffer);
			System.out.println("当前线程ID为 "+Thread.currentThread().getName()
					+" 对客户端编号为："+serverSessionMsg.get(key) + " 你好，我已经完成你的请求");
			
			client.register(selector, SelectionKey.OP_READ);
		}
	}catch (Exception e) {
        key.cancel();
        try {
			client.socket().close();
			client.close();
	        System.out.println("客户端 "+clientSessionMsg.get(key) + " 已经非法下线");
	        clientSessionMsg.remove(key);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
	
	public static void main(String[] args) {
		  try {
			new NIOServer(9000).listener();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
