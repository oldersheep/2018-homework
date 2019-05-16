package com.xxx.zookeeper.gp;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Date 2019/5/16 22:21
 * @Version 1.0
 */
public class CuratorWatcherDemo {

    final static Logger log = LoggerFactory.getLogger(CuratorDemo.class);
    private static final String zkServerPath = "192.168.0.111:2181,192.168.0.112:2181,192.168.0.113:2181";
    private static final Integer timeout = 5000;
    private static final String path = "/watch";

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(zkServerPath)
                .sessionTimeoutMs(timeout)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("curator")
                .build();

        curatorFramework.start();

        addListenerWithTreeCache(curatorFramework, path);

        System.in.read();
    }

    private static void addListenerWithTreeCache(CuratorFramework curatorFramework, String path) throws Exception {
        TreeCache pathChildrenCache = new TreeCache(curatorFramework, path);

        pathChildrenCache.getListenable().addListener((client, event) -> {
            log.error("收到事件：{}，数据为：{}，节点为{}", event.getType(), event.getData(), event.getData().getPath());
        });
        pathChildrenCache.start();
    }

    // 针对子节点的监听
    private static void addListenerWithPathChildrenCache(CuratorFramework curatorFramework, String path) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, path, true);

        pathChildrenCache.getListenable().addListener((client, event) -> {
            log.error("收到事件：{}", event.getType());
        });
        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }
    // 针对当前节点的监听
    private static void addListenerWithNodeCache(CuratorFramework curatorFramework, String path) throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, path, false);

        nodeCache.getListenable().addListener(() -> {
            log.error("收到事件：{}", nodeCache.getCurrentData().getPath());
        });
        nodeCache.start();
    }
}
