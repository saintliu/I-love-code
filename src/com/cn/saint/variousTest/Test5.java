package com.cn.saint.variousTest;
/** 
* @author saint
* @version 创建时间：2017年9月22日 下午1:14:59 
* 类说明 
*/
public class Test5 extends Parent{

	String name;
	
	/**
	 * 当有自定义构造函数时，系统不会自动创建默认构造函数
	 * @param name
	 */
	public Test5(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
	//	Test5 test5 = new Test5();
		Test5 test5 = new Test5("yes");
		test5.add();
		test5.add2();
		//test5.add1();  ==> 子类只能调用父类非私有函数
	}
}

class Parent {
	public void add() {
		System.out.println(".....name.....");
	}
	
	private void add1() {
		System.out.println(".....name1.....");
	}
	
	protected void add2() {
		System.out.println(".....name2.....");
	}
}
