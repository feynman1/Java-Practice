package com.hspjava.exercise12;

import java.io.*;
import java.util.Properties;

public class ex12 {
    public static void main(String[] args) throws IOException {
        String propertiesPath = "src\\dog.properties";
        File file = new File(propertiesPath);
        if(!(file.exists())) {
            file.mkdirs();
        }
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        properties.setProperty("name","tom");
        properties.setProperty("age","5");
        properties.setProperty("color","red");
        properties.store(new FileOutputStream(propertiesPath),null);
        Dog dog = new Dog(properties.getProperty("name"), properties.getProperty("age"), properties.getProperty("color"));
        dog.toString();
        String objectPath = "d:\\mytemp\\dog.dat";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(objectPath));
        objectOutputStream.writeObject(dog);
        if(objectOutputStream!=null)
            objectOutputStream.close();
    }
}
class Dog implements Serializable{
    private String name;
    private String age;
    private String color;

    public Dog(String name, String age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}