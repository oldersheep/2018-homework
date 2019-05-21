package com.xxx.rmi;

import com.xxx.rmi.rpc.annotation.RemoteService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Description
 * @Date 2019/5/20 20:37
 * @Version 1.0
 */
@RemoteService(value = HelloService.class, version = "2.0")
public class HelloServiceImpl2 extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl2() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String message) throws RemoteException {

        return "Hello 2.0, " + message;
    }
}
