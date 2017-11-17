package com.cn.saint.ssh.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
* @author saint
* @version 创建时间：2017年8月29日 下午3:52:22 
* 类说明 
*/
public class SpringIOCDemo {

	public void add() {
		System.out.println("add......");
	}
	
	public static void main(String[] args) {
		
		//加载配置文件
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("/MyFirstProject/src/com/cn/saint/ssh/spring/applicationContext.xml");
		
		//调用反射机制，获取class name, 并且创建新的class对象
		SpringIOCDemo demo = (SpringIOCDemo) context.getBean("springIOCDemo");
	    demo.add();
	}
}
