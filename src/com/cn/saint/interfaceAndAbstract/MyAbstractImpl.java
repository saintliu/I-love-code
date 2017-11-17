package com.cn.saint.interfaceAndAbstract;
/** 
* @author saint
* @version 创建时间：2017年9月7日 下午5:57:29 
* 类说明 
*/
public class MyAbstractImpl extends MyAbstract{

	/**
	 * 不一定需要重写接口方法
	 */
	
	public void getName() {
        System.out.println("this is implementation function");
	}

	/**
	 * 抽象方法一定要实现
	 */
	@Override
	public void name() {
		
	}
}
