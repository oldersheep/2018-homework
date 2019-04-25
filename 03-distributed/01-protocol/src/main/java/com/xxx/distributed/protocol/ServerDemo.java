package com.xxx.distributed.protocol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName ServerDemo
 * @Description 这里是得不到响应，就不能再发，单线程的响应
 * @Author aitaiyo
 * @Date 2019/4/25 21:57
 * @Version 1.0
 */
public class ServerDemo {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket socket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream());
             BufferedReader sin = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("client say: " + reader.readLine());

            String line = sin.readLine();
            while (!line.equals("quit")) {
                writer.println(line);
                writer.flush();
                System.out.println("client say:" + reader.readLine());
                line = sin.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
