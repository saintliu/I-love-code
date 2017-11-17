package com.cn.saint.variousTest;
/** 
* @author saint
* @version 创建时间：2017年9月14日 上午7:31:00 
* 类说明 
*/
public class Test3 {

	public static void main(String[] args) {
		
		Hello hello = null;
		hello.world();
		int i = 0;
		while(i<(++i)+1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("i="+i);
		}
	}
}

class Hello {
	public static void world() {
		System.out.println("hello");
	}
}