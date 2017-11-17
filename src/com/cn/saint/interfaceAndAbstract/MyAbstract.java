package com.cn.saint.interfaceAndAbstract;
/** 
* @author saint
* @version 创建时间：2017年9月7日 下午5:54:49 
* 类说明 
*/
public abstract class MyAbstract {
	/**
	 * 可以有方法的实现
	 */
	
     public void sayHello() {
		System.out.println("your name");
	}
     
     public void getName() {
	}

    /**
     * 如果类中有抽象方法，那么这个类一定是抽象类
     */
    public abstract void name();
    
    //构造方法不能用static 修饰
	public MyAbstract() {
		super();
	}
     
	public static void main(String[] args) {
		//抽象类可以实例化，并且一定要实现它的抽象方法
		MyAbstract myAbstract = new MyAbstract() {
			
			@Override
			public void name() {
			}
		};
		System.out.println("hi man, this is abstract");
	}
     
}
