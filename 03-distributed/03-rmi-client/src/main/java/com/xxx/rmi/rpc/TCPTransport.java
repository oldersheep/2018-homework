package com.xxx.rmi.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Description
 * @Date 2019/5/20 22:20
 * @Version 1.0
 */
public class TCPTransport {
    /*private String host;
    private int port;
    public TCPTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }*/

    private String serviceAddress;

    public TCPTransport(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public Object send(RpcRequest request) {
        System.out.println("创建一个新的连接....");
        String[] arrays = serviceAddress.split(":");
        try (Socket socket = new Socket(arrays[0], Integer.valueOf(arrays[1]));
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())){

            outputStream.writeObject(request);
            outputStream.flush();

            return inputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException("发起远程异常服务失败：", e);
        }
    }
}
