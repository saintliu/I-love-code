package com.cn.saint.sourceCode;
/** 
* @author saint
* @version 创建时间：2017年8月31日 上午9:38:13 
* 类说明 
*/

/**
 * 查询速度快，删除和插入效率低
 * 
 * 线程不安全
 *
 */
public class ArrayListTest {

	//本质就是一个object数组. 在jdk中，他是用transient修饰的，不可序列化
	//所以在jdk中，它有方法writeObject用来将数组元素转化为stream,以此实现序列化
	private Object[] elementData;
	//长度
	private int size; 
	
    public ArrayListTest() {
       //默认长度为10 
    	   this(10);		
	}
    //构造方法，当有初始容量时
    public ArrayListTest(int initialCapacity) {
		if(initialCapacity<0)
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		elementData = new Object[initialCapacity];
	}
    
    //判断长度方法
    public int size() {
    	  return size;
    }
	//判断是否为空
    public boolean isEmpty() {
		if(size==0) {
			return true;
		} else {
			return false;
		}
	}
	
    //数组越界判断
    public void rangeCheck(int index) {
		if(index <0 || index >= size) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
    
    //扩容
    public void ensureCapacity() {
    	    if(size >= elementData.length) {
    	    	   Object[] temp = new Object[elementData.length*3/2];
    	    	   System.arraycopy(elementData, 0, temp, 0, size);
    	    	   elementData = temp;
    	    }
    }
    
    //增删改查
    public void add(Object object) {
		ensureCapacity();
		elementData[size++] = object;
	}
    
    public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}
    
    public void update(int index, Object newObj) {
		rangeCheck(index);
		Object old = elementData[index];
		elementData[index] = newObj;
	}
    //删除
    public void remove(int index) {
		rangeCheck(index);
		ensureCapacity();
		//删除第index个数，从index+1开始复制到原数组第index个位置,长度为size-index-1
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		size--;
	}
    
	public static void main(String[] args) {
          ArrayListTest arrayListTest = new ArrayListTest(10);
          arrayListTest.add("a");
          arrayListTest.add("b");
          System.out.println("长度为:"+arrayListTest.size);
          arrayListTest.update(0, "c");
          System.out.println(arrayListTest.get(0));
          arrayListTest.remove(1);
          //测试是否支持null元素
          arrayListTest.add(null);
          //元素是否可以重复
          arrayListTest.add("b");
          arrayListTest.add("b");
          for(int i=0;i<arrayListTest.size;i++) {
    	        System.out.println("="+arrayListTest.get(i));
          }
	 }
}
