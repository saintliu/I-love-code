package com.cn.saint.Proxy.CGLib;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/** 
* @author saint
* @version 创建时间：2017年9月4日 下午5:33:02 
* 类说明 
*/
public class CglibProxy implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();
	//设置需要创建子类的类
	public Object getProxy(Class clazz) {
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		//通过字节码技术动态创建子类实例
		return enhancer.create();
	}
	
	//实现methodInterceptor接口方法
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
        System.out.println("前置代理");
        //通过代理类调用父类方法
        Object result = arg3.invokeSuper(arg0, arg2);
        System.out.println("后置代理");
		return result;
	}
}
