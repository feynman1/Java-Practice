package com.hspjava.tankGame;

import java.io.Serializable;
import java.util.Vector;

/*
*
* */
public class Tank implements Serializable {
    private int x,y;
    private int direct;//0:up,1:down,2:left,3:right
    private int color;//0:yellow,1:bule
    private int type;//0:hero,1:enemy
    private int speed;
    private boolean isAlive;
    private Hero hero = null;

    public Hero getHero() {
        return hero;
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    private Vector<EnemyTank> enemyTanks = null;
    private Vector<Shot> shots = new Vector<>();

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    public boolean isOverlap(int direction){
        return false;
    }

    public Tank(int x, int y, int direct, int color, int type, int speed, Hero hero, Vector<EnemyTank> enemyTanks) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.color = color;
        this.type = type;
        this.speed = speed;
        this.hero = hero;
        this.enemyTanks = enemyTanks;
        this.isAlive = true;
    }

    public void moveUp(){
        y-=speed;
        if(y<0){
            y=0;
        }
        if(isOverlap(0)){
            y+=speed;
        }
    }

    public void moveDown(){
        y+=speed;
        if((y+106)>MyPanel.length){
            y=MyPanel.length-106;
        }
        if(isOverlap(1)){
            y-=speed;
        }
    }

    public void moveLeft(){
        x-=speed;
        if(x<0){
            x=0;
        }
        if(isOverlap(2)){
            x+=speed;
        }
    }

    public void moveRight(){
        x+=speed;
        if((x+78)>MyPanel.width){
            x=MyPanel.width-78;
        }
        if(isOverlap(3)){
            x-=speed;
        }
    }

    public void shot(){
        Shot shot = null;
        switch (this.getDirect()){
            case 0:
                shot = new Shot(getX()+20,getY(),getDirect(),2);
                shots.add(shot);
                break;
            case 1:
                shot = new Shot(getX()+20,getY()+60,getDirect(),2);
                shots.add(shot);
                break;
            case 2:
                shot = new Shot(getX(),getY()+20,getDirect(),2);
                shots.add(shot);
                break;
            case 3:
                shot = new Shot(getX()+60,getY()+20,getDirect(),2);
                shots.add(shot);
                break;
        }
        Thread thread = new Thread(shot);
        thread.start();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
