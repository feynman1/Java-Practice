package com.hspjava.exercise06;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Scanner;

public class Email {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入用户名");
            String name = scanner.next();
            System.out.println("密码");
            String password = scanner.next();
            System.out.println("邮箱");
            String email = scanner.next();
            try {
                check(name, password, email);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("请重新输入");
                continue;
            }
            break;
        }
    }
    public static void check(String name,String password,String email){

        if(name.length()<2||name.length()>4){
            throw new EmailFormatException("用户名格式错误");
        }
        if (password.length()!=6){
            throw new EmailFormatException("密码格式错误");
        }
        char[] chars = password.toCharArray();
        for(char a:chars){
            if((a-'0')>9||(a-'0')<0){
                throw new EmailFormatException("密码格式错误");
            }
        }
        int a = email.indexOf(".");
        int b = email.indexOf("@");
        if(a==-1||b==-1||a>b){
            throw new EmailFormatException("邮箱格式错误");
        }
    }
}

class EmailFormatException extends RuntimeException{
    public EmailFormatException(String message){
        super(message);
    }
}
