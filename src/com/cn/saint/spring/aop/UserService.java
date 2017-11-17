package com.cn.saint.spring.aop;
/** 
* @author saint
* @version 创建时间：2017年9月21日 下午3:14:46 
* 类说明 
*/
public interface UserService {

	/** 
	* @author saint
	* @version 创建时间：2017年9月21日 下午3:14:46 
	* 类说明 
	*/
	public String findUser(String username);
	public void addUser(String username);
	public void findAll();
}
