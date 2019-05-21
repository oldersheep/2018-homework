package com.xxx.rmi.rpc;

import com.xxx.rmi.rpc.annotation.RemoteService;
import com.xxx.rmi.rpc.zk.RegisterCenter;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Date 2019/5/20 22:09
 * @Version 1.0
 */
public class RpcServer {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private RegisterCenter registerCenter;
    private String serviceAddress;
    // 存储服务名称与服务对象之间的关系
    Map<String, Object> handlerMap = new HashMap<>();

    public RpcServer(RegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     * @param services
     */
    public void bind(Object... services) {
        for (Object service : services) {
            RemoteService annotation = service.getClass().getAnnotation(RemoteService.class);
            String serviceName = annotation.value().getName();
            String version = annotation.version();
            if (!"".equals(version)) {
                serviceName = serviceName + "-" + version;
            }
            handlerMap.put(serviceName, service);
        }
    }

    public void publisher() {
        String[] addresses = serviceAddress.split(":");
        try (ServerSocket serverSocket = new ServerSocket(Integer.valueOf(addresses[1]))) {
            for (String interfaceName : handlerMap.keySet()) {
                registerCenter.register(interfaceName, serviceAddress);
                System.out.printf("注册服务成功：%s->%s \\r\\n", interfaceName, serviceAddress);
            }
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket, handlerMap));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* // rmi手写
    public void publisher(final Object service, int port) {
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket, service));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
