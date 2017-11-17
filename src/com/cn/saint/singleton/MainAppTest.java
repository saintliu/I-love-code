package com.cn.saint.singleton;
/** 
* @author saint
* @version 创建时间：2017年9月21日 下午8:53:05 
* 类说明 
*/
public class MainAppTest {

	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance(); 
		singleton1.setName("hello1");
		Singleton singleton2 = Singleton.getInstance();
		singleton2.setName("hello2");
		Singleton singleton3 = new Singleton();
		singleton3.setName("hello3");
		
		singleton1.printInfo();
		singleton2.printInfo();
		singleton3.printInfo();
		
		System.out.println(singleton1 == singleton2);
		System.out.println(singleton1 == singleton3);
		/**
		 *  name = hello2
			name = hello2
			name = hello3
			true
			false
		 */
	}
}
