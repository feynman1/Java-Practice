package com.hspjava.qqProject.qqClient.ClientService;

import com.hspjava.qqProject.qqCommon.Message;
import com.hspjava.qqProject.qqCommon.MessageType;
import com.hspjava.qqProject.qqCommon.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public final class ClientConnectService {
    //因为在Client客户端会一直用到user的信息，因此作出成员属性
    private static User u = new User();
    //登录系统功能
    public static boolean checkUser(String userId,String password)  {
        //初始化user
        u.setUserId(userId);
        u.setPassword(password);
        //向服务端发送请求
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getByName("10.28.156.7"), 9999);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(u);
            //socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("======连接失败======");
            return false;
        }
        //接收服务端返回的请求
        boolean b = true;
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Message m = (Message) objectInputStream.readObject();
            if(m.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
                UserClientThread userClientThread = new UserClientThread(socket);
                userClientThread.start();
                //加入集合中，以便管理线程
                UserClientThreadMap.addUserClientThread(userId,userClientThread);
                b = true;
            }else {
                //关闭socket
                socket.close();
                System.out.println("======用户名或密码错误======");
                b = false;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }
    //向服务器请求在线用户
    public static void getOnlineUser() throws IOException {
        //获取socket
        UserClientThread userClientThread = UserClientThreadMap.getUserClientThread(u.getUserId());
        //向服务器发送类型为请求用户在线列表的message
        Socket socket = userClientThread.getSocket();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_USER);
        message.setSender(u.getUserId());
        objectOutputStream.writeObject(message);
    }
    //向服务器请求登出
    public static void logout() throws IOException {
        //获取socket
        UserClientThread userClientThread = UserClientThreadMap.getUserClientThread(u.getUserId());
        //向服务器发送类型为登出的message
        Socket socket = userClientThread.getSocket();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId());
        objectOutputStream.writeObject(message);
    }
}
