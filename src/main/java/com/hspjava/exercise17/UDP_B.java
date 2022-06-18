package com.hspjava.exercise17;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDP_B {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8888);
        //接收答案
        byte[] bytes1 = new byte[1024];
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes1,bytes1.length);
        datagramSocket.receive(datagramPacket1);
        int length = datagramPacket1.getLength();
        byte[] data = datagramPacket1.getData();
        String s = new String(data, 0, length,"utf-8");
        //回答问题
        String answer = null;
        System.out.println(s);
        if(s.equals("四大名著是哪些")){
            answer = "红楼梦 西游记 水浒传 三国演义";
        }else {
            answer = "what?";
        }
        byte[] bytes = answer.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("10.128.235.11"),8887);
        datagramSocket.send(datagramPacket);
        //关闭流
        datagramSocket.close();
    }
}
