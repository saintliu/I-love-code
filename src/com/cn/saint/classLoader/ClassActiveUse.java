package com.cn.saint.classLoader;
/** 
* @author saint
* @version 创建时间：2017年9月10日 下午3:21:11 
* 类说明 
*/
public class ClassActiveUse {

	public static void main(String[] args) {
		//主动使用
	//	new Obj();
		//会初始化父类
	//	System.out.println(Obj.salary);
		
		//不会初始化类，因为他是final修饰常量，在加载过程中直接放入常量池-->方法区
		System.out.println(Obj.x);
		
		//子类访问父类的静态变量，会初始化父类，但不会初始化子类
		System.out.println(Child.salary);
		//类只会初始化一次
		System.out.println(Obj.salary);
	}
}

class Obj {
	
	public static long salary = 100000L;
	
	public static final int x = 200; 
	
	static {
		System.out.println("obj 静态方法快被初始化");
	}
	
	public Obj() {
		System.out.println("obj 构造方法被初始化");
	}
}


class Child extends Obj {
	static {
		System.out.println("子类 静态方法初始化");
	}
}