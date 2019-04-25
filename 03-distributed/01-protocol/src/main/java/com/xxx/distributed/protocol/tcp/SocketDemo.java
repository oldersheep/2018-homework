package com.xxx.distributed.protocol.tcp;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @ClassName SocketDemo
 * @Description
 * @Author aitaiyo
 * @Date 2019/4/25 21:43
 * @Version 1.0
 */
public class SocketDemo {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) {

        try (Socket socket = new Socket(IP, PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println("Hello Socket");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
