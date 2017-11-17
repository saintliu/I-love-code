package com.cn.saint.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap - 线程不安全
 * HashTable  - 线程安全，但效率低下。使用synchronzied来保证线程安全，但在竞争激烈的情况下HashTable的效率
 * 非常低下，因为当一个线程访问HashTable的同步方法时，其他线程访问HashTable的同步方法时，可能会
 * 进入阻塞或轮询状态，如果线程1使用put进行添加元素，线程2不但不能使用put方法添加元素，并且也不能使用
 * get方法来获取元素，所以竞争越激烈，效率越低。
 * 
 *ConcurrentHashMap - 是由Segment数组结构和HashEntry数组结构组成，Segment是一种可重入锁ReetrantLock, 
 *在ConcurrentHashMap扮演锁的角色，HashEntry则用于存储键值对数据。一个ConcurrentHashMap里包含一个
 *Segment数组，Segment的结构和HashMap类似，是一种数组和链表结构，每个segment守护着一个HashEntry数组里的元素，
 *当对HashEntry数组的数据进行修改时，必须首先获得他对应的Segnment锁。
 *
 *Segment的get，先经过一次哈希，然后使用这个哈希值通过哈希运算定位到Segment, 然后再通过哈希算法定位到元素。
 *不需要加锁： 原因是它的get方法里将要使用的共享变量都定义成volatile，如用于统计当前Segement大小的count字段和用于存储值的HashEntry的value。定义成volatile的变量，
 *能够在线程之间保持可见性，能够被多线程同时读，并且保证不会读到过期的值，但是只能被单线程写（有一种情况可以被多线程写，就是写入的值不依赖于原值），
 *在get操作里只需要读不需要写共享变量count和value，所以可以不用加锁。之所以不会读到过期的值，是根据Java内存模型的happen before原则，对volatile字段的写入操作先于读操作，
 *即使两个线程同时修改和获取volatile变量，get操作也能拿到最新的值，这是用volatile替换锁的经典应用场景。
 *
 *                             HashEntry -> HashEntry
 *                    Segment1 HashEntry -> HashEntry -> HashEntry -> HashEntry
 *ConcurrentHashMap   Segment2 HashEntry -> HashEntry
 *                    Segment3 HashEntry -> HashEntry
 *                             HashEntry -> HashEntry
 *
 */

public class MainApp {

	public static void main(String[] args) {

		Map<String, String> map = new ConcurrentHashMap<String, String>();
		
		/**
		 * 一个concurrentHashMap 包含一个数组的segment
		 * 
		 * final Segment<K,V>[] segments; 
		 * 
		 * 每一个segment有若干个相连的节点 
		 * 
		 * static final class HashEntry<K,V>{
		 *      final K key;
		 *      final int hash; 
		 *      volatile V value;
		 *      final HashEntry<K,V> next;
		 * }
		 * 
		 * 写操作是需要锁的synchronized. 读操作不需要
		 */
		
		/**
		 * Segment 数据结构
		 * 
		 * 
		 static final class Segment<K,V> extends ReetrantLock implement Serializable {
		 
		 //Segment中元素的数量
		 transient volatile int count;
		 //对table的大小造成影响的操作的数量
		 transient int modCount; 
		 //阈值，Segment里面元素的数量超过这个值，会对Segment进行扩容
		 transient int threshold;
		 //链表数组，数组中的每个元素代表了一个链表的头部（首个元素）
		 transient volatile HashEntry<K, V>[] table;
		 //负载因子
		 final float loadFactor;
		 }
		 */
		
		
		/**
		 * HashEntry 数据结构
		 * 
		 * 
		 static final class HashEntry<K, V>{
		 //元素的Key
		 final K key;
		 //元素的hash值
		 final int hash;
		 //元素的值 
		 volatile V value;
		 //指向的下一个元素
		 final HashEntry<K,V> next;
		 }
		 */
		
		/**
		 * ConcurrentHashMap - 初始化
		 public ConcurrentHashMap(int initialCapacity,  
                         float loadFactor, int concurrencyLevel) {  
    if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)  
        throw new IllegalArgumentException();  
   
    if (concurrencyLevel > MAX_SEGMENTS)  
        concurrencyLevel = MAX_SEGMENTS;  
   
    // Find power-of-two sizes best matching arguments  
    int sshift = 0;  
    int ssize = 1;  
    while (ssize < concurrencyLevel) {  
        ++sshift;  
        ssize <<= 1;  
    }  
    segmentShift = 32 - sshift;  
    segmentMask = ssize - 1;  
    this.segments = Segment.newArray(ssize);  
   
    if (initialCapacity > MAXIMUM_CAPACITY)  
        initialCapacity = MAXIMUM_CAPACITY;  
    int c = initialCapacity / ssize;  
    if (c * ssize < initialCapacity)  
        ++c;  
    int cap = 1;  
    while (cap < c)  
        cap <<= 1;  
   
    for (int i = 0; i < this.segments.length; ++i)  
        this.segments[i] = new Segment<K,V>(cap, loadFactor);  
}  
		 初始化有3个参数，一个initialCapacity， 初始容量， loadFactor, 负载参数， 
		 concurrentLevel, 代表内部的segment的数量，不可改变。后续需要扩容，也是对Segment中链表
		 的容量大小，这样不需要对整个HASHMAp做rehash。
		 
		 整个ConcurrentHashMap的初始化方法还是非常简单的，先是根据concurrentLevel来new出Segment，这里Segment的数量是不大于concurrentLevel的最大的2的指数，就是说Segment的数量永远是2的指数个，这样的好处是方便采用移位操作来进行hash，加快hash的过程。接下来就是根据intialCapacity确定Segment的容量的大小，
		 每一个Segment的容量大小也是2的指数，同样使为了加快hash的过程。
		 */
		
		/**
		 * get方法
		 public V get(Object key) {   
    int hash = hash(key.hashCode());    
    return segmentFor(hash).get(key, hash);   -- 确定Segment的 hash 
}
=================================================
      V get(Object key, int hash) {       --
    if (count != 0) { // read-volatile  
        HashEntry<K,V> e = getFirst(hash);   
        while (e != null) {   
            if (e.hash == hash && key.equals(e.key)) {   
                V v = e.value;   
                if (v != null)   
                    return v;   
                return readValueUnderLock(e); // recheck  
            }   
            e = e.next;   
        }   
    }   
    return null;   
}  
		 */
	}

}
