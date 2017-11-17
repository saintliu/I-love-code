package com.cn.saint.callback;
/** 
* @author saint
* @version 创建时间：2017年9月22日 下午2:54:33 
* 类说明 
*/
/*回调类*/
public class SuperCalculator {
	
	public void add(int a, int b, Customer customer) {
		int result = a + b; 
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//接口回调
		customer.fillBlank(a, b, result);
	}

}
