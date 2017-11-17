package com.cn.saint.queue;
/** 
* @author saint
* @version 创建时间：2017年9月9日 下午4:02:16 
* 类说明 
*/

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使用队列实现自定义栈
 * @param <E>
 * 
 * 
 */
public class MyStack<E> {
     private Deque<E> container = new ArrayDeque<E>();
     private int cap;
     
     public MyStack(int cap) {
		super();
		this.cap = cap;
	}

	//压栈
     public boolean push(E e) {
		if(container.size()+1>cap)
			return false;
		return container.offerLast(e);
	}
     
     //出栈
     public E pop() {
		return container.pollLast();
	}
     
    //获取
     public E peek() {
		return container.peekLast();
	}
     
     public int size() {
		return container.size();
	}
}
