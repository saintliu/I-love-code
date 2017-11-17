package com.cn.saint.nio.onlineImpl;
/** 
* @author saint
* @version 创建时间：2017年9月9日 下午1:42:41 
* 类说明 
*/

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOClient {

	SocketChannel client; 
	Selector selector; 
	ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
	ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
	
	public NIOClient() throws IOException {
        client = SocketChannel.open();
        
        client.configureBlocking(false);
        client.connect(new InetSocketAddress("localhost", 9000));
        selector = Selector.open();
        
        client.register(selector, SelectionKey.OP_CONNECT);
        
	}
	
	public void session() throws IOException {
		if(client.isConnectionPending()) {
			client.finishConnect();
			client.register(selector, SelectionKey.OP_WRITE);
			System.out.println("已经连接服务器，请发送你的信息");
		}
		
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
             String msg = scanner.nextLine();
             if("".equals(msg)) {
            	      continue;
             }
             if("quit".equals(msg)) {
            	 System.exit(0);
             }
             process(msg);
		}
		
	}

	private void process(String msg) {
         boolean waitHelp = true;
         Iterator<SelectionKey> iterator = null;
         Set<SelectionKey> keys = null;
         
         while(waitHelp) {
        	     try {
        	    	     int readys = selector.select();
        	    	     if(readys == 0) 
        	    	    	      continue;
        	    	     
        	    	     keys = selector.selectedKeys();
        	    	     
        	    	     iterator = keys.iterator();
        	    	     
        	    	     while(iterator.hasNext()) {
        	    	    	       SelectionKey key = iterator.next();
        	    	    	       if(key.isValid() && key.isWritable()) {
        	    	    	    	        sendBuffer.clear();
        	    	    	    	        sendBuffer.put(msg.getBytes());
        	    	    	    	        sendBuffer.flip();
        	    	    	    	        
        	    	    	    	        client.write(sendBuffer);
        	    	    	    	        client.register(selector, SelectionKey.OP_READ);
        	    	    	       } else if(key.isValid() && key.isReadable()) {
        	    	    	    	           receiveBuffer.clear();
        	    	    	    	           int len = client.read(receiveBuffer);
        	    	    	    	           if(len >0) {
        	    	    	    	        	       receiveBuffer.flip();
        	    	    	    	        	       System.out.println("服务端反馈的信息 "+new String(receiveBuffer.array()));
        	    	    	    	        	       client.register(selector, SelectionKey.OP_WRITE);
        	    	    	    	        	       waitHelp = false;
        	    	    	    	           }
        	    	    	       }
        	    	    	       
        	    	    	       iterator.remove();
        	    	     }
        	     } catch (Exception e) {
					((SelectionKey)keys).cancel();
					try {
						client.socket().close();
						client.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					return;
			}
         }
	}
	
	public static void main(String[] args) throws IOException {
		 new NIOClient().session();
	}
}
