package com.cn.saint.variousTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.cn.saint.threadpool.ThreadPool;

public class Test<V> implements Callable<V>{
	
	/**
	 * 递归实现字符串的反转
	 * 
	 * abcd -> bcd + a -> cd + b + a -> d + c + b + a 
	 * 
	 * @param args
	 */
	public String StrReverse(String str){
		
		if(null == str || str.length() == 1){
			return str;
		}
		//按规则依次分解字符串： 始终把第一个元素放在最后面
	    return StrReverse(str.substring(1))+str.charAt(0);
	}
	
	/**
	 * 泛型
	 * @return
	 * 保证存储数据的类型一致性，免去了遍历过程中的类型转换过程
	 * 
	 */
	public List<String> GenericTest() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("bb");
		return list;
	}

	public static void main(String[] args) {
		
		Test t = new Test();
		System.out.println(t.StrReverse("abcd"));
		
		Thread thread = new Thread();
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		List<String> list = t.GenericTest();
		
		List<Integer> list1 = new ArrayList<Integer>();
		
		list1.add(2);
		list1.add(1);
		list1.add(4);
		list1.add(0);
		//利用工具类进行优雅的排序
		java.util.Collections.sort(list1, (o1,o2)->((Integer)o1).compareTo((Integer)o2));
		
		for(Integer i:list1) {
			System.out.println(i);
		}
		
		for(String ss:list) {
     //		System.out.println("=="+ss);
		}
		
		HashSet set = new HashSet<>();
		
		/**
		 * 简单实现读取功能
		 * 
		 * 如果要同步的话，使用原子或锁
		 */
		List<Integer> list2 = new ArrayList<Integer>();
		
  //      new WriteThread(list2).start();
//		new ReadThread(list2).start();
		
		List<AtomicInteger> list3 = new ArrayList<AtomicInteger>();
		
//		new WriteThread(list3).start();
//		new ReadThread(list3).start();
		
		List<Integer> list4 = new ArrayList<>();
		Lock lock = new ReentrantLock();
		
		new WriteThread(list4, lock).start();
		new ReadThread(list4,lock).start();
		
		String string = "我是saint";
		System.out.println("====$$$=="+string.getBytes().toString());
		
		
		/**
		 * 判断是否为素数
		 * 
		 * 只能被1和他自身整除的数
		 */
	/*	Scanner scanner = new Scanner(System.in);
		System.out.println("请输入数字：");
		int n = scanner.nextInt();
		for(int i = 2;i<=n/2;i++) {
			if(n%i == 0) {
				System.out.println("非素数");
				return;
			}
		}
		System.out.println("素数");*/
		
		/**
		 * 算1000以内的水仙花数(3位数，各位数字的立方和等于数本身)
		 */
		int hundred, ten, bits; 
		for(int i=100;i<1000;i++) {
			String ss = String.valueOf(i);
			hundred = Integer.parseInt(ss.split("")[0]);
			ten = Integer.parseInt(ss.split("")[1]);
			bits = Integer.parseInt(ss.split("")[2]);
			
			if(i==hundred*hundred*hundred+ten*ten*ten+bits*bits*bits) {
				System.out.println("===="+i);
			}
		}
		
		/**
		 * 三目运算符
		 */
		int a = 20;
	//	System.out.println(a>18?a:a-1?a+1:a);  编译出错，因为 a-1 不是boolean类型
				
		/**
		 * unchecked exception 包括runtimeexception 和error，不需要try catch
		 * checked exception 需要try catch
		 */
		List<String> list5 = null;
		 try {
			 list5.add("aa");
			 File file = new java.io.File("user");
		 }catch (NullPointerException e) {  //runtime  uncheck
		 }catch (ClassCastException e) {  //runtime   unckeck
		// }catch (FileNotFoundException e) {  //exception
		} catch (ArrayIndexOutOfBoundsException e) {  //runtime
		}
		 
		 try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

	public V call() throws Exception {
		return null;
	}

}
