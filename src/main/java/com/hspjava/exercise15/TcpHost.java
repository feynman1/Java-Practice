package com.hspjava.exercise15;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpHost {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=inputStream.read(buffer))!=-1){
            String s = new String(buffer, 0,len);
            System.out.println(s);
        }
        socket.close();
        inputStream.close();
    }
}
