package com.xxx.concurrent.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * 中断线程的Demo
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class InterruptDemo {

    private static int i;

    public static void main(String[] args) throws InterruptedException {
        /*Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }, "interrupt");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();*/


        /*Thread thread = new Thread(() -> {
            while (true) {
                boolean in = Thread.currentThread().isInterrupted();
                if (in) {
                    System.out.println("before:" + in);
                    Thread.interrupted(); // 设置复位
                    System.out.println("after:" + Thread.currentThread().isInterrupted());
                }
            }
        }, "interrupt");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();*/

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "interrupt");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
        System.out.println("before:" + thread.isInterrupted());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after:" + thread.isInterrupted());
    }

}
