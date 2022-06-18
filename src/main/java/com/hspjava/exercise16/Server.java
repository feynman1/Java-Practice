package com.hspjava.exercise16;

import com.hspjava.Utils.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //监听端口，等待连接
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        //接收byte[]
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = StreamUtils.streamToBytes(inputStream);
        //将byte[]写入文件中
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("src\\img2.png"));
        bufferedOutputStream.write(bytes);
        //回复消息
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("服务端已收到消息");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        socket.shutdownOutput();
        //关闭所有流
        socket.close();
        inputStream.close();
    }
}
