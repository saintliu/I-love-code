package com.cn.saint.spring.aop;

import java.util.HashMap;
import java.util.Map;

/** 
* @author saint
* @version 创建时间：2017年9月21日 下午3:16:02 
* 类说明 
*/
public class UserServiceImpl implements UserService {

	public static Map<Integer, String> map = null;
	
	public static void init() {
		String[] liStrings = {"Lucy", "Tom", "ming", "Smith"};
		map = new HashMap<>();
		
		for(int i=0;i<liStrings.length;i++) {
			map.put(i, liStrings[i]);
		}
	}
	
	@Override
	public String findUser(String username) {
		init();
		String password ="111";
		if(map.containsKey(username)) {
			password = map.get(username).toString();
		}
		System.out.println("-------【findUser】-------------");
		System.out.println("--------UserName:"+username+"-------");
		System.out.println("--------【result】:"+password+"------");
        return password;
	}

	@Override
	public void addUser(String username) {
		init();
        map.put(map.size()+1, username);
        System.out.println("------【addUser】:" +username +"--------");
	}

	@Override
	public void findAll() {
        init();
        System.out.println("------【findAll】:"+map+"----------");
	}
}
