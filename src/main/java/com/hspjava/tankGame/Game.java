package com.hspjava.tankGame;

import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Game extends JFrame {
    private MyPanel myPanel = null;
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        //游戏菜单部分
        boolean flag = true;
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        while (flag){
            System.out.println("********坦克大战********");
            File file = new File("D:\\TankGame\\game.dat");
            //如果有存档，则读取，否则只能开始新游戏。
            if(file.exists()) {
                System.out.println("********输入1继续游戏，输入2开始新的游戏********");
                try {
                    String next = scanner.next();
                    i = Integer.parseInt(next);
                } catch (NumberFormatException e) {
                    System.out.println("输入有误，请重新输入");
                    continue;
                }
                switch (i) {
                    case 1:
                        System.out.println("加载上一局游戏");
                        Game game1 = new Game(1);
                        flag = false;
                        break;
                    case 2:
                        System.out.println("开始新游戏，按WASD移动，J射击，Q退出");
                        Game game2 = new Game(2);
                        flag = false;
                        break;
                    default:
                        System.out.println("输入有误，请重新输入");
                        break;
                }
            }
            else{
                System.out.println("********输入2开始新的游戏********");
                try {
                    String next = scanner.next();
                    i = Integer.parseInt(next);
                } catch (NumberFormatException e) {
                    System.out.println("输入有误，请重新输入");
                    continue;
                }
                switch (i) {
                    case 2:
                        System.out.println("开始新游戏，按WASD移动，J射击，Q退出");
                        Game game2 = new Game(2);
                        flag = false;
                        break;
                    default:
                        System.out.println("输入有误，请重新输入");
                        break;
                }
            }
        }

    }
    //创建新游戏
    public Game(int i) throws InterruptedException, IOException, ClassNotFoundException {
       if(i==1){
            String p = "D:\\TankGame\\game.dat";
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(p));
            myPanel = (MyPanel) objectInputStream.readObject();
            objectInputStream.close();
            myPanel.LoadMyPanel();
        }
        else if(i==2) {
            myPanel = new MyPanel();
        }
        Thread thread = new Thread(myPanel);
        thread.start();
        this.add(myPanel);
        this.setSize(MyPanel.width, MyPanel.length);
        this.addKeyListener(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public Game(){
        System.out.println("sb");
    }
}


