# JVM

## 存储区域

> 方法区

线程共享。存储虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。该区域的回收目标主要是对常量池和对类型的写在。

【运行时常量池】是方法区的一部分。存放Class文件中定义的常量，常量在编译的时候确定，在类加载之后存放进常量池

> 虚拟机栈

线程私有，生命周期同线程同步。每个方法在运行的时候会推进帧栈，方法结束弹出帧栈，用于存储方法运行的局部变量。局部变量表需要的内存空间在编译的时候就已经确定！帧栈是java方法运行时的一个数据结构！方法运行起始就是帧的出栈和入栈的过程。每次运行一个方法，会在栈中分配一个方法帧的大小，推入栈中，方法结束，把帧清空，弹出栈。

当栈的深度超过了虚拟机允许的最大值的时候抛出StackOverflow错误，如果申请的内存不够时抛出OutOfMemoryError错误

> 本地方法栈

作用类似虚拟机栈，但是执行的是native方法，什么是native方法？也会同虚拟机栈一样抛出错误

> 堆

线程共享。在虚拟机启动的时候创建！存放类实例，是垃圾收集器管理的主要区域！

> 程序计数器

线程私有。存放程序执行的字节码指令，字节码解释器通过读取里面存储的值来运行程序。并且每个线程都会分配一个程序计数器，用来保存和恢复当前线程在上下文切换的线程环境，属于线程私有！

## 对象是如何访问的

新建的对象存放在堆存储区，访问的方式有【使用句柄】和【直接指针】

## 垃圾回收算法

==如何判断对象死亡？==

引用计数法，每当对象被引用，计数器会加1，计数器为0的对象即为死亡的对象。缺点是会出现循环引用的问题导致内存泄漏。
根搜索算法，垃圾收集器从GC Root开始搜索对象，如果对某个对象可达，则表示该对象存活，否则对象死亡，决解了循环引用的问题。

java中存在的引用类型：

1. 强引用
   1. 如果没有特别指定，则新建的对象都是强引用，常驻内存
2. 软引用
   1. 内存溢出前，会把对象回收
3. 弱引用
   1. 只能生存到下次垃圾回收前
4. 虚引用

> 标记-清除算法

最基础的算法，其他垃圾回收算法都是在其基础智商演变而来。缺点是效率低，然后是会产生很多内存空间碎片

> 复制算法

将内存去分为两部分，每次使用一块，当使用中的一块快用完的时候，会把活着的对象拷贝到另外一块，把当前的空间清理出来。

当前使用复制算法的垃圾收集器（手机新生代）并不是等分堆区域，而是按照8:1:1的形式分成三份，为Eden占8份，两个Survivor区域。一次使用Eden和一个Suivivor来存储区域，垃圾回收的时候，把存活的对象复制到未使用的Survivor，清理Eden和另一个Survivor区域。

> 标记-整理算法

基本操作同标记-清除算法，但是是通过移动存活对象的指针，使活着的对象放在一起，然后把边界的地方一次清理，这样不会造成空间碎片。

> 分代收集算法

把内存区域分割成几个部分，例如新生代，老生代，可以根据每个区域选择不同的算法。其中新生代死亡率高，可以选用复制算法，而老生代可以使用标记-清除或者标记-整理算法

## 收集器

> Serial收集器

单线程收集器，在垃圾回收的时候会停止用户所有的工作线程，知道垃圾手机完成。适用于client模式下的新生代收集器

> ParNew收集器

是Serial的多线程版本。适用于Server模式下的新生代收集器

> Parallel Scavenge收集器

新生代收集器，多线程，复制算法。

> Serial Old收集器

是Serial收集器老年代的版本，使用标记-整理算法。

> Parallel Old收集器

Parallel的老年代版本，使用标记-整理算法。

> CMS收集器（Mark Sweep）

基于标记-清除算法。回收过程：

==初始标记：==

停止所有工作线程，标记GC Root能直达的对象。

==并发标记：==

工作线程正常运行。

==重新标记：==

停止所有工作线程

==并发清除：==

工作线程正常运行。

> G1收集器

基于标记-整理算法实现。

## java版本

java版本是从45开始的，对应的jdk为1.1。jdk1。7对应的java版本为51，依次类推，可以向下兼容，不能向上兼容。

| java版本 | jdk版本 |
| -------- | ------- |
| 45       | 1.1     |
| 46       | 1.2     |
| 47       | 1.3     |
| 48       | 1.4     |
| 49       | 1.5     |
| 50       | 1.6     |
| 51       | 1.7     |
| 52       | 1.8     |

## Class文件的规范

头四个字节为class文件的识别符号。0xCAFEBABE
之后的四个字节为jdk的版本号。0x00000032，表示为版本号50

## 虚拟机的加载机制

加载->验证->准备->解析->初始化->使用->卸载

## java的内存模型和多线程编程

jvm的内存模型分为主存和工作内存。其中主存是所有线程共享的区域，工作内存是线程独享的区域，主存主要存放类变量，实例字段，静态字段等，而工作内存存放局部变量和方法参数等。

java内存模型规定，所有变量存放在主存中（相当于操作系统的内存），而工作内存需要使用的变量会从主存中拷贝一份到工作内存（相当于是操作系统的缓存），因此当多线程应用时候，存在竞争的问题，多个线程同事存储到主存中的一致性问题等。可以近似的把主存当作java堆，而工作内存当作虚拟机栈。