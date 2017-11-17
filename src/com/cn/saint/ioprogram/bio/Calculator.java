package com.cn.saint.ioprogram.bio;
/** 
* @author saint
* @version 创建时间：2017年8月28日 下午4:25:49 
* 类说明 
*/

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {

	private final static ScriptEngine jse = new
			ScriptEngineManager().getEngineByName("JavaScript");
	
	public static Object cal(String expression) throws ScriptException {
		return jse.eval(expression);
	}
}
