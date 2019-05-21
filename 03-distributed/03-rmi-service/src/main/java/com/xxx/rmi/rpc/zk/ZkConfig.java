package com.xxx.rmi.rpc.zk;

/**
 * @Description
 * @Date 2019/5/21 20:39
 * @Version 1.0
 */
public interface ZkConfig {

    String connectString = "192.168.0.111:2181,192.168.0.112:2181,192.168.0.113:2181";

    String nameSpace = "/registries";
}
