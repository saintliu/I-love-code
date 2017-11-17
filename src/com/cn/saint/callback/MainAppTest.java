package com.cn.saint.callback;

/** 
* @author saint
* @version 创建时间：2017年9月22日 下午3:00:04 
* 类说明 
*/

/**
 * 接口回调机制
 * 
 * Xiaoming 类调用superCaculator的 add方法， 然后supercaculator方法会回调
 * xiaoming的fillblank方法，将结果输出。
 * 
 */
public class MainAppTest {

	public static void main(String[] args) {
		int a = 56; 
		int b = 31; 
		
		int c = 2564;
		int d = 4578;
		
		XiaoMing xiaoMing = new XiaoMing("小明");
		OldMother oldMother = new OldMother("老婆婆");
		
		xiaoMing.callHelp(a, b);
	//	xiaoMing.doSomethingElse();
		oldMother.callHelp(c, d);
	}
}
