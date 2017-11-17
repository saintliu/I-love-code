package com.cn.saint.Proxy.CGLib;
/** 
* @author saint
* @version 创建时间：2017年9月4日 下午5:38:40 
* 类说明 
*/

/**
 * 当目标类没有实现任何接口时，使用cglib来创建代理 
 * 
 * Code Generation Library   ==》 cglib
 *
 */
public class DoCGLib {

	public static void main(String[] args) {

		CglibProxy proxy = new CglibProxy();
		//通过生成子类的方式创建代理类
		SayHello proxyImpl = (SayHello) proxy.getProxy(SayHello.class);
		//执行以下方法时，会先执行intercept方法
		proxyImpl.say();
	}
}
