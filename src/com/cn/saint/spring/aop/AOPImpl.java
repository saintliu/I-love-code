package com.cn.saint.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/** 
* @author saint
* @version 创建时间：2017年9月21日 下午3:23:01 
* 类说明 
*/
@Aspect
public class AOPImpl {

	//@Pointcut("execution(* com.cn.saint.spring.aop.*.find*(..))")
	public void checkUser() {
		System.out.println("*********the system is searching for you....");
	}
	
	//@Pointcut("execution(* com.cn.saint.spring.aop.*.add*(..))")
	public void checkAdd() {
		System.out.println("**********<<Add User>>**************");
	}
	
	//@Before("checkUser()")
	public void beforeCheck() {
		System.out.println(">>>>>>>>>>准备搜查用户..............");
	}
	
	//@After("checkUser()")
	public void afterCheck() {
		System.out.println(">>>>>>>>>搜查用户完毕...............");
	}
	
	//@Before("checkAdd()")
	public void beforeAdd() {
		System.out.println(">>>>>>>>增加用户--检查ing............");
	}
	
	//@After("checkAdd()")
	public void afterAdd() {
		System.out.println(">>>>>>>>>>增加用户完毕,未发现问题.......");
	}
}
