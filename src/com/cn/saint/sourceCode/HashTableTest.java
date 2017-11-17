package com.cn.saint.sourceCode;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

/** 
* @author saint
* @version 创建时间：2017年9月1日 下午12:06:51 
* 类说明 
*/

/**
 * hashtable 是线程安全的，因为很多基本方法都用synchronized修饰。
 *
 */
public class HashTableTest {
	
	public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        
        //不支持key= null， 看源码
        /**
         *  public synchronized V put(K key, V value) {
        // Make sure the value is not null
	        if (value == null) {
	            throw new NullPointerException();
	        }
         */
    //    hashtable.put(null, 11);
        
        //
	}

}
