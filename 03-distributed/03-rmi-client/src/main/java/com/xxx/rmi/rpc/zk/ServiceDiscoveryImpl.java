package com.xxx.rmi.rpc.zk;

import com.xxx.rmi.rpc.zk.ld.LoadBalance;
import com.xxx.rmi.rpc.zk.ld.RandomLoadBalance;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * @Description
 * @Date 2019/5/21 21:22
 * @Version 1.0
 */
public class ServiceDiscoveryImpl implements ServiceDiscovery {

    private CuratorFramework curatorFramework;
    private List<String> repositories;

    private String connectString;

    public ServiceDiscoveryImpl(String connectString) {
        this.connectString = connectString;
        this.curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 5))
                .build();
        this.curatorFramework.start();
    }

    @Override
    public String discovery(String serviceName) {
        String path = ZkConfig.nameSpace + "/" + serviceName;
        try {

            repositories = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常...", e);
        }
        // 动态发现服务节点变化
        registerWatcher(path);

        LoadBalance loadBalance = new RandomLoadBalance();

        return loadBalance.selectHost(repositories);
    }

    private void registerWatcher(String path) {
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework, path, true);

        childrenCache.getListenable().addListener((curatorFramework, event) -> {
            repositories = curatorFramework.getChildren().forPath(path);
        });
        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PathChildrenCache异常", e);
        }
    }
}
