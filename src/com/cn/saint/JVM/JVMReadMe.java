package com.cn.saint.JVM;

/**
 * jvm的结构
 * 
 * .java 经过编译编程.class文件，然后由类加载器(class loader)负责装载编译后的字节码，并加载到
 * 运行时数据区(runtime data area)， 最后由execution engine
 * 执行这些字节码，变成机器可识别的native code。
 * 
 * 运行时数据区包括以下部分
 * 1. 程序计数器 -- 私有的。保存的是程序当前执行的指令的地址，当cpu需要执行指令时，
 * 需要从程序计数器得到当前需要执行的指令所在存储单元的地址，根据地址获取指令，得到指令后，计数器加1，如此循环，直至执行完所有的指令. 唯一没有规定oom的区域
 
 * 由于可能当前线程的任务并没有执行完毕，所以在切换时需要保存线程的运行状态，以便下次重新切换回来时能够继续切换之前的状态运行。举个简单的例子：比如一个线程A正在读取一个文件的内容，正读到文件的一半，
 * 此时需要暂停线程A，转去执行线程B，当再次切换回来执行线程A的时候，我们不希望线程A又从文件的开头来读取。

　　因此需要记录线程A的运行状态，那么会记录哪些数据呢？因为下次恢复时需要知道在这之前当前线程已经执行到哪条指令了，所以需要记录程序计数器的值，
另外比如说线程正在进行某个计算的时候被挂起了，那么下次继续执行的时候需要知道之前挂起时变量的值时多少，因此需要记录CPU寄存器的状态。所以一般来说，线程上下文切换过程中会记录程序计数器、CPU寄存器状态等数据。
 * 
 * 
 * 
 * 2. java栈 stack  -- 存的是一个个被调用的方法。每个方法被执行时，都会同时创建一个栈帧，用于存放局部变量表（基本类型变量）、操作栈、动态链接(对象的引用)、
 * 方法出口灯信息。当执行完一个方法后，对应的栈帧就会出栈。存取速度快。先进后出
 * 3. 本地方法栈 -- 存的是本地native方法
 * 4. java堆  heap -- 所有线程共享的内存区域，存的是对象本身以及数组， 运行时动态分配内存，存取速度慢。也是垃圾收集器管理的主要区域。称为grabage collected heap.
 * 分为两块区域：新生代，老年代。堆大小=新生代 1/3+老年代 2/3
 * 
 * 
 * 新生代又分为eden、from survior、to survior(8:1:1) 这样划分为了方便gc算法-复制算法来进行垃圾回收
 * jvm每次都会只使用eden和其中一块survivor来为对象服务。
 * 新生代是java对象出生的地方：当对象在eden，当此对象经过一次minior gc后仍然存活，并且能够被另外一块survior容纳，则复制算法会将
 * 他们复制到 to survivor中，然后清理eden和from survivor，并且年龄+1，以此累加。当达到一定年龄15时，成为老年代。通过参数-xx:maxtenuringThread类设置。
 * 
 * 老年代-- 经过survivor熬过来的
 * 永久代 -- 存放class和meta的信息。
 * 
 * 分代的理由：优化gc性能。
 * 
 * 5. 方法区    -- 被线程共享的区域。存的是每个类的信息（类名称、方法信息、字段信息），静态变量、常量池以及编译器编译后的代码。
 * 
 * java中通过多线程机制使得多个任务可以同时执行，所有线程共享jvm内存区域main memory,而每个线程又单独有自己的工作内存。
 * 
 * 例如：
 * Object obj = new Object();
 * 
 * "Object obj"会反映到java栈的本地变量表中，作为一个引用类型数据出现。
 * "new object()"会反映到java堆中，形成一快存储了oBJECT类型的结构化内存。
 * 
 * 内存溢出 - 没有足够的内存空间用来分配。
 * 内存泄漏 - 无法释放已申请的内存空间。
 * 
 * 垃圾回收算法
 * 
 * 1.标记-清除算法 -- 标记出需要被回收的对象，后回收对象所占用的引用。容易有内存碎片
 * 2.复制算法 -- 将内存分为2块大小一样，每次只使用其中一块。当一块内存用完，就将存活的对象复制到另一块，然后被当前内存块清理。没有碎片
 * 3.标记-整理算法 -- 标记处需要被回收的对象，将存活对象向一端移动，然后清理端边界以外的内存。
 * 4.分代收集 -- 按新生代和老年代收集。
 * 
 * 
 * gc什么时候发生：eden满了就会minor gc，升到老年代的对象大于老年代剩余空间就会full gc.可以通过调整参数
 * 控制新生代老年代的比例，或者控制进入老年代前的生存次数。
 * minnor gc -- 从年轻代空间回收内存。触发条件为当jvm无法为一个新对象分配内存空间。
 * major gc -- 清理老年代
 * full gc -- 清理整个堆空间，包括年轻代和老年代。
 * 
 * 对什么东西进行gc：引用计数为0，或者gc root达不到的对象，就会进行gc。
 * 做什么事情：根据不同的算法，对新生代和老年代的对象进行回收。
 * 
 * Java内存模型  Java Memory Model 
 * 
 * 定义了多线程之间共享变量的可见性以及如何在需要的时候对共享变量同步。
 * 共享变量存入主内存，每个线程有私有的本地内存。分为线程栈区和堆区
 -----------------------------------------------------------------
 * 创建对象的过程：
 * 从栈中的本地变量表中获取对象引用，然后到堆中获取对象实例
 * 
 */

public class JVMReadMe {

}
