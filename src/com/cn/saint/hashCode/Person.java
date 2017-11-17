package com.cn.saint.hashCode;
/** 
* @author saint
* @version 创建时间：2017年9月9日 下午3:36:18 
* 类说明 
*/
public class Person extends Object{

	private int id;
	private String name;
	
	/**
	 * 重写Object类的方法，在Object类中，对于equlas方法，是直接比较地址的
	 * 
	 * public boolean equals(Object obj) {
        return (this == obj);
       }
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public boolean ReEquals(Object obj) {
		if(obj instanceof Integer) {
			return id == ((Integer)obj).intValue();
		} 
			return false;
	}
	
	StringBuffer sBuffer = new StringBuffer();
	
}
