package com.cn.saint.compare;

import java.util.Comparator;
import java.util.Objects;

/** 
* @author saint
* @version 创建时间：2017年9月4日 下午4:55:33 
* 类说明 
*/
public class CompareTest implements Comparable<CompareTest>{

	private String name;
	private int age; 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public CompareTest() {
       		
	}
	
	public CompareTest(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public int compareTo(CompareTest o) {
		if(age > o.age) {
			return 0;
		} else if(age <o.age){
			return 1;
		} else {
			return name.compareTo(o.name);
		}
	}

	public static void main(String[] args) {
		/**
		 * comparable 需要重写方法compareTo
		 */
		CompareTest ct1 = new CompareTest("a", 15);
		CompareTest ct2 = new CompareTest("b", 16);
		
		System.out.println("Comparable比较 = "+ct1.compareTo(ct2));
		
		/**
		 * comparator 需要重写方法compare 
		 */
		int i = 10;
		int j = 11;
		
		CompareTestor compareTestor = new CompareTestor();
		System.out.println("CompareTestor = "+compareTestor.compare(i, j));
	}
}

class CompareTestor implements Comparator<Object>{

	public int compare(Object o1, Object o2) {
		int hash1 = Objects.hashCode(o1);
		int hash2 = Objects.hashCode(o2);
		return hash1>hash2?1:(hash1==hash2)?0:-1;
	}
}
