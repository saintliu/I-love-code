package com.cn.saint.variousTest;

import com.alibaba.fastjson.JSON;

/** 
* @author saint
* @version 创建时间：2017年11月1日 下午4:24:39 
* 类说明 
*/
public class TestJson {

	public static void main(String[] args) {
		People a = new People();
		a.setName("a");
		People b = new People();
		b.setName("b");
		
		a.setParent(b);
		b.setParent(a);
		
		String str = JSON.toJSONString(b);
		System.out.println(str);
		
		People b2 = JSON.parseObject(str, People.class);
		System.out.println(b2.getParent().getName());
	}
}

class People{
	private String name;
	private People parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public People getParent() {
		return parent;
	}

	public void setParent(People parent) {
		this.parent = parent;
	}
	
	
}