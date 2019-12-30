# chapter1

章节1的Demo是一个以责任链为案例展示的一个线程执行的过程

# chapter2

章节2的Demo讲的是线程的状态出现的情况

```
target\classes\com\xxx\concurrent>jps
11472 RemoteMavenServer36
4800
8644 KotlinCompileDaemon
11164 ThreadStateDemo
8316 Jps

target\classes\com\xxx\concurrent>jstack 11164
2019-09-08 21:58:23
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.25-b02 mixed mode):

"DestroyJavaVM" #19 prio=5 os_prio=0 tid=0x0000000002a0e800 nid=0x2c2c waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"blocked-1" #18 prio=5 os_prio=0 tid=0x0000000019037800 nid=0x680 waiting for monitor entry [0x0000000019cbf000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.xxx.concurrent.chapter2.ThreadStateDemo$BlockDemo.run(ThreadStateDemo.java:49)
        - waiting to lock <0x00000000d63a9bb0> (a java.lang.Class for com.xxx.concurrent.chapter2.ThreadStateDemo$BlockDemo)
        at java.lang.Thread.run(Thread.java:745)

"blocked-0" #16 prio=5 os_prio=0 tid=0x0000000019035000 nid=0x840 waiting on condition [0x0000000019bbe000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at java.lang.Thread.sleep(Thread.java:340)
        at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
        at com.xxx.concurrent.chapter2.ThreadStateDemo$BlockDemo.run(ThreadStateDemo.java:49)
        - locked <0x00000000d63a9bb0> (a java.lang.Class for com.xxx.concurrent.chapter2.ThreadStateDemo$BlockDemo)
        at java.lang.Thread.run(Thread.java:745)

"waiting" #14 prio=5 os_prio=0 tid=0x0000000019031800 nid=0x3354 in Object.wait() [0x0000000019abf000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000000d6159aa0> (a java.lang.Class for com.xxx.concurrent.chapter2.ThreadStateDemo)
        at java.lang.Object.wait(Object.java:502)
        at com.xxx.concurrent.chapter2.ThreadStateDemo.lambda$main$1(ThreadStateDemo.java:31)
        - locked <0x00000000d6159aa0> (a java.lang.Class for com.xxx.concurrent.chapter2.ThreadStateDemo)
        at com.xxx.concurrent.chapter2.ThreadStateDemo$$Lambda$2/1654589030.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:745)

"time-waiting" #13 prio=5 os_prio=0 tid=0x0000000019030800 nid=0x1b7c waiting on condition [0x00000000199be000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at java.lang.Thread.sleep(Thread.java:340)
        at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
        at com.xxx.concurrent.chapter2.ThreadStateDemo.lambda$main$0(ThreadStateDemo.java:20)
        at com.xxx.concurrent.chapter2.ThreadStateDemo$$Lambda$1/834133664.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:745)


.....

JNI global references: 2183
```
## 线程的状态

A thread state.  A thread can be in one of the following states:
<ul>
    <li>{@link #NEW}<br>
        A thread that has not yet started is in this state.
    </li>
    <li>{@link #RUNNABLE}<br>
        A thread executing in the Java virtual machine is in this state.
    </li>
    <li>{@link #BLOCKED}<br>
        A thread that is blocked waiting for a monitor lock
        is in this state.<br>
        等待阻塞 wait<br>
        同步阻塞 synchronize<br>
        其他阻塞 sleep/join<br>
    </li>
    <li>{@link #WAITING}<br>
        A thread that is waiting indefinitely for another thread to
        perform a particular action is in this state.
    </li>
    <li>{@link #TIMED_WAITING}<br>
        A thread that is waiting for another thread to perform an action
        for up to a specified waiting time is in this state.
    </li>
    <li>{@link #TERMINATED}<br>
        A thread that has exited is in this state.
    </li>
</ul>

# chapter3 
线程中断的demo

# chapter4

原子性、可见性、有序性

线程是CPU的最小调度单元

use/load/read/lock
assign/store/write/unlock