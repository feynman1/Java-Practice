package com.hspjava.exercise16;

import com.hspjava.Utils.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //创建tcp连接
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        //读取图片文件，转换为byte[]
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("src\\20190215210323305.png"));
        byte[] bytes = StreamUtils.streamToBytes(bufferedInputStream);
        //将byte[]传给对方
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(bytes);
        socket.shutdownOutput();
        //接收对方的回复
        InputStream inputStream = socket.getInputStream();
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);
        //关闭流
        socket.close();
        bufferedInputStream.close();
    }
}
