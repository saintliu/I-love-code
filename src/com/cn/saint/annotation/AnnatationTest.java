package com.cn.saint.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/** 
* @author saint
* @version 创建时间：2017年9月14日 下午7:26:07 
* 类说明 
*/

public class AnnatationTest {

	public static void main(String[] args) throws Exception {
		/**
		 * 通过反射获取注解，和注解的值
		 */
		Method[] method = Test.class.getDeclaredMethods(); 
		Annotation[] a = method[0].getAnnotations();
		System.out.println(((MyAnnotation)a[0]).id());
		
		if(a.length == 1) {
		// exception 在java.lang包中, 父类是throwable
		throw new Exception(); 
		}
		// throwable 是exception 和error的父类
	//	throw new Error();
	}
}

class Test {
	
	@MyAnnotation(id=3)
	public static void name() {
		
	}
}
