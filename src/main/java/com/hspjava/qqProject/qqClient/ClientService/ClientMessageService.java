package com.hspjava.qqProject.qqClient.ClientService;

import com.hspjava.qqProject.qqCommon.Message;
import com.hspjava.qqProject.qqCommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMessageService {
    public static void sendToOne(String sender,String getter,String content) throws IOException {
        //获取sender的socket
        UserClientThread userClientThread = UserClientThreadMap.getUserClientThread(sender);
        Socket socket = userClientThread.getSocket();
        //构建message并发送
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        message.setContent(content);
        message.setGetter(getter);
        message.setSender(sender);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(message);
        System.out.println("已向用户"+getter+"发送信息");
    }
    public static void sendToAll(String sender,String content) throws IOException {
        //获取sender的socket
        UserClientThread userClientThread = UserClientThreadMap.getUserClientThread(sender);
        Socket socket = userClientThread.getSocket();
        //构建message并发送
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);
        message.setContent(content);
        message.setSender(sender);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(message);
        System.out.println("已向所有用户群发信息");
    }
}
