package com.cn.saint.variousTest;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.prism.impl.ManagedResource;

/** 
* @author saint
* @version 创建时间：2017年9月28日 上午9:20:12 
* 类说明 
*/
public class Test7 {

	public final static Integer MAX_VALUE = 100;
	
	public static void name() {
		System.out.println("hello, my name");
	}
	
	@SuppressWarnings("null")
	public static void main(String[] args) {
		Object o1 = new Object();
		Object o2 = new Object();
		
		System.out.println(o1==o2);
		System.out.println(o1.equals(o2));
		System.out.println(Objects.hashCode(o1)+"=="+Objects.hashCode(o2));
		
		Integer i1 = new Integer(2);
		Integer i2 = new Integer(2);
		
		System.out.println(i1==i2);
		System.out.println(i1.equals(i2));
		
		Map<Integer, Integer> map = new HashMap<>();
		
		map.put(i1, 3);
		
		System.out.println(map.get(2));
		
		String a = "1,11,2,13";
		String b = "3";
		
		String[] s1 = a.split(",");
		String[] s2 = b.split(",");
		
		List<String[]> list = new ArrayList<>();
		
		System.out.println("===="+Arrays.asList(a).contains("3"));
		
		list.add(s1);
		list.add(s2);
		
		list.stream().filter(s->Arrays.asList(s).contains(b)).collect(Collectors.toList());
		
		System.out.println("====="+list.size());
		
		
	//	System.out.println("result ="+a.contains("3"));
		
	//	Stream.of(list).filter(x->!x.contains("3")).collect(Collectors.toList());
		
		Integer[] sixNums = {1, 2, 3, 4, 5, 6};
		Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
		
		for(int i=0;i<evens.length;i++) {
			System.out.println(evens[i]);
		}
		
		String[] ss = {"AA", "AB", "CC", "BC"};
		
		Arrays.asList(ss).stream().filter(x->x.startsWith("A")).collect(Collectors.toList()).forEach(x->{
			System.out.println("==========="+x);
		});
		
		//LongAdder
		System.out.println(BigDecimal.TEN.compareTo(BigDecimal.ONE));
		
	//	private volatile String[] sb = new String[12];
		
	    System.out.println(java.lang.Runtime.getRuntime().freeMemory()/1024/1024);
	    System.out.println(java.lang.Runtime.getRuntime().totalMemory()/1024/1024);
	    System.out.println(java.lang.Runtime.getRuntime().maxMemory()/1024/1024);

	    //打印数组
	    int[] a1 = {1,3,5,7};
	    System.out.println(Arrays.toString(a1));
	    
	    List<Integer> it = new ArrayList<>();
	    it.add(3);
	    it.add(1);
	    it.add(5);
	    
	    Iterator<Integer> aa = it.iterator();
	    //使用iterator进行删除
	    while(aa.hasNext()) {
	    	    Integer i = aa.next();
	    	     if(i.intValue() == 3) {
	    	    	     aa.remove();
	    	     }
	    }
        System.out.println(it.get(0)+"--"+it.get(1));	    
        
        List<String> a2 = new ArrayList<>();
        
        a2.add("2");
        a2.add("1");
        
        for(String item:a2) {
        	   if("1".equals(item)) {
        	//	   a2.remove(item);
        	   }
        }
        
        System.out.println(a2.size());
        
        System.out.println(Test7.class.isAnnotationPresent(Controller.class));
        
        Arrays.asList(Test7.class.getAnnotations()).forEach(x->{
        	     System.out.println("====="+x);
        });
        
        //jdbc
        //加载驱动
        //Class.forName(driver)
        //获取链接
        //conn = DriverManager.getConnection
        //执行ps
        //PreparedStatement ps = Connection.preparestatement(sql);
        //获取数据集
        //ResultSet = ps.executeQuery();
        
        String[] str = null ;
        
        //在for循环中加入判空条件，可避免NPE问题,并且要先判断为null条件，否则也会有NPE问题
        for(int i=0;str!=null&&i<str.length;i++) {
        	    System.out.println(str[i]);
        }
        
   //     Map map = new HashMap<K, V>;
        // hashset -> set -> colloection -> iterator 
     //   Hashtable<K, V>
        
        DecimalFormat d = new DecimalFormat("00000");
        
        System.out.println(d.format(1));
	}
}
