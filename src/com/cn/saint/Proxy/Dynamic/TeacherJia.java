package com.cn.saint.Proxy.Dynamic;
/** 
* @author saint
* @version 创建时间：2017年8月30日 下午2:19:55 
* 类说明 
*/
public class TeacherJia implements Boy{

	public boolean date(float length) {
		if(length>1.65) {
			System.out.println("【男艺人】身材不错");
			return true;
		} else {
			System.out.println("【男艺人】太矮了，不爽");
			return false;
		}
	}

}
