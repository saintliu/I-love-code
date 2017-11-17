package com.cn.saint.hashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author saint
 * @version 创建时间：2017年8月31日 下午4:34:01 类说明
 */
public class HashMapTest<K, V> {

	private Node<K, V>[] table;

	static class Node<K, V> {
		private int hash;
		private K key;
		private V value;
		private Node<K, V> next;

		public int getHash() {
			return hash;
		}

		public void setHash(int hash) {
			this.hash = hash;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public Node<K, V> getNext() {
			return next;
		}

		public void setNext(Node<K, V> next) {
			this.next = next;
		}

		public Node(int hash, K key, V value, Node<K, V> next) {
			super();
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [hash=" + hash + ", key=" + key + ", value=" + value + ", next=" + next + "]";
		}
	}
	
	public void toString1() {
		for(Node<K, V> node:table) {
			if(null != node) {   //判断entry是否为null
                System.out.println("=="+node.key+"==="+node.value+"==="+node.hash);
                if(node.next != null) {
                	   System.out.println("===="+node.next.key);
                	   System.out.println("===="+node.next.value);
                	   System.out.println("===="+node.next.hash);
                }
			}
		}
	}

	// 储存元素
	public void put(K key, V value) {
		// 算hash
		int hash = Objects.hashCode(key);
		// 初始化数组长度
		int capacity = 16;
		if (table == null) {
			table = new Node[16];  //初始化了16个值为null的entry
		}
		// 根据key，算出hash值
		int i = hash % capacity;
		// 判断table[i]是否存在
		if (null == table[i]) {
			table[i] = new Node<K, V>(hash, key, value, null);
		} else {
			Node<K, V> node = table[i];
			// 判断存在的key是否等于传入的key
			if ((node.hash == hash) && (node.key == key || (key != null && node.key.equals(key)))) {
				node.value = value;
			} else {
				// 判断是否有下一个元素
				for (int count = 0;; count++) {
					if (node.next == null) {
						node.next = new Node<K, V>(hash, key, value, null);
						break;
					}
					// 判断下一个元素的key是否等于传入的key
					if ((node.next.hash == hash)
							&& (node.next.key == key || (key != null && node.next.key.equals(key)))) {
						node.next.value = value;
						break;
					}
					node = node.next;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		HashMapTest<String, Integer> map = new HashMapTest<String, Integer>();
		
		map.put("a", 111);
		map.put("b", 222);
		map.put("c", 333);
		map.put(null, 444);
		map.put("p", 555);
		
		map.toString1();

		Map<String, String> map1 = new HashMap<String, String>();
	}
}
