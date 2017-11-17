package com.cn.saint.variousTest;

import java.lang.reflect.Field;

/** 
* @author saint
* @version 创建时间：2017年11月15日 下午2:37:50 
* 类说明 
*/
public class IntegerSwap {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Integer a = 1;
		Integer b = 2;
		
		swap(a,b);
		
		System.out.println("a = "+a);
		System.out.println("b = "+b);
	}

	private static void swap(Integer a, Integer b) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        /**
         * 使用定义临时变量来交换，不能实现，因为a,b指向的地址没有改变 
         */
		/*
		Integer tmp = a;
         a = b;
         b = tmp;*/
		
		Field field = Integer.class.getDeclaredField("value");
		field.setAccessible(true);
	//	int tmp = a.intValue();   //在field.set赋值时，会自动装箱
		Integer tmp = new Integer(a.intValue());
        //修改了integer.cache 数据
		field.set(a, b);
		field.set(b, tmp);
	}
}
