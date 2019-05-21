package com.xxx.rmi.rpc.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Description
 * @Date 2019/5/21 20:37
 * @Version 1.0
 */
public class RegisterCenterImpl implements RegisterCenter {

    private CuratorFramework curatorFramework;

    {
        this.curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZkConfig.connectString)
                .sessionTimeoutMs(4000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 5))
                .build();
        this.curatorFramework.start();
    }

    @Override
    public void register(String serviceName, String serviceAddress) {
        try {
            String servicePath = ZkConfig.nameSpace + "/" + serviceName;

            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
            }
            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath, "0".getBytes());

            System.out.println("服务注册成功..." + rsNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
