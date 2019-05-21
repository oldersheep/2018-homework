package com.xxx.rmi.rpc;

import com.xxx.rmi.rpc.zk.ServiceDiscovery;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Date 2019/5/20 22:17
 * @Version 1.0
 */
public class RpcClientProxy {

    private ServiceDiscovery serviceDiscovery;

    public RpcClientProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public <T> T clientProxy(final Class<T> interfacesClass, String version) {

        return (T) Proxy.newProxyInstance(interfacesClass.getClassLoader(),
                new Class[]{interfacesClass}, new RemoteInvocationHandler(serviceDiscovery, version));
    }
}
