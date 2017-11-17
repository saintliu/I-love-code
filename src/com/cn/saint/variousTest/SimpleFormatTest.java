package com.cn.saint.variousTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author saint
* @version 创建时间：2017年9月6日 上午9:11:14 
* 类说明 
*/
public class SimpleFormatTest {

	public static class TestSimpleDateFormatThreadSafe extends Thread{
		public void run() {
			while(true) {
				try {
					this.join(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					//线程不安全
			//		System.out.println(this.getName()+":"+DateUtil.parse("2013-05-24 06:02:20"));
					//线程安全
					System.out.println(this.getName()+":"+ConcurrentDateUtil.parse("2013-05-24 06:02:20"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
         for(int i=0;i<3;i++) {
        	    new TestSimpleDateFormatThreadSafe().start();
         }
	}
}

class DateUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDate(Date date) throws ParseException{
		return sdf.format(date);
	}

	public static Date parse(String strDate) throws java.text.ParseException{
		return sdf.parse(strDate);
	}
}

class ConcurrentDateUtil{
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
		
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	public static String format(Date date) {
		return threadLocal.get().format(date);
	}
	
	public static Date parse(String strDate) throws ParseException {
		return threadLocal.get().parse(strDate);
	}
	
}

