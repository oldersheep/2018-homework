package com.xxx.zookeeper.lock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description 分布式锁测试
 * @Date 2019/5/19 21:20
 * @Version 1.0
 */
public class DistributeLockTest {

    public static void main(String[] args) throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i< 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    DistributeLock distributeLock = new DistributeLock();
                    distributeLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }
}
