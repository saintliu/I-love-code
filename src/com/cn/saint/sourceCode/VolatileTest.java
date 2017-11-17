package com.cn.saint.sourceCode;

/**
 * synchronized是一个笔记重量级的操作，对系统的性能有比较大的影响，所以有时候
 * 我们会使用volatile关键字解决可见性和有序性的问题
 * 
 * 对volatile字段的写入操作happens-before于每一个后续的同一个字段的读操作。
 * 可以保证写操作在读操作之前，也就保证了写操作对后续的读操作都是可见的。
 * 
 * DCL -- double check locking   双重检查加锁
 * 
 * public Resource getResource() {  
        if (resource == null) {    -- 1层
            synchronized(this) {  
                if (resource == null) -- 2层   
                    resource = new Resource();  
            }  
        }  
        return resource;  
    }  
 * 
 * 解决的问题有:
 * 
 * 1. 防止重排序 
 * 2. 实现可见性
 * 3. 原子性
 * 
 * 可见性问题主要指一个线程修改了共享变量值，而另一个线程却看不到。
 * 引起可见性问题的主要原因是每个线程拥有自己的一个高速缓存区——线程工作内存
 * 
 * 可见性只能保证每次读取的是最新的值
 * 
 */

public class VolatileTest {
	
	private volatile int m = 4; 

	int a = 1;
	int b = 2;
	
	public void change(){
		a = 3;
		b = a;
	}
	
	public void print(){
		System.out.println("b="+b+";a="+a);
	}

	public static void main(String[] args) {
		
		while(true){
			final VolatileTest test = new VolatileTest();
			
			new Thread(new Runnable(){

				public void run() {
					try {
						Thread.sleep(10);
					} catch(InterruptedException e){
						e.printStackTrace();
					}
					
					test.change();
				}
				
			}).start();
			
			new Thread(new Runnable(){

				public void run() {
					try {
						Thread.sleep(10);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					test.print();
				}
				
			}).start();
		}
	}

	/**
	 * result
	 * 
	 * 正常情况下是 先执行print b = 2,a = 1  or 先执行change b = 3; a = 3
	 * b=3, a=1  第一个线程 change a = 3, 但是对第二个线程不可见（看不到更新的值），所以当第二个线程执行的时候，a 还是1， 当第一个线程结束后，此时b=3。
	 * 如果将a 和 b 改成volatile类型，就不会这个问题
	 *
	 *在前文中已经提及过，线程本身并不直接与主内存进行数据的交互，而是通过线程的工作内存来完成相应的操作。这也是导致线程间数据不可见的本质原因。因此要实现volatile变量的可见性，直接从这方面入手即可。对volatile变量的写操作与普通变量的主要区别有两点：

　　（1）修改volatile变量时会强制将修改后的值刷新的主内存中。

　　（2）修改volatile变量后会导致其他线程工作内存中对应的变量值失效。因此，再读取该变量值的时候就需要重新从读取主内存中的值。
	 *
	 */
}
