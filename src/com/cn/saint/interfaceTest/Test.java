package com.cn.saint.interfaceTest;
/** 
* @author saint
* @version 创建时间：2017年10月9日 下午12:04:29 
* 类说明 
*/
public class Test {

	public static void main(String[] args) {
		Base base = new BaseImpl();
		Base2 base2 = (Base2) base;
		base.addBase(1);
		base2.addBase2();
	}
}
