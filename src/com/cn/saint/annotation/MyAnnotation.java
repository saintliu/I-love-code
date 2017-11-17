package com.cn.saint.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
* @author saint
* @version 创建时间：2017年9月14日 下午7:24:26 
* 类说明 
*/

//意思是注解只能用在方法前面. elementType 是一个枚举类型
@Target(value=ElementType.METHOD)
//注解保留的策略：source / class / runtime. runtime时可以用反射来读取
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

	int id() default 0;
}
