package com.xxx.zookeeper.gp;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Date 2019/5/16 21:50
 * @Version 1.0
 */
public class CuratorDemo {

    final static Logger log = LoggerFactory.getLogger(CuratorDemo.class);
    private static final String zkServerPath = "192.168.0.111:2181,192.168.0.112:2181,192.168.0.113:2181";
    private static final Integer timeout = 5000;
    private static final String path = "/zk-persist-node";

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(timeout)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();

        curatorFramework.start();
        // 增
        String result = curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath(path, "create".getBytes());
        log.error("创建节点，返回结果：{}", result);

        // 查
        Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath(path);
        log.error("查询节点{}的值为{}", path, new String(bytes));

        // 改
        stat = curatorFramework.setData().withVersion(stat.getVersion()).forPath(path, "update".getBytes());

        curatorFramework.delete().forPath(path);
    }
}
