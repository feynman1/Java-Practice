package com.hspjava.qqProject.qqServer.service;

import com.hspjava.qqProject.qqCommon.Message;
import com.hspjava.qqProject.qqCommon.MessageType;
import com.hspjava.qqProject.qqServer.server.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId;

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }
    public String getOnlineUser(){
        ConcurrentHashMap<String, ServerConnectClientThread> map = ServerConnectClientMap.getMap();
        Set<String> strings = map.keySet();
        String st="";
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            st=st+iterator.next()+" ";
        }
        return st;
    }
    public void run(){
        System.out.println("服务器与用户"+userId+"建立连接");
        boolean flag = true;
        InputStream inputStream = null;
        ObjectOutputStream objectOutputStream = null;
//        //发送离线信息
//        try {
//            ArrayList<Message> messages = server.getOffLineMessaageMap().get(userId);
//            if(messages!=null){
//                OutputStream outputStream = socket.getOutputStream();
//                objectOutputStream = new ObjectOutputStream(outputStream);
//                for(Message m:messages){
//                    objectOutputStream.writeObject(m);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //正常通信流程
        while (flag){
            try {
                inputStream = socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                OutputStream outputStream = socket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);
                //接收message
                Message m = (Message) objectInputStream.readObject();
                //后续操作
                //处理用户在线列表请求
                if(m.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_USER)){
                    Message message = new Message();
                    message.setGetter(m.getSender());
                    message.setContent(getOnlineUser());
                    message.setMesType(MessageType.MESSAGE_RET_ONLINE_USER);
                    objectOutputStream.writeObject(message);
                }
                //处理用户请求退出请求
                if(m.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    ServerConnectClientMap.getMap().remove(userId);
                    break;
                }
                //处理用户单独发送的请求
                if(m.getMesType().equals(MessageType.MESSAGE_COMM_MES)){

                    //获取socket
                    ServerConnectClientThread thread = ServerConnectClientMap.getThread(m.getGetter());
                    if(thread!=null) {
                        Socket socket = thread.getSocket();
                        //将message并发送给指定用户
                        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream1.writeObject(m);
                        System.out.println("用户" + m.getSender() + "向用户" + m.getGetter() + "发送消息");
                    }else {
                        ConcurrentHashMap<String, ArrayList<Message>> offLineMessaageMap = server.getOffLineMessaageMap();
                        ArrayList<Message> messages = offLineMessaageMap.get(m.getGetter());
                        if(messages==null){
                            offLineMessaageMap.put(m.getGetter(),new ArrayList<Message>());
                        }
                        offLineMessaageMap.get(m.getGetter()).add(m);
                    }
                }
                //处理用户群体发送的请求
                if(m.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    //获取所有用户socket
                    Set<Map.Entry<String, ServerConnectClientThread>> entries = ServerConnectClientMap.getMap().entrySet();
                    Iterator<Map.Entry<String, ServerConnectClientThread>> iterator = entries.iterator();
                    //构建message并发送给所有用户
                    while (iterator.hasNext()){
                        Map.Entry<String, ServerConnectClientThread> next = iterator.next();
                        if(!next.getKey().equals(m.getSender())){
                            ServerConnectClientThread value = next.getValue();
                            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(value.getSocket().getOutputStream());
                            objectOutputStream2.writeObject(m);
                        }
                    }
                    System.out.println("用户"+m.getSender()+"向所有用户发送消息");
                }
                //处理用户发送的文件
                if(m.getMesType().equals(MessageType.FILE_COMM_FILE)){
                    //获取socket
                    ServerConnectClientThread thread = ServerConnectClientMap.getThread(m.getGetter());
                    if(thread!=null) {
                        Socket socket = thread.getSocket();
                        //将message并发送给指定用户
                        ObjectOutputStream objectOutputStream3 = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream3.writeObject(m);
                        System.out.println("用户" + m.getSender() + "向用户" + m.getGetter() + "发送文件");
                    }
                    else {
                        ConcurrentHashMap<String, ArrayList<Message>> offLineMessaageMap = server.getOffLineMessaageMap();
                        ArrayList<Message> messages = offLineMessaageMap.get(m.getGetter());
                        if(messages==null){
                            offLineMessaageMap.put(m.getGetter(),new ArrayList<Message>());
                        }
                        offLineMessaageMap.get(m.getGetter()).add(m);
                    }
                }
                } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        System.out.println("服务器与用户"+userId+"断开连接");
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
