package com.cn.saint.ioprogram.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.script.ScriptException;
/** 
* @author saint
* @version 创建时间：2017年8月28日 上午9:45:29 
* 类说明 
*/
public class ServerHandler implements Runnable {

	private Socket socket;
	public ServerHandler(Socket socket) {
		super();
		this.socket = socket;
	}
	
	public void run() {
       BufferedReader in = null;
       PrintWriter out = null;
       
       try {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		String expression; 
		String result;
		
		while(true) {
			if((expression = in.readLine()) == null) break;
			System.out.println("【服务器】服务器收到信息:"+expression);
			result = Calculator.cal(expression).toString();
		    out.println(result);
		   }
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ScriptException e) {
		e.printStackTrace();
	} finally {
		if(in != null) {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			in = null;
		}
		if(out != null) {
			out.close();
			out = null;
		}
		if(socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			socket = null;
		  }
	  }
   }
}
