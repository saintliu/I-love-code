package com.cn.saint.ssh.spring;
/** 
* @author saint
* @version 创建时间：2017年8月29日 下午3:12:45 
* 类说明 
*/
/**
 * Spring 开源的，轻量级的框架  - 项目依赖少
 * 一站式框架 --> 提供不同的技术给3层：web (spring mvc)/service(ioc)/do(spring jdbc template)
 * 
 * 核心： 面向切面aop、控制反转ioc
 * 
 * aop：面向切面编程
 * ioc：省去创建类对象的过程，把对象的创建交由spring，以以下方式实现
 *    a. 配置文件 (基于反射)
 *      1. 申明类
 *      <bean id="UserService" class="com.cn.saint.UserService"/>
 *      2. 创建工厂类，dom4j解析xml文件，使用反射得到类对象和其全路径， 
 *         Class.forName
 *         使用反射创建类对象（无参构造）
 *         class.newInstance()
 *         
 *         bean实例化的三种方式：
 *         1. 🈚参构造
 *         2. 静态工厂创建：创建静态方法，返回类对象
 *         3. 实例化工厂创建：创建非静态方法，创建工厂对象，返回类对象
 *    b. 注解
 *
 */

public class SpringReadMe {

	public static void main(String[] args) {

	}

}
