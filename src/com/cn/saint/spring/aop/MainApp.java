package com.cn.saint.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* @author saint
* @version 创建时间：2017年9月21日 下午3:40:17 
* 类说明 
*/
public class MainApp {

	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext("classpath:springaop.xml");
		
		UserService userService = (UserService) context.getBean("user");
	 //   UserService userService = new UserServiceImpl();
		
		/**
		 * Spring Aop 通过动态代理机制，ReflectiveMethodInvocation 
		 * 
		 * // We need to create a method invocation...
				invocation = new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, chain);
				// Proceed to the joinpoint through the interceptor chain.
				retVal = invocation.proceed();
		 * 
		 */
		userService.findAll();
	}
}
