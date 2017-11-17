package com.cn.saint.sourceCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TransientTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		User user = new User();
		user.setPassword("123456");
		user.setUsername("abc");
		
		System.out.println("read before serializable:");
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		
		ObjectOutputStream os = new ObjectOutputStream(new
				FileOutputStream("E:/study_note/user.txt"));
		
		os.writeObject(user);
		/**
		 * 一般主要用在IO中，即清空缓冲区数据，就是说你用读写流的时候，
		 * 其实数据是先被读到了内存中，然后用数据写到文件中，当你数据读完的时候不代表你的数据已经写完了，
		 * 因为还有一部分有可能会留在内存这个缓冲区中。这时候如果你调用了 close()方法关闭了读写流，
		 * 那么这部分数据就会丢失，所以应该在关闭读写流之前先flush()，先清空数据。
		 */
		os.flush();
		os.close();
		
		ObjectInputStream is = new ObjectInputStream(new
				FileInputStream("E:/study_note/user.txt"));
		
		user = (User)is.readObject();
		is.close();
		
		System.out.println("\n read after Serialable: ");
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		
		
		/**
		 * result:
		 * 
		 * read before serializable:
		username: abc
		password: 123456
		
		 read after Serialable: 
		username: abc
		password: null
		 */
		
	}
	
}

class User implements Serializable {

	/**
	 * Eclipse自动生成：点击类名
	 */
	private static final long serialVersionUID = 7235925119579530713L;
	
	private String username; 
	
	private transient String password;
	
//	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
}