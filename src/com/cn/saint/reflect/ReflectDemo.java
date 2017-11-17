package com.cn.saint.reflect;

/** 
* @author saint
* @version 创建时间：2017年9月10日 下午4:11:50 
* 类说明 
*/
public class ReflectDemo {
	
	public void sayHello() {
		System.out.println("sayhello");
	}
	
	private int age;
	
	public String name;
    
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		ReflectDemo reflectDemo = new ReflectDemo();
		//使用反射获取包名和全路径
		Class<?> reflectDemo2 = null;
		
		try {
			//通过class对象生成具体class对象
			reflectDemo2 = Class.forName("com.cn.saint.reflect.ReflectDemo");
			
			System.out.println(reflectDemo2.getName());
			//通过newInstance方法，生成具体实例对象
			ReflectDemo reflectDemo3 = (ReflectDemo) reflectDemo2.newInstance();
			reflectDemo3.sayHello();
			//返回一个数组，包含其方法
			System.out.println(reflectDemo2.getDeclaredMethods()[1]);
            //获取构造函数
			System.out.println(reflectDemo2.getConstructors()[0]);
			
			//只能获取public 的属性
			System.out.println(reflectDemo2.getFields().length);
			
			//当对私有变量处理时，设置其可见性，用setAccessible
			reflectDemo2.getDeclaredFields()[0].setAccessible(true);
			System.out.println(reflectDemo2.getDeclaredFields()[0]);

			//获取包名
			System.out.println(reflectDemo2.getPackage());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
