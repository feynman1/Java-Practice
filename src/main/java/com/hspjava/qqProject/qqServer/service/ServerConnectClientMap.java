package com.hspjava.qqProject.qqServer.service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ServerConnectClientMap {
    public static ConcurrentHashMap<String, ServerConnectClientThread> getMap() {
        return map;
    }
    //由于Hashmap不是线程安全的，而服务器中会存在多个进程对map同时操作，所以要选用ConcurrentHashMap
    private static ConcurrentHashMap<String,ServerConnectClientThread> map = new ConcurrentHashMap<>();
    public static void addToMap(String userId,ServerConnectClientThread thread){
        map.put(userId,thread);
    }
    public static ServerConnectClientThread getThread(String userId){
        return map.get(userId);
    }
}
