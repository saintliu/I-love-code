package com.cn.saint.ssh.hibernate;
/** 
* @author saint
* @version 创建时间：2017年8月30日 上午10:52:20 
* 类说明 
*/
//*实体类*/
public class User {

	private int uid;
	private String name;
	private int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
