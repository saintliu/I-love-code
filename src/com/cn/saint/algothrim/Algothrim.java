package com.cn.saint.algothrim;

import java.util.Scanner;

/**
 * 
 * @author liuyusheng
 *
 */
public class Algothrim {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		
		System.out.println("请输入a的值");
		int a = in.nextInt();
		
		System.out.println("请输入n个数");
		int n = in.nextInt();
		
		int s = 0, t = 0;
		
		for(int i=1; i<=n; i++){
			t += a;   
			a = a*10; 
			s += t; 
		}
		
		System.out.println(s);
	}

}
