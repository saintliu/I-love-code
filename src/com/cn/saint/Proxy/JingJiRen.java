package com.cn.saint.Proxy;

import com.cn.saint.Proxy.Dynamic.Boy;

/** 
* @author saint
* @version 创建时间：2017年8月30日 下午1:52:34 
* 类说明 
*/
public class JingJiRen implements Girl,Boy {

	private Girl girl;
	
	public JingJiRen(Girl girl) {
		super();
		this.girl = girl;
	}

	public Girl getGirl() {
		return girl;
	}

	public void setGirl(Girl girl) {
		this.girl = girl;
	}
	
	public boolean date(float length) {
		DosomethingBefore();
        this.girl.date(length);		
		DosomethingAfter();
		return false;
	}

	private void DosomethingAfter() {
        System.out.println("【经纪人】用过了，感觉咋样");		
	}

	private void DosomethingBefore() {
        System.out.println("【经纪人】我试过了，不错,推荐给你");		
	}
	
	public static void main(String[] args) {
		TeacherCang teacherCang = new TeacherCang();
		JingJiRen jingJiRen = new JingJiRen(teacherCang);
		jingJiRen.date(170);
	}
}
