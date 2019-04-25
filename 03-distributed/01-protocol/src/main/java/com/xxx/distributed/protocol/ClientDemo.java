package com.xxx.distributed.protocol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @ClassName ClientDemo
 * @Description
 * @Author aitaiyo
 * @Date 2019/4/25 22:03
 * @Version 1.0
 */
public class ClientDemo {

    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8080);
             BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(socket.getOutputStream());
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            String line = sin.readLine();
            while (!line.equals("quit")) {
                writer.println(line);
                writer.flush();
                System.out.println("Server say:" + reader.readLine());
                line = sin.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
