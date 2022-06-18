package com.hspjava.exercise08;

import java.util.*;

public class ex8 {
    public static void main(String[] args) {
        Set<Student> studentSet = new HashSet<Student>();
        Map<String,Student> stringStudentMap = new HashMap<String,Student>();
        Student a = new Student("A", 1);
        Student b = new Student("B", 2);
        studentSet.add(a);
        studentSet.add(b);
        stringStudentMap.put("A",a);
        stringStudentMap.put("B",b);
        Iterator<Student> iterator = studentSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
class Student{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
