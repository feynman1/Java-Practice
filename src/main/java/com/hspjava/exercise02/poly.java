package com.hspjava.exercise02;

public class poly {
    public static void main(String[] args) {
        worker w = new worker("abc",11);
        manager m = new manager("cvb",100,111);
        poly p = new poly();
        p.showEmSal(w);
        p.showEmSal(m);
        p.check(w);
        p.check(m);
    }
    public void showEmSal(employee e){
        System.out.println(e.salary());
    }
    public void check(employee e){
        if (e instanceof worker){
            ((worker) e).work();
        }
        else if(e instanceof manager){
            ((manager) e).work();
        }else {
            System.out.println("do nothing");
        }
    }
}

class employee{
    private String name;
    private double sal;

    public double salary(){
        return 12*sal;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public employee(String name, double sal) {
        this.name = name;
        this.sal = sal;
    }
    public void work(){
            System.out.println("经理 "+getName()+" 在工作");
    }
}

class worker extends employee{
    public worker(String name, double sal) {
        super(name, sal);
    }
    public void work(){
        System.out.println("工人 "+getName()+" 在工作");
    }
    public double salary(){
        return super.salary();
    }
}

class manager extends employee{
    private double bonus;

    public manager(String name, double sal, double bonus) {
        super(name, sal);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
    public double salary(){
        return (super.salary()+bonus);
    }
}