package com.hspjava.moneyChange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class moneyChange {
    public static void main(String[] args) {
        boolean loop=true;
        Scanner scanner = new Scanner(System.in);
        String details="";
        String note;
        double money=0;
        double balance=0;
        //菜单的显示和处理
        do {
            System.out.println("======零钱通菜单======");
            System.out.println("\t\t\t1. 零钱通明细");
            System.out.println("\t\t\t2. 收益入账");
            System.out.println("\t\t\t3. 消费");
            System.out.println("\t\t\t4. 退出");
            System.out.println("请选择1-4");
            int i = scanner.nextInt();
            Date date = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//用于日期格式化
            switch (i){
                case 1:
                    System.out.println(details);
                    break;
                case 2:
                    System.out.print("收益入账金额:");
                    money = scanner.nextDouble();
                    if(money<=0){
                        System.out.println("输入有误");
                        break;
                    }
                    balance+=money;
                    date = new Date();
                    details+="\n收益入账\t" + money + "\t" + simpleDateFormat.format(date) +"\t"+balance;
                    System.out.println(details);
                    break;
                case 3:
                    System.out.print("消费事项：");
                    note = scanner.next();
                    System.out.print("消费金额：");
                    money=scanner.nextInt();
                    if(money<=0||money>balance){
                        System.out.println("输入有误");
                        break;
                    }
                    balance-=money;
                    date=new Date();
                    details+="\n消费事项：\t" + note + "\t" + "消费金额："+note+"\t"+simpleDateFormat.format(date) +"\t"+balance;
                    break;
                case 4:
                    String check="";
                    while(true){
                        System.out.print("您确定要退出:y/n");
                        check=scanner.next();
                        if(check.equals("y")||check.equals("n")){
                            break;
                        }
                    }
                    if(check.equals("y")){
                        loop=false;
                        break;
                    }else {
                        break;
                    }
                default:
                    System.out.println("输入有误");
                    break;
            }
        }while (loop);
    }
}
