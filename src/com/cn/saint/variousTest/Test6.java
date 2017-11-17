package com.cn.saint.variousTest;

import java.util.ArrayList;
import static com.cn.saint.variousTest.Test7.MAX_VALUE;
import java.util.List;
import static java.lang.System.out;

/** 
* @author saint
* @version 创建时间：2017年9月26日 上午9:45:52 
* 类说明                                             
*/
public class Test6 {

	public static void main(String[] args) {
		System.out.println(java.util.Objects.equals("a", "b"));
		//意思是只有有一个条件成立，都返回true
		System.out.println((1==2)||(3==3));
		
		/**
		 * 在-128~127的Integer值并且以Integer x = value;的方式赋值的Integer值在进行==和equals比较时，都会返回true，
		 * 因为Java里面对处在在-128~127之间的Integer值，用的是原生数据类型int，会在内存里供重用，integerCache
		 * 也就是说这之间的Integer值进行==比较时只是进行int原生数据类型的数值比较，而超出-128~127的范围，进行==比较时是进行地址及数值比较。
		 * 
		 * equals 比较的是值 
		 * 
		 * public boolean equals(Object obj) {
	        if (obj instanceof Integer) {
	            return value == ((Integer)obj).intValue();
	        }
	        return false;
	    }
		 * 
		 */
		
		Integer integer = 128;   //-128 - 127
		System.out.println(integer);
		
		/**
		 * clone 深拷贝、浅拷贝
		 * 
		 * 浅拷贝只是对对象本身进行克隆，而对其引用类型参数不做操作，默认为浅拷贝
		 * 深拷贝对对象并且其所有引用型参数进行克隆，需要重写clone方法
		 * 
		 */
		List<String> list = new ArrayList<String>(); 
		list.add("1");
		list.add("2");
		/*for (String item : list) {
			if ("2".equals(item)) { 
				list.remove(item);
			} 
		}*/
		
		for(String item: list) {
			System.out.println(item);
		}
		
		/**
		 * import static 静态引入静态方法或变量
		 * import 只能引入到类级别
		 * 
		 */
		System.out.println(MAX_VALUE);
		
		out.println("hello");
	
		// 1*2*3*4*5
		
		int n = 5;
		int result = 1;
		
		for(int i=1; i<=5; i++) {
			
			result = result * i;
		}
		
		System.out.println(result);
	}
}
