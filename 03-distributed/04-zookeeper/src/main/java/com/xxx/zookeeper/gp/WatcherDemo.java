package com.xxx.zookeeper.gp;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Date 2019/5/15 21:17
 * @Version 1.0
 */
public class WatcherDemo {

    final static Logger log = LoggerFactory.getLogger(WatcherDemo.class);

    public static final String zkServerPath = "192.168.0.111:2181,192.168.0.112:2181,192.168.0.113:2181";
    public static final Integer timeout = 5000;
    public static final String path = "/zk-persist-node";

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            ZooKeeper zooKeeper = new ZooKeeper(zkServerPath, timeout, event -> {

                log.error("全局默认事件:{}", event.getType());
                // 似乎这个不是很需要，除非连接不上
                if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            // 创建节点
            zooKeeper.create(path, "create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            // 绑定事件 exists getData getChildren
            Stat stat = zooKeeper.exists(path, event -> {
                log.error(event.getType() + "->" + event.getPath());
                try {
                    zooKeeper.exists(event.getPath(), true);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            // 去触发事件
            stat = zooKeeper.setData(path, "update".getBytes(), stat.getVersion());
            Thread.sleep(1000);

            //
            zooKeeper.delete(path, stat.getVersion());
            System.in.read();

            zooKeeper.close();
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
