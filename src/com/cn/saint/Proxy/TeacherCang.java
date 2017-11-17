package com.cn.saint.Proxy;
/** 
* @author saint
* @version 创建时间：2017年8月30日 下午1:50:12 
* 类说明 
*/
public class TeacherCang implements Girl {

	public boolean date(float length) {
		if(length > 1.7f) {
			System.out.println("【女艺人】这个身高可以约会");
			return true;
		} else {
			System.out.println("【女艺人】这个身高不行");
			  return false;
		}
	}
}
