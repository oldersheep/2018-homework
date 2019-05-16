package com.xxx.zookeeper.gp;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Date 2019/5/15 20:53
 * @Version 1.0
 */
public class ZkOperationDemo {

    final static Logger log = LoggerFactory.getLogger(ZkOperationDemo.class);

    private static final String zkServerPath = "192.168.0.111:2181,192.168.0.112:2181,192.168.0.113:2181";
    private static final Integer timeout = 5000;
    private static final String path = "/zk-persist-node";

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            ZooKeeper zooKeeper = new ZooKeeper(zkServerPath, timeout, event -> {
                System.out.println(event.getState());
                // 似乎这个不是很需要，除非连接不上
                if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                    countDownLatch.countDown();
                }
            });
            log.error("当前状态是：{}", zooKeeper.getState());
            countDownLatch.await();
            log.error("当前状态是：{}", zooKeeper.getState());

            // 增
            String result = zooKeeper.create(path, "create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            log.error("新建节点，返回值：{}", result);
            Thread.sleep(1000);
            Stat stat = new Stat();

            // 查
            byte[] data = zooKeeper.getData(path, null, stat);
            log.error("查询节点，返回值：{}", new String(data));

            // 改
            stat = zooKeeper.setData(path, "update".getBytes(), stat.getVersion());
            log.error("修改后节点状态：{}", stat);
            data = zooKeeper.getData(path, null, stat);
            log.error("查询节点，返回值：{}", new String(data));

            // 删
            zooKeeper.delete(path, stat.getVersion());

            System.in.read();
            zooKeeper.close();
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
