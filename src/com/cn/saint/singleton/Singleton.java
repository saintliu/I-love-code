package com.cn.saint.singleton;
/** 
* @author saint
* @version 创建时间：2017年9月21日 下午8:45:54 
* 类说明 
*/

/**
 * 修饰class名，只有 public、abstract、final，不能用private、protected
 *
 */
public class Singleton {

	public static volatile Singleton instance ; 
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Singleton() {
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			synchronized (Singleton.class) {
				if(instance == null) {
				   //一定要使用这种方法
				   instance =  new Singleton();
				   //如果使用以下方法，会导致单例生成的对象不一致
				   //return new Singleton();
				}
			}
		}
		return instance;
	}
	
	public void printInfo() {
		System.out.println("name = "+name);
	}
}
