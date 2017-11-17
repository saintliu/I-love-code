package com.cn.saint.byteTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/** 
* @author saint
* @version 创建时间：2017年11月6日 上午9:51:20 
* 类说明 
*/

/**  基本数据类型所占字节数
 *  boolean 依编译环境而定
 *  byte 1个字节
 *  short 2个字节
 *  char 2个字节
 *  int 4个字节
 *  long 8个字节
 *  float 4个字节
 *  double 8个字节
 *  
 *  1个字节8位
 */
public class MainApp {

	public static void main(String[] args) throws IOException {
		
		byte b1 = 127;
		byte b2 = -128; 
	    byte b3 = 'a';
	    byte b4 = 'A'; // 1 byte = 8位
	    
	    // byte b5 = 'aa';  invalid character constant
	    //byte b6 = '啊';  1个汉字，2个字节，16位
	    
	    short s1 = '啊';  // short 也是16位
	    
	   // short s2 = '你好';  2个汉字，32位
	    
	    char c1 = '汗';  // char也是16位
	    
	    int a = '你';
	    
	    String str = "中"; 
	    
	    byte[] by = str.getBytes();
	    
	    //UTF-8 中文3个字符
	    String str1 = new String(by, "UTF-8");
	    //GBK 中文2个字符
	    String str2 = new String(by, "GBK");
	    String str3 = new String(by, "ASCII");
	    
        for(byte b:str2.getBytes()) {
        	    System.out.println(b);
        }
	    
	    System.out.println(str1.getBytes().length);
	    System.out.println(str2.getBytes().length);
	    System.out.println(str3.getBytes().length);
	
	    //获取当前编码方式
	    System.out.println(System.getProperty("file.encoding"));
	    
	    //字符流
	    //Writer
	    //Writer <== OutputStreamWriter <== FileWriter
	    String s3 = "中国人";
	    PrintWriter pw = new PrintWriter("/Users/liuyusheng/1.txt");
	    
	    pw.write(s3);
	    pw.close();
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/liuyusheng/1.txt"), "UTF-8"));
	    
	    String s4 = br.readLine();
	    br.close();
	    System.out.println("bufferedread result:"+s4);
	    
	}
}
