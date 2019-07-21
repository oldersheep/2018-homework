package com.xxx.distributed.jedis;

/**
 * @Description
 * @Date 2019/7/10 22:00
 * @Version 1.0
 */
public class UnitTest extends Thread {

    @Override
    public void run() {
        while (true) {
            JedisLock lock = new JedisLock();
            String result = lock.acquiredLock("update", 2000, 5000);
            if (result != null) {
                System.out.println(Thread.currentThread().getName() + "->获得锁：" + result);
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "->释放锁：" + result);
                    //lock.releaseLock("update", result);
                    lock.releaseLockWithLua("update", result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        UnitTest unitTest = new UnitTest();
        for (int i = 0; i < 10; i++) {
            new Thread(unitTest, "第"+i +"个").start();
        }
    }
}
