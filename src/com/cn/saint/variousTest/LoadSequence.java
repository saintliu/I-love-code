package com.cn.saint.variousTest;
/** 
* @author saint
* @version 创建时间：2017年11月15日 上午9:23:24 
* 类说明 
*/
class Test8Parent {
    public Test8Parent() {
		System.out.println("test8parent");
	}
    {System.out.println("i am parent");}
    
    static {System.out.println("static parent");}
}

public class LoadSequence extends  Test8Parent{
	public LoadSequence() {
		System.out.println("Test8");
	}
	
	{System.out.println("i am test8");}
	
	static {System.out.println("static test8");}
	
	public static void main(String[] args) {
		System.out.println("---------------main start-----------------");
		new LoadSequence();
		System.out.println("--------------another call----------------");
		new LoadSequence();
		System.out.println("---------------main end--------------------");
	}
}


/**
 * static parent
 * static test8
 * test8parent
 * test8
 * i am parent
 * i am test8
 * Test8
 * 
 * static parent
	static test8
	---------------main start-----------------
	i am parent
	test8parent
	i am test8
	Test8
	--------------another call----------------
	i am parent
	test8parent
	i am test8
	Test8
	---------------main end--------------------
 * 
 */



