package com.xxx.concurrent.chapter2;

import java.util.concurrent.TimeUnit;

/**
 * 线程状态的演示
 *
 * 通过jps 与 jstack pid 命令查看当前线程的执行状态
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class ThreadStateDemo {

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"timed-waiting").start();

        new Thread(() -> {
            while (true) {
                synchronized (ThreadStateDemo.class) {
                    try {
                        ThreadStateDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "waiting").start();

        new Thread(new BlockDemo(), "blocked-0").start();
        new Thread(new BlockDemo(), "blocked-1").start();
    }

    static class BlockDemo extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (BlockDemo.class) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
