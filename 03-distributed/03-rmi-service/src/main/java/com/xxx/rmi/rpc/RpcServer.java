package com.xxx.rmi.rpc;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Date 2019/5/20 22:09
 * @Version 1.0
 */
public class RpcServer {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public void publisher(final Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket, service));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
