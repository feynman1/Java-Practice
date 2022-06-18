package com.hspjava.tankGame;

import java.io.Serializable;

public class Shot implements Runnable, Serializable {
    private int x,y;
    private int direction;//0:up,1:down,2:left,3:right
    private int speed;

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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private boolean isAlive;

    public Shot(int x, int y, int direction, int speed) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        this.isAlive = true;
    }

    public void shotMove(){
        switch (direction) {
            case 0:
                y-=speed;
                break;
            case 1:
                y+=speed;
                break;
            case 2:
                x-=speed;
                break;
            case 3:
                x+=speed;
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        while (true){
            if(isAlive==false){
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shotMove();//子弹移动
            if(!(x>=0&&x<=MyPanel.width&&y>=0&&y<=MyPanel.length)){
                isAlive = false;
            }
        }
    }
}
