package com.hspjava.exercise10;

import java.util.*;

public class ex10 {
    public static void main(String[] args) {
        Employee a = new Employee("a", 10, new MyDate(12, 2010, 12));
        Employee b = new Employee("bc", 11, new MyDate(1, 2009, 11));
        Employee c = new Employee("a", 100, new MyDate(12, 2000, 12));
        ArrayList<Employee> arrayList = new ArrayList<>();
        Map<Employee,Integer> map = new HashMap<>();
        Collection<Integer> values = map.values();

        arrayList.add(a);
        arrayList.add(b);
        arrayList.add(c);
        arrayList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if((o1.getName().length()-o2.getName().length())!=0){
                    return (o1.getName().length()-o2.getName().length());
                }
                else {
                    if((o1.getMyDate().getYear()-o2.getMyDate().getYear())!=0){
                        return (o1.getMyDate().getYear()-o2.getMyDate().getYear());
                    }else if((o1.getMyDate().getMonth()-o2.getMyDate().getMonth())!=0){
                        return (o1.getMyDate().getMonth()-o2.getMyDate().getMonth());
                    }else {
                        return (o1.getMyDate().getDay()-o2.getMyDate().getDay());
                    }
                }
            }
        });
        for(Employee e:arrayList){
            System.out.println(e.toString());
        }
    }
}
class Employee{
    private String name;
    private int sal;
    private MyDate myDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public MyDate getMyDate() {
        return myDate;
    }

    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }

    public Employee(String name, int sal, MyDate myDate) {
        this.name = name;
        this.sal = sal;
        this.myDate = myDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", myDate=" + myDate +
                '}';
    }
}
class MyDate{
    private int month;
    private int year;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public MyDate(int month, int year, int day) {
        this.month = month;
        this.year = year;
        this.day = day;
    }

    private int day;

    @Override
    public String toString() {
        return "MyDate{" +
                "month=" + month +
                ", year=" + year +
                ", day=" + day +
                '}';
    }
}