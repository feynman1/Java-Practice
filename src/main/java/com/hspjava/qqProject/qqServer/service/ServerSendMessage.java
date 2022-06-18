package com.hspjava.qqProject.qqServer.service;

import com.hspjava.Utils.SysteminUtil;
import com.hspjava.qqProject.qqCommon.Message;
import com.hspjava.qqProject.qqCommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ServerSendMessage extends Thread{
    @Override
    public void run() {
        boolean flag = true;
        while (flag){
            System.out.print("请输入想群发的消息");
            String string = SysteminUtil.getString(50);
            Message message = new Message();
            message.setMesType(MessageType.SERVER_COMM_MES);
            message.setContent(string);
            message.setSender("服务器");
            ConcurrentHashMap<String, ServerConnectClientThread> map = ServerConnectClientMap.getMap();
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                String next = iterator.next();
                OutputStream outputStream = null;
                try {
                    outputStream = map.get(next).getSocket().getOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("发送成功");
        }
    }
}
