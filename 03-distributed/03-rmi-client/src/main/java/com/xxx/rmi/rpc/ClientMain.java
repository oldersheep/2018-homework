package com.xxx.rmi.rpc;

import com.xxx.rmi.HelloService;
import com.xxx.rmi.rpc.zk.ServiceDiscovery;
import com.xxx.rmi.rpc.zk.ServiceDiscoveryImpl;
import com.xxx.rmi.rpc.zk.ZkConfig;

import java.rmi.RemoteException;

/**
 * @Description
 * @Date 2019/5/20 22:39
 * @Version 1.0
 */
public class ClientMain {

    public static void main(String[] args) {
        /*RpcClientProxy proxy = new RpcClientProxy();

        HelloService helloService = proxy.clientProxy(HelloService.class, "localhost", 8888);
        System.out.println(helloService.sayHello("custom rpc"));*/

        ServiceDiscovery serviceDiscovery = new ServiceDiscoveryImpl(ZkConfig.connectString);
        RpcClientProxy proxy = new RpcClientProxy(serviceDiscovery);
        HelloService helloService = proxy.clientProxy(HelloService.class, "");
        System.out.println(helloService.sayHello("custom rpc"));
    }
}
