package com.xxx.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Description
 * @Date 2019/5/20 20:33
 * @Version 1.0
 */
public interface HelloService extends Remote {

    String sayHello(String message) throws RemoteException;
}
