package com.cn.saint.variousTest;
/** 
* @author saint
* @version 创建时间：2017年9月15日 下午4:20:12 
* 类说明 
*/
public class Test4 {

	public static void main(String[] args) {
		Test4 base = new Test4();
	    Sub1 sub1 = (Sub1)base;
	}
	
	public class Sub extends Test4{};
	public class Sub1 extends Test4{};
}

