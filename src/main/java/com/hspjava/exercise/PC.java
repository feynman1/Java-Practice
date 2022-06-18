package com.hspjava.exercise;

public class PC extends Computer{
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String brand;

    public PC(String cpu,String memory,String disk,String brand) {
        super(cpu,memory,disk);
        this.brand = brand;
    }
    public void printls(){
        getDetails();
        System.out.println(brand);
    }
}
