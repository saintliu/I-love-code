package com.cn.saint.classLoader;
/** 
* @author saint
* @version 创建时间：2017年9月10日 下午3:39:50 
* 类说明 
*/
public class LoaderClass {

	public static void main(String[] args) {
	   MyObject object1 = new MyObject();
	   MyObject object2 = new MyObject();
	   MyObject object3 = new MyObject();
	   MyObject object4 = new MyObject();
	   
	   System.out.println(object1.getClass() == object2.getClass());
	   System.out.println(object1 == object2);  // false, hashcode 不一样
	   System.out.println(object1.hashCode()+"========"+object2.hashCode());
	   System.out.println(object1.equals(object2));  // false
	   System.out.println(object4.getClass());
	}
}

class MyObject {
	
	private String name;
	
	
}
