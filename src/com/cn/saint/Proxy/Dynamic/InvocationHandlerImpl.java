package com.cn.saint.Proxy.Dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.cn.saint.Proxy.Girl;
import com.cn.saint.Proxy.TeacherCang;

/** 
* @author saint
* @version 创建时间：2017年8月30日 下午2:16:03 
* 类说明 
*/
/**
 * 动态代理类,使用动态代理，可以控制对真实对象的访问，以及对功能的增强。
 * 
 * spring aop是基于动态代理实现，其实就是在业务实现方法前后/环绕时，执行安全/日志/事务相关的方法。
 * 并且相关切点/切面配置可以在xml里实现
 * 
 * 在java的动态代理机制中，有两个重要的类或接口，一个是 InvocationHandler(Interface)、另一个则是 Proxy(Class)
 * 每一个动态代理类都必须要实现InvocationHandler这个接口，并且每个代理类的实例都关联到了一个handler，
 * 当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由InvocationHandler这个接口的 invoke 方法来进行调用。
 * 
 */
public class InvocationHandlerImpl implements InvocationHandler {

	//被代理真实对象
	private Object actor;
	
	public InvocationHandlerImpl(Object actor) {
		super();
		this.actor = actor;
	}

	/**
	 * 参数
	 * proxy : 
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//前增强功能
		DosomethingBefore();
		//真正的业务方法
		Object ret = method.invoke(actor, args);
		//后增强功能
		DosomethingAfter();
		
		return ret;
	}
	
	private void DosomethingAfter() {
        System.out.println("【经纪人】用过了，感觉咋样");		
	}

	private void DosomethingBefore() {
        System.out.println("【经纪人】我试过了，不错,推荐给你");		
	}

	
	//测试方法
	public static void main(String[] args) {
		//创建实例对象
		TeacherJia teacherJia = new TeacherJia();
		//创建invocationHandler对象，参数为真实代理对象
		InvocationHandler iHandler = new InvocationHandlerImpl(teacherJia);
		//创建动态代理类对象 （同步生成$ProxyX对象）
		/**
		 * loader:　　一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行加载
  		   interfaces:　　一个Interface对象的数组，表示的是我将要给我需要代理的对象提供一组什么接口，如果我提供了一组接口给它，那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组接口中的方法了
           invocationHandler:　　一个InvocationHandler对象，表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个InvocationHandler对象上
		 */
		Boy jingJiRenProxy = (Boy) Proxy.newProxyInstance(teacherJia.getClass().getClassLoader(), teacherJia.getClass().getInterfaces(), iHandler);
		//执行date方法时，原理就是在proxy对象通过invocationHandler执行invoke方法
		jingJiRenProxy.date(165);
		//com.sun.proxy.$Proxy0
		System.out.println(jingJiRenProxy.getClass().getName());
		//sun.misc.Launcher$AppClassLoader
		System.out.println(jingJiRenProxy.getClass().getClassLoader().getClass().getName());
		System.out.println("===========================");
		
		TeacherCang teacherCang  = new TeacherCang();
		InvocationHandler iHandler1 = new InvocationHandlerImpl(teacherCang);
		
		Girl jingJiRenProxy1 = (Girl) Proxy.newProxyInstance(teacherCang.getClass().getClassLoader(), teacherCang.getClass().getInterfaces(), iHandler1);
		jingJiRenProxy1.date(165);
	}
}
