package com.xxx.rmi.rpc;

import com.xxx.rmi.HelloService;
import com.xxx.rmi.HelloServiceImpl;

import java.rmi.RemoteException;

/**
 * @Description
 * @Date 2019/5/20 22:37
 * @Version 1.0
 */
public class ServerMain {

    public static void main(String[] args) throws RemoteException {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(helloService, 8888);
    }
}
