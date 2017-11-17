package com.cn.saint.sourceCode;

/** 
* @author saint
* @version 创建时间：2017年8月31日 上午11:07:47 
* 类说明 
*/
/**本质也是一个数组。
 * vector是线程安全，可以看到很多函数都有用synchronized关键字
 */
public class VectorTest {
	
	//本质也是一个数组
	private Object[] elementData;
	//容量
	private int size; 
	
	public VectorTest() {
		this(10);
	}
	
	public VectorTest(int initialCapacity) {
		if(initialCapacity<0)
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		elementData = new Object[initialCapacity];
	}

	//判断长度
	public int size() {
		return elementData.length;
	}
	
	//判断是否为空
	public boolean isEmpty() {
		return elementData.length == 0;
	}
	
	//当前容量
	public synchronized int capacity() {
		return elementData.length;
	}
	
	//增删改查
	public synchronized Object get(int index) {
		if(index > elementData.length)
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return elementData[index];
	}
	
	public synchronized void add(Object object) {
		elementData[size++] = object;
	}
	
	public synchronized void remove(int index) {
		System.arraycopy(elementData, index+1, elementData, index, elementData.length-index-1);
	}
	
	public synchronized void update(Object newObj, int index) {
		elementData[index] = newObj;
	}
	
	public static void main(String[] args) {
        VectorTest vectorTest = new VectorTest(10);
        
        vectorTest.add("a");
        vectorTest.add("b");
        vectorTest.add("c");
        
        vectorTest.update("d", 1);
        vectorTest.remove(0);
        for(int i=0;i<vectorTest.size;i++) {
        	  System.out.println("="+vectorTest.get(i));
        }
	}
}
