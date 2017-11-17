package com.cn.saint.variousTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** 
* @author saint
* @version 创建时间：2017年9月13日 下午12:17:21 
* 类说明 
*/
public class StartUp {

	public static void main(String[] args) throws ClassNotFoundException {  
        
        int i = 0 ;  
          
        while(true && i<10){  
            MyClassLoader mcl = new MyClassLoader() ;  
            System.out.println(mcl.getParent());  
            Class<?> testClazz =  mcl.findClass("com.cn.saint.variousTest.Test2");  
            System.out.println(testClazz.getClassLoader());
            try {  
                Object test2 =  testClazz.newInstance() ;  
                Method sayHelloMethod = testClazz.getMethod("name") ;  
                sayHelloMethod.invoke(test2) ;  
                System.out.println(++i);  
            } catch (InstantiationException e1) {  
                e1.printStackTrace();  
            } catch (IllegalAccessException e1) {  
                e1.printStackTrace();  
            } catch (SecurityException e) {  
                e.printStackTrace();  
            } catch (NoSuchMethodException e) {  
                e.printStackTrace();  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (InvocationTargetException e) {  
                e.printStackTrace();  
            }  
              
            try {  
                Thread.sleep(1000) ;  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
