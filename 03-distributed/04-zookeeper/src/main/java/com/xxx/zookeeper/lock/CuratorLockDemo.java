package com.xxx.zookeeper.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @Description
 * @Date 2019/5/19 21:39
 * @Version 1.0
 */
public class CuratorLockDemo {

    public static void main(String[] args) {
        try {
            CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().build();

            InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/locks");
            interProcessMutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
