package com.hspjava.tankGame;

import java.io.Serializable;

public class Bomb implements Serializable {
    private int x,y;
    private int life = 9;
    public void decline(){
        life--;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
