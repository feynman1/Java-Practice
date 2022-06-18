package com.hspjava.exercise14;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool;

public class ex14 {
    public static void main(String[] args) {
        User user = new User();
        Thread thread1 = new Thread(user);
        Thread thread2 = new Thread(user);
        thread1.start();
        thread2.start();
    }
}
class User implements Runnable{
    public static int money = 10000;
    public static boolean flag = true;
    public synchronized void draw(){
        if(money==0){
            flag = false;
            return;
        }
        money -= 1000;
        System.out.println("剩余："+money);
    }
    public void run(){
        while (flag){
            draw();
        }
    }
}
