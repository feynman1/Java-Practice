package com.hspjava.exercise;

public class Computer {
    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMemmory() {
        return memmory;
    }

    public void setMemmory(String memmory) {
        this.memmory = memmory;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String cpu;
    public String memmory;
    public String disk;

    public Computer(){}
    public Computer(String cpu, String memmory, String disk) {
        this.cpu = cpu;
        this.memmory = memmory;
        this.disk = disk;
    }

    public void getDetails(){
        System.out.println("CPU:"+cpu+" memmory"+memmory+" disk"+disk);
    }

}
