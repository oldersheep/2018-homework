package com.xxx.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @Description
 * @Date 2019/5/20 20:42
 * @Version 1.0
 */
public class Client {

    public static void main(String[] args) {
        try {
            HelloService helloService = (HelloService) Naming.lookup("rmi://127.0.0.1/hello");
            String result = helloService.sayHello("java rmi");
            System.out.println(result);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
