package com.xxx.rmi.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Description
 * @Date 2019/5/20 22:12
 * @Version 1.0
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Object service;

    public ProcessorHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());) {

            RpcRequest request = (RpcRequest) inputStream.readObject();
            outputStream.writeObject(invoke(request));
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest request) throws Exception {

        Object[] args = request.getArgs();
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        Method method = service.getClass().getMethod(request.getMethodName(), types);
        return method.invoke(service, args);
    }
}
