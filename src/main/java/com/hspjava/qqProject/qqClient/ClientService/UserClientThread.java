package com.hspjava.qqProject.qqClient.ClientService;

import com.hspjava.qqProject.qqCommon.Message;
import com.hspjava.qqProject.qqCommon.MessageType;

import java.io.*;
import java.net.Socket;

public class UserClientThread extends Thread{
    private Socket socket;

    public UserClientThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        boolean flag = true;
        ObjectInputStream objectInputStream = null;

        while (flag){
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                //接收message
                Message m = (Message) objectInputStream.readObject();
                //后续操作
                //处理返回的用户在线列表
                if(m.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_USER)){
                    String content = m.getContent();
                    String[] s = content.split(" ");
                    for(int i=0;i<s.length;i++){
                        System.out.println("在线用户"+(i+1)+": "+s[i]);
                    }
                }
                //处理用户单独发送的消息
                if(m.getMesType().equals(MessageType.MESSAGE_COMM_MES)){
                    System.out.println("用户"+m.getSender()+"向您发送:"+m.getContent());
                }
                //处理用户群发的消息
                if(m.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    System.out.println("用户"+m.getSender()+"向所有在线用户发送:"+m.getContent());
                }
                //处理用户发送的文件
                if(m.getMesType().equals(MessageType.FILE_COMM_FILE)){
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(m.getDes()));
                    bufferedOutputStream.write(m.getFile());
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    System.out.println("用户"+m.getSender()+"向您发送文件"+m.getDes());
                }
                if(m.getMesType().equals(MessageType.SERVER_COMM_MES)){
                    System.out.println(m.getSender()+"向您发送:"+m.getContent());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.close();
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
