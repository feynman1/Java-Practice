package com.hspjava.qqProject.qqServer.server;

import com.hspjava.qqProject.qqCommon.Message;
import com.hspjava.qqProject.qqCommon.MessageType;
import com.hspjava.qqProject.qqCommon.User;
import com.hspjava.qqProject.qqServer.service.ServerConnectClientMap;
import com.hspjava.qqProject.qqServer.service.ServerConnectClientThread;
import com.hspjava.qqProject.qqServer.service.ServerSendMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class server {
    private ServerSocket serverSocket = null;
    private static HashMap<String,User> map = new HashMap<>();
    private static ConcurrentHashMap<String, ArrayList<Message>>  offLineMessageMap = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, ArrayList<Message>> getOffLineMessaageMap() {
        return offLineMessageMap;
    }

    //初始化用户信息
    static {
        map.put("1",new User("1","123456"));
        map.put("2",new User("2","123456"));
        map.put("3",new User("3","123456"));
        map.put("4",new User("4","123456"));
        map.put("5",new User("5","123456"));
        map.put("6",new User("6","123456"));
    }

    //确认登录
    public boolean checkUser(User user){
        if(!(map.containsKey(user.getUserId())))
            return false;
        if(!((map.get(user.getUserId()).getPassword().equals(user.getPassword())))){
            return false;
        }
        return true;
    }

    //启动服务器，监听请求
    public server() throws IOException, ClassNotFoundException, InterruptedException {
        boolean flag = true;
        //在9999端口监听
        serverSocket = new ServerSocket(9999);
        System.out.println("服务器在9999端口监听");
        ServerSendMessage serverSendMessage = new ServerSendMessage();
        serverSendMessage.start();
        while(flag){
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            User u = (User) objectInputStream.readObject();
            Message message = new Message();
            //进行账号和密码的确认
            if(checkUser(u)){
                message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                ServerConnectClientThread scct = new ServerConnectClientThread(socket,u.getUserId());
                scct.start();
                ServerConnectClientMap.addToMap(u.getUserId(),scct);
            }else {
                message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            //发送离线信息
            ArrayList<Message> messages = offLineMessageMap.get(u.getUserId());
            if(messages!=null){
                for(Message m:messages){
                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(m);
                }
            }
            //socket.shutdownOutput();
        }
        //服务器关闭后，serverSocket需要关闭
        System.out.println("服务器关闭");
        serverSocket.close();
    }
}
