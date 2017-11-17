package com.cn.saint.sourceCode;

import java.util.LinkedList;

import org.springframework.util.concurrent.ListenableFutureTask;

import com.sun.corba.se.impl.orbutil.graph.Node;

/** 
* @author saint
* @version 创建时间：2017年8月31日 下午3:39:04 
* 类说明 
*/

/**
 * 基于链表的方式实现，每个元素都有指针，指向其前面和后面的元素，
 * 所以它在增删的时候速度特别快，但查询很慢
 * 
 * 线程不安全
 */
public class LinkedListTest {
	
	//指向上一个节点
	private Node<String> first;
	private Node<String> last;
	private int size=0; 
	
	public LinkedListTest() {
		
	}
	//返回长度
	public int size() {
		return size;
	}
	
	//增加第一个元素
	public void addFirst(String e) {
		Node<String> f = first;
		Node<String> newNode = new Node<String>(null, e, f);
		first = newNode;
		if(f== null) 
			last = newNode;
		else 
			f.prev = newNode;
		size++;
	}
	
	 Node<String> node(int index) {
	        // assert isElementIndex(index);

	        if (index < (size >> 1)) {
	            Node<String> x = first;
	            for (int i = 0; i < index; i++)
	                x = x.next;
	            return x;
	        } else {
	            Node<String> x = last;
	            for (int i = size - 1; i > index; i--)
	                x = x.prev;
	            return x;
	        }
	    }
	 
	//查询一个元素
	public String get(int index){
		return node(index).item;
	}

	public static void main(String[] args) {
        LinkedListTest linkedListTest = new LinkedListTest();
        linkedListTest.addFirst("a");
        linkedListTest.addFirst("b");
        for(int i=0;i<linkedListTest.size;i++) {
        	    System.out.println("="+linkedListTest.get(i));
        }
	}

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;

		Node(Node<E> prev, E element, Node<E> next) {
			this.item = element;
			this.next = next;
			this.prev = prev;
		}
	}
}
