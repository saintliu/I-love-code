package com.cn.saint.callback;


/** 
* @author saint
* @version 创建时间：2017年9月22日 下午2:59:20 
* 类说明 
*/
public class OldMother implements Customer{

private String name ; 
	
    public OldMother(String name) {
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
		new SuperCalculator().add(a, b, new OldMother(name));
	}
}
