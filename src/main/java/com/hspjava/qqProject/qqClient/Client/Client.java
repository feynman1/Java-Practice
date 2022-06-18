package com.hspjava.qqProject.qqClient.Client;

import com.hspjava.Utils.SysteminUtil;
import com.hspjava.qqProject.qqClient.ClientService.ClientConnectService;
import com.hspjava.qqProject.qqClient.ClientService.ClientFileMessage;
import com.hspjava.qqProject.qqClient.ClientService.ClientMessageService;

import java.io.IOException;

public class Client {
    public Client() throws IOException {
        boolean flag = true;
        System.out.println("========多用户聊天系统========");
        while(flag){
            System.out.println("1.登录系统");
            System.out.println("9.退出系统");
            //接收系统输入的数字
            String i = SysteminUtil.getString(1);
            //进入功能
            switch (i){
                case "1":
                    //接收用户的id以及密码
                    System.out.println("请输入用户id");
                    String userId = SysteminUtil.getString(10);
                    System.out.println("请输入密码");
                    String password = SysteminUtil.getString(20);
                    //建立连接
                    boolean isLogin = ClientConnectService.checkUser(userId,password);
                    //使用登录系统之后的二级功能
                    if(isLogin){
                        System.out.println("========欢迎用户"+userId+"登录系统========");
                        boolean flag2 = true;
                        while(flag2){
                            System.out.println("1.拉取在线用户列表");
                            System.out.println("2.群发信息");
                            System.out.println("3.私聊消息");
                            System.out.println("4.发送文件");
                            System.out.println("9.退出登录");
                            System.out.println("请输入你想要的服务:");
                            String string = SysteminUtil.getString(1);
                            switch (string){
                                case "1":
                                    ClientConnectService.getOnlineUser();
                                    break;
                                case "2":
                                    System.out.print("请输入你想发送消息:");
                                    String content = SysteminUtil.getString(10);
                                    ClientMessageService.sendToAll(userId,content);
                                    break;
                                case "3":
                                    System.out.print("请输入你想发送消息的用户id:");
                                    String getter = SysteminUtil.getString(10);
                                    System.out.print("请输入你想发送消息:");
                                    String content1 = SysteminUtil.getString(10);
                                    ClientMessageService.sendToOne(userId,getter,content1);
                                    break;
                                case "4":
                                    System.out.print("请输入你想发送消息的用户id:");
                                    String getter2 = SysteminUtil.getString(10);
                                    System.out.print("请输入你想发送文件的路径:");
                                    String src = SysteminUtil.getString(50);
                                    ClientFileMessage.sendFile(src,"d:\\abc.png",getter2,userId);
                                    break;
                                case "9":
                                    ClientConnectService.logout();
                                    flag2 = false;
                                    flag  = false;
                                    break;
                                default:
                                    System.out.println("输入错误");
                                    break;
                            }
                        }
                    }
                    break;
                case "9":
                    flag = false;
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
        System.out.println("========系统退出========");
        System.exit(0);
    }
}
