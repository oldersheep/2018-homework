package com.xxx.jvm;

/**
 * @Description
 * @Date 2019/7/4 21:47
 * @Version 1.0
 */
public class DeadLock {

    public void run() {
        MyThread mt = new MyThread();
        new Thread(mt, "san").start();
        new Thread(mt, "si").start();
    }

    class MyThread implements Runnable {
        private Object o1 = new Object();
        private Object o2 = new Object();
        private boolean flag = true;
        @Override
        public void run() {
            if (flag) {
                flag = false;
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "have o1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2) {
                        System.out.println(Thread.currentThread().getName() + "have o2");
                    }
                }
            } else {
                flag = true;
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "have o2");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1) {
                        System.out.println(Thread.currentThread().getName() + "have o1");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock obj = new DeadLock();
        obj.run();
    }
}
