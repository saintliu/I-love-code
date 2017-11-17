package com.cn.saint.ioprogram.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/** 
* @author saint
* @version 创建时间：2017年8月28日 上午11:14:05 
* 类说明 
* 
* 特点：
* 统一使用Channel高速通道来传输数据，不再使用输入/输出流。
* 使用buffer缓冲区，增加数据传输的吞吐量。
* 使用selector多路复用注册器，用于注册网络操作（读写）事件，驱动服务器socketchannel处理模块处理事件。
* 使用selector key，归类了事件类型，包含了：
*    a. 信息包所属客户端通道信息
*    b. 事件类型  ACCEPT / CONNECT / READ / WRITE
* 
* 创建NIO服务端的主要步骤如下：
* 1. 打开ServerSocketChannel， 监听客户端连接
* 2. 绑定监听端口，设置连接为非阻塞模式
* 3. 创建单线程，创建多路复用器selector并启动线程
* 4. 将ServerSocketChannel注册到单线程中的Selector上，监听acceptor事件
* 5. Selector轮询事件类型为accept / connect / read / write
* 6. 如果有以上事件，就交由处理器处理
* 7. 在处理器中，设置客户链路为非阻塞模式
* 8. 并且将新接入的客户端注册到Selector上，监听读操作，读取客户端发送的网络消息
* 9. 异步读取客户端消息到缓冲区
* 10. 对buffer编码，将解码成功的消息封装成Task
* 11. 将应答消息编码为Buffer，调用SocketChannel的write将消息异步发送给客户端
* 
* 注册顺序为  accept -> read -> write 
* 
* 
* NIO是基于事件驱动，对服务器占用率减少
* 缓冲区，加快的传输效率
* 一个线程处理多个客户端读写操作。
* 
* 
* 
*/
public class ServerHandler implements Runnable{

	private Selector  selector;
	private ServerSocketChannel serverSocketChannel;
	private volatile boolean started;
	
	/**
	 * 构造方法
	 * @throws IOException 
	 * 
	 */
	public ServerHandler(int port) throws IOException {
		//创建选择器
		selector = Selector.open();
		//打开监听器
		serverSocketChannel = ServerSocketChannel.open();
		//如果为true, 则此通道将被用于阻塞模式，否则为非阻塞
		serverSocketChannel.configureBlocking(false);
		//绑定端口
		serverSocketChannel.socket()
		   .bind(new InetSocketAddress(port), 1024);
		//监听客户端请求
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		//标记服务端已开启
		started = true;
		System.out.println("服务端已启动，端口号："+port);
	}
	public void stop() {
		started = false;
	}
	
	public void run() {
		while (started) {
			//无论是否有读写事件发生，selector每个1s被唤醒一次
			try {
				selector.select(1000);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//阻塞，只有当至少一个注册的事件发生的时候才会继续
			//selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();
			SelectionKey key= null;
			while(iterator.hasNext()) {
				key = iterator.next();
				iterator.remove();
				try {
					handleInput(key);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void handleInput(SelectionKey key) throws IOException {

		if(key.isValid()) {
			//处理新接入的请求信息
			if(key.isAcceptable()) {
				ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
				//通过serversocketchannel的accept创建socketchannel实例
				//完成该操作意味着完成TCP三次握手，tcp物理链路正式建立
				SocketChannel sChannel = serverSocketChannel.accept();
				//设置为非阻塞
				sChannel.configureBlocking(false);
				//注册为读
				sChannel.register(selector, SelectionKey.OP_READ);
			}
			//如果是读消息
			if(key.isReadable()) {
				SocketChannel sChannel = (SocketChannel) key.channel();
				//创建bytebuffer, 并开辟一个1M的缓冲区
				ByteBuffer buffer = ByteBuffer.allocate(1024);
				//读取请求，返回读取的字节数
				int readBytes = sChannel.read(buffer);
				if(readBytes > 0) {
					//将缓冲区当前的limit设置为position=0, 用户后续对缓冲区的读取操作
					buffer.flip();
					//根据缓冲区可读字节数创建字节数组
					byte[] bytes = new byte[buffer.remaining()];
					//将缓冲区可读字节数组复制到新建的数组中
					buffer.get(bytes);
					String expression = new String(bytes, "utf-8");
					System.out.println("服务器收到消息:"+expression);
					//处理数据
					String result = expression;
					//发送应答消息
					doWrite(sChannel, result);
				} 
				//没有读取到字节
				else if(readBytes <0){
					key.cancel();
					sChannel.close();
				}
			}
		}
	}
	//异步发送应答消息
	private void doWrite(SocketChannel sChannel, String result) throws IOException {

		//将消息编码为字节数组
		byte[] bytes = result.getBytes();
		//根据数组容量创建byteBuffer
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		//将字节数组复制到缓冲区
		writeBuffer.put(bytes);
		//flip用于切换读写模式
		writeBuffer.flip();
		//发送缓冲区的字节数组
		sChannel.write(writeBuffer);
	}
}
