package com.cn.saint.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** 
* @author saint
* @version 创建时间：2017年9月5日 上午10:54:38 
* 类说明 
*/
public class T {

	static final int threads = 1000;
	static final int number = 1000000; 
	
	public static void main(String[] args) {
		//普通hashmap方法
        Map<String, Integer> hashmapSync = Collections.synchronizedMap(new HashMap<String, Integer>());
        //
        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<String, Integer>();
        //
        Map<String, Integer> hashTable = new Hashtable<String, Integer>();
        
        long totalA = 0;
        long totalB = 0;
        long totalC = 0;
        
        for(int i=0;i<=10;i++) {
        	   totalA += testPut(hashmapSync);
        	   totalB += testPut(concurrentHashMap);
        	   totalC += testPut(hashTable);
        }
        System.out.println("Put time HashMapSync=" + totalA + "ms.");
        System.out.println("Put time ConcurrentHashMap=" + totalB + "ms.");
        System.out.println("Put time HashTable=" + totalC + "ms.");
        
        totalA = 0;
        totalB = 0;
        totalC = 0;
        
        for(int i=0;i<=10;i++) {
        	   totalA += testGet(hashmapSync);
        	   totalB += testGet(concurrentHashMap);
        	   totalC += testGet(hashTable);
        }
        
        System.out.println("Get time HashMapSync=" + totalA + "ms.");
        System.out.println("Get time ConcurrentHashMap=" + totalB + "ms.");
        System.out.println("Get time HashTable=" + totalC + "ms.");        
	}

	private static long testPut(Map<String, Integer> map) {
		long start = System.currentTimeMillis();
		for(int i=0;i<threads;i++) {
			new MapPutThread(map).start();
		}
		while(MapPutThread.counter>0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return System.currentTimeMillis() - start;
	}
	
	private static long testGet(Map<String, Integer> map) {
		long start = System.currentTimeMillis();
		for(int i=0;i<threads;i++) {
			new MapPutThread(map).start();
		}
		while(MapPutThread.counter > 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return System.currentTimeMillis() - start;
	}
}

class MapPutThread extends Thread {
	static int counter = 0;
	static Object lock = new Object();
	private Map<String, Integer> map;
	private String key = this.getId() + "";
	public MapPutThread(Map<String, Integer> map) {
		synchronized (lock) {
			counter++;
		}
		this.map = map;
	}
	public void run() {
		for(int i=1;i<=T.number;i++) {
			map.put(key, i);
		}
		synchronized (lock) {
			counter--;
		}
	}
}

class MapGetThread extends Thread {
	static int counter = 0;
	static Object lock = new Object();
	private Map<String, Integer> map;
	private String key = this.getId() + ""; 
	public MapGetThread(Map<String, Integer> map) {
		synchronized (lock) {
			counter++;
		}
		this.map = map;
	}
	public void run() {
		for(int i=1;i<=T.number;i++) {
			map.get(key);
		}
		synchronized (lock) {
			counter--;
		}
	}
	
}
