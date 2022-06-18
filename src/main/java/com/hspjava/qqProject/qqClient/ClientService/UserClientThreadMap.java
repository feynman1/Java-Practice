package com.hspjava.qqProject.qqClient.ClientService;

import com.hspjava.qqProject.qqClient.ClientService.UserClientThread;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class UserClientThreadMap {
    public static ConcurrentHashMap<String, UserClientThread> map = new ConcurrentHashMap<>();
    public static void addUserClientThread(String userId,UserClientThread thread){
        map.put(userId,thread);
    }
    public static UserClientThread getUserClientThread(String userId){
        return map.get(userId);
    }
}
