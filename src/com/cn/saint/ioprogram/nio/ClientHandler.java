package com.cn.saint.ioprogram.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/** 
* @author saint
* @version 创建时间：2017年8月28日 下午4:10:19 
* 类说明 
*/
public class ClientHandler implements Runnable{

	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean started;
	
	public ClientHandler(String ip, int port) {
		this.port = port;
		this.host = ip;
		
		//创建选择器
		try {
			selector = Selector.open();
			//打开监听通道
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			started = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		started = false;
	}
	
	public void run() {
		try {
			doConnect();
		} catch (ClosedChannelException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		//循环遍历selector
		while(started) {
			//无论是否有读写事件发生，selector每隔1s轮询一次
			try {
				selector.select(1000);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();
			SelectionKey key = null;
            while (iterator.hasNext()) {
                  key= iterator.next();
                  iterator.remove();
                  
                  try {
					handleInput(key);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}			
		}
	}

	private void doConnect() throws ClosedChannelException, IOException {
		if(socketChannel.connect(new InetSocketAddress(host,port)));  
        else socketChannel.register(selector, SelectionKey.OP_CONNECT);  
	}

	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()){  
            SocketChannel sc = (SocketChannel) key.channel();  
            if(key.isConnectable()){  
                if(sc.finishConnect());  
                else System.exit(1);  
            }  
            //读消息  
            if(key.isReadable()){  
                //创建ByteBuffer，并开辟一个1M的缓冲区  
                ByteBuffer buffer = ByteBuffer.allocate(1024);  
                //读取请求码流，返回读取到的字节数  
                int readBytes = sc.read(buffer);  
                //读取到字节，对字节进行编解码  
                if(readBytes>0){  
                    //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作  
                    buffer.flip();  
                    //根据缓冲区可读字节数创建字节数组  
                    byte[] bytes = new byte[buffer.remaining()];  
                    //将缓冲区可读字节数组复制到新建的数组中  
                    buffer.get(bytes);  
                    String result = new String(bytes,"UTF-8");  
                    System.out.println("客户端收到消息：" + result);  
                }  
                //没有读取到字节 忽略  
//              else if(readBytes==0);  
                //链路已经关闭，释放资源  
                else if(readBytes<0){  
                    key.cancel();  
                    sc.close();  
                }  
            }  
        }  
	}

	public void sendMsg(String msg) throws IOException {
		socketChannel.register(selector, SelectionKey.OP_READ);  
        doWrite(socketChannel, msg); 		
	}

	private void doWrite(SocketChannel socketChannel2, String request) throws IOException {
		 //将消息编码为字节数组  
        byte[] bytes = request.getBytes();  
        //根据数组容量创建ByteBuffer  
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);  
        //将字节数组复制到缓冲区  
        writeBuffer.put(bytes);  
        //flip操作  
        writeBuffer.flip();  
        //发送缓冲区的字节数组  
        socketChannel2.write(writeBuffer);  		
	}

}
