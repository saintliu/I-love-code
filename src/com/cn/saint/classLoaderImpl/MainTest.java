package com.cn.saint.classLoaderImpl;

import com.cn.saint.variousTest.Test2;

/** 
* @author saint
* @version 创建时间：2017年9月13日 上午10:31:08 
* 类说明 
*/
/**
 *  boostrap <- extclassloader <- appclassloader 
 *
 */
public class MainTest {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        FileSystemClassLoader loader = new FileSystemClassLoader("/Users/liuyusheng");
	    Class<?> clazz2 = loader.findClass("com.cn.saint.variousTest.Test2");
	    
	    //==> com.cn.saint.variousTest.Test2, 由本身的加载器加载
	    System.out.println(clazz2.getClassLoader());
	    
	    Test2 test2 = (Test2) clazz2.newInstance();
	    
   //     test2.name();	    
	    //同一个类被不同的类加载器加载，jvm也认为不同
	    //这样的类会首先被系统加载类加载。
	    Class<?> clazz1 = loader.findClass("java.lang.String");
        // null --> bootstraploaderclass
	    System.out.println(clazz1.getClassLoader());
	    
	    Class<?> clazz3 = loader.findClass("com.cn.saint.classLoaderImpl.MainTest");
	    System.out.println(clazz3.getClassLoader());
	}
}