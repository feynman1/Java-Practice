package com.hspjava.exercise04;

public class innerClass {
    public static void main(String[] args) {
        CellPhone cellPhone = new CellPhone();
        cellPhone.alarmclock(new Bell(){
            public void ring(){
                System.out.println("懒猪起床了");
            }
        });
        Bell bell = new Bell() {
            public void ring() {
                System.out.println("小伙伴上课了");
            }
        };
        cellPhone.alarmclock(bell);
    }
}


class CellPhone{
    public void alarmclock(Bell bell){
        System.out.println(bell.getClass());
        bell.ring();
    }
}

interface Bell{
    public void ring();
}
