package com.xxx.distributed.protocol.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @ClassName ClientDemo
 * @Description
 * @Author aitaiyo
 * @Date 2019/4/25 21:51
 * @Version 1.0
 */
public class ClientDemo {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8080;
    private static byte[] data = "Hello UDP".getBytes();
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName(IP);
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, address, PORT);
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
