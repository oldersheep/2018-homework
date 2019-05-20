package com.xxx.rmi.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Date 2019/5/20 22:19
 * @Version 1.0
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);

        TCPTransport transport = new TCPTransport(host, port);
        return transport.send(rpcRequest);
    }
}
