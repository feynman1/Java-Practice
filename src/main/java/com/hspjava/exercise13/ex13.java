package com.hspjava.exercise13;

import java.util.Scanner;

public class ex13 {
    public static void main(String[] args) {
        Thread1_ thread1_ = new Thread1_();
        Thread thread1 = new Thread(thread1_);
        Thread thread2 = new Thread(new Thread2_(thread1_));
        thread1.start();
        thread2.start();
    }
}
class Thread1_ implements Runnable{
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private boolean flag = true;
    @Override
    public void run() {
        while(flag) {
            double random = Math.random();
            System.out.println((int)(random*100));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Thread2_ implements Runnable{
    private Thread1_ thread1_ = null;
    public Thread2_(Thread1_ thread1_) {
        this.thread1_ = thread1_;
    }

    @Override
    public void run() {
        String next = null;
        Scanner scanner = new Scanner(System.in);
        while (true){
            next = scanner.next();
            System.out.println(next);
            if (next.equals("Q")){
                System.out.println("退出");
                thread1_.setFlag(false);
                break;
            }
        }
    }
}

