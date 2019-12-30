package com.xxx.concurrent.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * volatile 可见性
 *
 * @author old boy
 * @version 1.0.0
 * @since 1.0.0
 */
public class VisibleDemo {

    private volatile static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (!stop) {
                i++;
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        stop = true;

    }
}
