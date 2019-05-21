package com.xxx.rmi.rpc;

import com.xxx.rmi.HelloService;
import com.xxx.rmi.HelloServiceImpl;
import com.xxx.rmi.HelloServiceImpl2;
import com.xxx.rmi.rpc.zk.RegisterCenter;
import com.xxx.rmi.rpc.zk.RegisterCenterImpl;

/**
 * @Description
 * @Date 2019/5/20 22:37
 * @Version 1.0
 */
public class ServerMain {

    public static void main(String[] args) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        HelloService helloService2 = new HelloServiceImpl2();
        RegisterCenter registerCenter = new RegisterCenterImpl();
        RpcServer rpcServer = new RpcServer(registerCenter, "127.0.0.1:8088");
        rpcServer.bind(helloService, helloService2);
        rpcServer.publisher();
        System.in.read();
    }
}
