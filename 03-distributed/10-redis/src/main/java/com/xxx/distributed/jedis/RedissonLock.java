package com.xxx.distributed.jedis;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Date 2019/7/14 21:02
 * @Version 1.0
 */
public class RedissonLock {

    public static void main(String[] args) {
        Config config = new Config();

        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock("updateR");
        try {
            lock.tryLock(100, 10, TimeUnit.SECONDS);
            System.out.println("业务处理....");
            Thread.sleep(1000);
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
