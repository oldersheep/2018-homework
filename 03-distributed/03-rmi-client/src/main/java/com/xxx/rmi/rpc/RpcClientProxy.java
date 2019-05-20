package com.xxx.rmi.rpc;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Date 2019/5/20 22:17
 * @Version 1.0
 */
public class RpcClientProxy {

    public <T> T clientProxy(final Class<T> interfacesClass, final String host, final int port) {

        return (T) Proxy.newProxyInstance(interfacesClass.getClassLoader(),
                new Class[]{interfacesClass}, new RemoteInvocationHandler(host, port));
    }
}
