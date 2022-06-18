package com.hspjava.exercise05;

import java.math.BigInteger;

public class enumExample {
    public static void main(String[] args) {
        BigInteger bigInteger1 = new BigInteger("1");
        BigInteger bigInteger2 = new BigInteger("1");
        BigInteger add = bigInteger1.add(bigInteger2);
        System.out.println(add.hashCode());
        System.out.println(bigInteger1.hashCode());
    }
}
enum Week{
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期天")
    ;

    private String name;
    private Week(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
