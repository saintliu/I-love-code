package com.cn.saint.hashMap;

/** 
* @author saint
* @version 创建时间：2017年8月31日 下午5:20:42 
* 类说明 
 * @param <V>
*/

/**
 * 在HashMap中，数组中存储的是entry
 */
public interface MyMap<K, V> {

	/** 
	* @author saint
	* @version 创建时间：2017年8月31日 下午5:20:42 
	* 类说明 
	 * @param <K>
	*/
	//保存元素
	public <K> V put(K k, V v) ;
	//获取元素
	public V get(K k);
	
	//内部接口
	public interface Entry<K, V>{
		public K getKey();
		public V getValue();
	}
}
