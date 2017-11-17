package com.cn.saint.sourceCode;

import java.util.ArrayList;
import java.util.List;

public class AppTest {

	public static void main(String[] args) {
		/**
		 * List extends Collection
		 * 
		 * ArrayList implements List
		 */
		
		List<String> list = new ArrayList<String>(2);
		list.add("1");
		list.add("2");
		list.add("3");
		
		System.out.println(list.get(2));
	}
}
