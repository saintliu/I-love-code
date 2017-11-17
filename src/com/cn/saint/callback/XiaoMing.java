package com.cn.saint.callback;
/** 
* @author saint
* @version 创建时间：2017年9月22日 下午2:55:25 
* 类说明 
*/
public class XiaoMing implements Customer{

	private String name ; 
	
	public XiaoMing(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void fillBlank(int a, int b, int result) {
		System.out.println(name + "求职小红超级计算机: " + a + "+" + b + "=" + result);
	}

	public void callHelp(int a, int b) {
		new SuperCalculator().add(a, b, new XiaoMing(name));
	}
	
	public void doSomethingElse() {
		System.out.println("我去干点别的，算好了告诉我结果");
	}
}
