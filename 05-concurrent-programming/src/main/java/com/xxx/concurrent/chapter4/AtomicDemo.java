package com.xxx.concurrent.chapter4;

/**
 * 原子性
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class AtomicDemo {

    private volatile static int count = 0;

    private static void inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::inc).start();
        }
        Thread.sleep(5000);
        System.out.println("最终结果：" + count);
    }
}
