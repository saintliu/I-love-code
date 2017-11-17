package com.cn.saint.classLoaderImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/** 
* @author saint
* @version 创建时间：2017年9月13日 上午10:11:25 
* 类说明 
*/
public class FileSystemClassLoader extends ClassLoader{

	private String rootDir = "/Users/liuyusheng"; 
	
	public FileSystemClassLoader(String rootDir) {
		this.rootDir = rootDir;
	}
	
	//重写父类方法
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		//先查询有没有加载过这个类，如果已经加载，则直接返回，如果没有就加载
		Class<?> c = findLoadedClass(name);
		if(c != null) {
			System.out.println("====loaded by boostrap======");
			return c;
		}else {
			//让父类加载器 appclassloader去加载
			ClassLoader parent =  this.getParent(); 
			try {
				c = parent.loadClass(name);
			} catch (Exception e) {
			}
			if(c != null) {
	             System.out.println("========loaded by app/ext loader========");
				return c;
			} else {
				//如果都没有加载过，则自己尝试加载
				byte[] classData;
				try {
					classData = getClassData(name);
					if(classData == null) {
						throw new ClassNotFoundException();
					} else {
						c = defineClass(name, classData, 0, classData.length);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				return c;
			}
		}
	}

	private byte[] getClassData(String name) throws IOException {

		String path = rootDir + "/" + name.replace(".", "/")+".class";
		
		InputStream is = new FileInputStream(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		int temp;
		while((temp=is.read()) != -1) {
			baos.write(buffer, 0, temp);
		}

		return baos.toByteArray();
	}
}
