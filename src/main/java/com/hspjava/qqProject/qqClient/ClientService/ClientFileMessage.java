package com.hspjava.qqProject.qqClient.ClientService;

import com.hspjava.Utils.StreamUtils;
import com.hspjava.qqProject.qqCommon.Message;
import com.hspjava.qqProject.qqCommon.MessageType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientFileMessage {
    public static void sendFile(String src,String des,String getter,String sender) throws IOException {
        //找出sender的socket
        UserClientThread userClientThread = UserClientThreadMap.getUserClientThread(sender);
        Socket socket = userClientThread.getSocket();
        //将文件以byte[]形式读出
        FileInputStream fileInputStream = new FileInputStream(src);
        byte[] bytes = StreamUtils.streamToBytes(fileInputStream);
        //创建message，将byte存入
        Message message = new Message();
        message.setSender(sender);
        message.setGetter(getter);
        message.setMesType(MessageType.FILE_COMM_FILE);
        message.setFile(bytes);
        message.setSrc(src);
        message.setDes(des);
        //发送message
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(message);
        System.out.println("已向用户"+getter+"发送文件"+src);
    }
}
