package com.xxx.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @Description
 * @Date 2019/5/20 20:39
 * @Version 1.0
 */
public class Server {

    public static void main(String[] args) {
        try {
            HelloService helloService = new HelloServiceImpl();
            LocateRegistry.createRegistry(1099);
            Naming.bind("rmi://127.0.0.1/hello", helloService);
            System.out.println("服务发布成功!");
        } catch (RemoteException | MalformedURLException |AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
