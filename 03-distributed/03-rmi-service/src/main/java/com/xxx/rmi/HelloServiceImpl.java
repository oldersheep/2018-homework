package com.xxx.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Description
 * @Date 2019/5/20 20:37
 * @Version 1.0
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    public HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String message) throws RemoteException {

        return "Hello " + message;
    }
}
