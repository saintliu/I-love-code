package com.cn.saint.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** 
* @author saint
* @version 创建时间：2017年9月10日 下午4:58:30 
* 类说明 
*/
public class Person implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//private int age;
	//可以使当前属性在序列化时不可见
	private transient int age;
	private String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) throws IOException {
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("/Users/liuyusheng/serialization.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Person person = new Person();
		
		person.setAge(13);
		person.setName("Saint");
		
		//序列化
		objectOutputStream.writeObject(person);
	    System.out.println("序列化结束。。。");
		
	    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("/Users/liuyusheng/serialization.txt")));
	    System.out.println("开始反序列化。。。");
	    
	    try {
	    	    Person person2 = (Person)objectInputStream.readObject();
			System.out.println("age = "+person2.getAge());
			System.out.println("name = "+person2.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
