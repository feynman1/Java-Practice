package com.hspjava.exercise17;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDP_A {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8887);
        //输入问题
        System.out.println("请输入你的问题");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        byte[] bytes = next.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("10.128.235.11"),8888);
        datagramSocket.send(datagramPacket);
        //接收答案
        byte[] bytes1 = new byte[1024];
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes1,bytes1.length);
        datagramSocket.receive(datagramPacket1);
        byte[] data = datagramPacket1.getData();
        int length = datagramPacket1.getLength();
        String s = new String(data, 0, length,"utf-8");
        System.out.println(s);
        //关闭流
        datagramSocket.close();
    }
}
