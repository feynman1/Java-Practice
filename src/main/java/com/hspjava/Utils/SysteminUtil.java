package com.hspjava.Utils;

import java.util.Scanner;

public final class SysteminUtil {
    private static Scanner scanner = new Scanner(System.in);
   public static String getString(int i){
       boolean flag = true;
       String next = null;
       while(flag) {
           next = scanner.next();
           if(next.length()>0&&next.length()<=i){
               flag = false;
           }else {
               System.out.println("长度必须在0与"+i+"之间");
           }
       }
       return next;
   }
}
