package com.xxx.rmi.rpc.zk;

/**
 * @ClassName RegisterCenter
 * @Description
 * @Date 2019/5/21 20:36
 * @Version 1.0
 */
public interface RegisterCenter {

    /**
     * 注册服务名称和服务地址
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName, String serviceAddress);
}
