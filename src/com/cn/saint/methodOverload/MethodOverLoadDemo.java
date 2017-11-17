package com.cn.saint.methodOverload;
/** 
* @author saint
* @version 创建时间：2017年9月10日 上午10:35:18 
* 类说明 
*/
public class MethodOverLoadDemo {
	
	/**
	 * 方法重载： 方法名相同，参数个数不同，参数类型不同,返回值可以不同
	 * 方法重写：方法名相同，参数类型、个数和返回类型必须跟父类保持一致， 而且权限不能小于父类
	 * @param name
	 */
	public void name(String name) {
		boolean flag = false; 
		if("Saint".equals(name)) {
			flag = true;
		}
		this.name(name, flag);
	}

	private void name(String name, boolean flag) {
        if(flag) {
        	   System.out.println("我是 "+name);
        } else {
        	   System.out.println("我不是Saint，我是 "+name);
        }
	}
	
	public int name() {
		return 1;
	}
  
	public static void main(String[] args) {
		MethodOverLoadDemo methodOverLoadDemo = new MethodOverLoadDemo();
		
		methodOverLoadDemo.name("Saint");
		methodOverLoadDemo.name("JiaYi");
	}
}
