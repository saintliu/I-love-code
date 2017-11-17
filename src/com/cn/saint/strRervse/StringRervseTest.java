package com.cn.saint.strRervse;


/**
 * use for String reverse
 * @author liuyusheng
 *
 */
public class StringRervseTest {

	public static String StrRervse(String str) {
		if(str.length() == 1 || str == null ) {
			return str;
		}
		return StrRervse(str.substring(1))+str.charAt(0);
	}
	
	public static void main(String[] args) {

		System.out.println(StrRervse("abcd"));
		
	}

}
