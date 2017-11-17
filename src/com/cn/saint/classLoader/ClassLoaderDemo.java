package com.cn.saint.classLoader;
/** 
* @author saint
* @version 创建时间：2017年9月10日 上午8:10:30 
* 类说明 
*/
public class ClassLoaderDemo extends ParentClassLoader{
	//静态变量
	final static int i = 10;
	
	public ClassLoaderDemo() {
		System.out.println("子类构造方法");
	}
	
	static {
		System.out.println("子类静态方法块");
	}
	
	public void name() {
		System.out.println("子类普通方法");
	}

	public static void main(String[] args) {
		new ClassLoaderDemo().name();
		
		/**
		 *  父类静态方法块
			子类静态方法块
			父类构造方法
			子类构造方法
			子类普通方法
		 */
	}
}


class ParentClassLoader{
	//父类静态变量
	final static int j = 4;
	
	public ParentClassLoader() {
		System.out.println("父类构造方法");
	}
	
	static {
		System.out.println("父类静态方法块");
	}
	
	public void name() {
		System.out.println("父类普通方法");
	}
}