package com.cn.saint.variousTest;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/** 
* @author saint
* @version 创建时间：2017年9月13日 上午11:11:28 
* 类说明 
*/
public class Test2 {
	
	public void name() {
		
	}

	public static void main(String[] args) {
		String s1 = new String("abc");
		String s2 = "abc";
		String s3 = new String("abc");
		// 创建s1, 创建了对象abc 在其内存中，s2是常量池，s3 创建了另一个abc对象。
		
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
		
		System.out.println(s1==s2);
		System.out.println(s1==s3);
		System.out.println(s2==s3);
		
		String hello = "hello";
		String hel = "hel";
		String lo  = "lo";
		
		System.out.println(hello == "hel" + "lo");  //true, 因为跟常量比较地址
		System.out.println(hello == "hel" + lo);    //false， 有一个不是常量，地址不一样
		
		// & 2个1才是1    | 有一个是1，就是1  ^ 相同为0，不同为1  ~ 取反
		int i = 1, j = 2;
	  //  (++i=2) & (++j=4);  //要求两侧为真则为真
		
		short s4 = 1;
	//	s4 = s4 +1;   编译错误，需要强转
		
		short s5 = 1;
		s5+=1;  // ==> 相当于  s5 = (short)s5+1;
		
		try {
			int k = 0;
			System.out.println(2/k);
		}catch (ArithmeticException e) {
			System.out.println("1");
		}catch (Exception e) {
		    System.out.println("2");
		}
		
		System.out.println(Math.floor(-4.7)); // -5
		System.out.println(Math.round(-4.7)); // -5
		System.out.println(Math.ceil(-4.7));  // -4
		
		/**
		 * 子类异常要放在前面，不然会有unreachable exception编译问题，并且异常只会抛出一次
		 */
		
		String string = "abc";
		char c = 1;
		System.out.println(string.substring(0, c));  //包括起始字符，不包末尾字符
		
		Map<String, String> map = new HashMap<>();
		map.put("total2017.m1", "1");
		map.put("total2017.m2", "2");
		map.put("total2017.m3", "3");
		map.put("total2017.m4", "4");

		for(Map.Entry<String, String> m:map.entrySet()) {
			if(m.getKey().contains("total2017.m")) {
				System.out.println("======"+m.getValue());
			}
		}
		
		String s6 = new String("abc");
		String s7 = new String("abc");
		//true ： string 重写了equals 方法，所以他们比较的是值
		System.out.println(s6.equals(s7));
		
		StringBuffer s8 = new StringBuffer("abc");
		StringBuffer s9 = new StringBuffer("abc");
		//false  : stringbuffer 没有重写equals 方法，所以他使用的是object的equals方法，直接比较对象
		System.out.println(s8.equals(s9));
		
		String s10 = "cc";
		
		if("abc".equals(s10)) {
			System.out.println("this is if");
		} else if("bbb".equals(s10)) {
			System.out.println("this is else if");
		} else {
			System.out.println("this is else");
		}
		
		
	}
}
