package com.cn.saint.classLoader;
/** 
* @author saint
* @version 创建时间：2017年9月10日 上午8:26:38 
* 类说明 
* 
* 在类加载过程中，分为3步骤：加载、链接(会对静态变量进行初始化默认值)、初始化(为静态变量赋予正确的初始值)
*/
public class ClassDemo2 {
	
	/**
	 * instance = null
	 * x = 0
	 * y = 0 
	 * 
	 * 链接步骤
	 * x++ => x = 1
	 * y++ => y = 1
	 * 
	 * 初始化步骤
	 * x = 0
	 * y = 1
	 */
//	private static ClassDemo2 instance = new ClassDemo2(); 


	public static int x = 0;
	public static int y;
	/**
	 * 
	 * x=0
	 * y=0
	 * instance = null 
	 * 
	 * 链接步骤
	 * instance = new instance
	 * x++ => x=1
	 * y++ => y=1
	 * 
	 * 初始化步骤
	 * 
	 * x = 1
	 * y = 1
	 * 
	 * 所以结果是 x=1, y=1
	 * 
	 */
	private static ClassDemo2 instance = new ClassDemo2(); 
	
	public ClassDemo2() {
		x++;
		y++;
	}
	
	public static ClassDemo2 getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		 ClassDemo2 classDemo2 = getInstance();
		 System.out.println("x=" +classDemo2.x);
		 System.out.println("y=" +classDemo2.y);
	}
}
