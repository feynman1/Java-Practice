package com.hspjava.tankGame;

import java.io.Serializable;
import java.util.Vector;

public class EnemyTank extends Tank implements Runnable, Serializable {
    public EnemyTank(int x, int y, int direct, int type, int color, int speed, Hero hero, Vector<EnemyTank> enemyTanks) {
        super(x, y, direct, color, type, speed,hero,enemyTanks);
    }

    public int chooseDirection(){
        //产生0-3的数字
        int i = 0;
        double random = Math.random();
        i = (int)(random*4);
        return i;
    }


    @Override
    public boolean isOverlap(int direction) {
        int hx = getHero().getX(),hy = getHero().getY(),ex = 0,ey = 0,x = getX(),y = getY();
        Vector<EnemyTank> enemyTanks = getEnemyTanks();
        EnemyTank enemyTank = null;
        switch (direction){
            case 0:
                for(int j=getX();j<(getX()+40);j++) {
                    if (getHero().isAlive()) {
                        if (getHero().getDirect() == 0 || getHero().getDirect() == 1) {
                            if ((j >= hx && j <= hx + 40 && y >= hy && y <= hy + 60) || (j >= hx && j <= hx + 40 && (y + 1 >= hy) && (y + 1 <= hy + 60))) {
                                return true;
                            }
                        }
                        if (getHero().getDirect() == 2 || getHero().getDirect() == 3) {
                            if ((j >= hx && j <= hx + 60 && y >= hy && y <= hy + 40) || (j >= hx && j <= hx + 60 && y + 1 >= hy && y + 1 <= hy + 40)) {
                                return true;
                            }
                        }
                    }
                }
                for(int i=0;i<enemyTanks.size();i++){
                    enemyTank = enemyTanks.get(i);
                    if(enemyTank.isAlive()&&enemyTank.hashCode()!=this.hashCode()){
                        ex = enemyTank.getX();
                        ey = enemyTank.getY();
                        //根据坦克的方向判断重叠边界
                        for(int j=getX();j<(getX()+40);j++){
                            if(enemyTank.getDirect()==0||enemyTank.getDirect()==1) {
                                if ((j >= ex && j <= (ex + 40) && y >= ey && y < (ey + 60))
                                        || (j >= ex && j <= (ex + 40) && ((y + 1) >= ey) && ((y + 1) <= (ey + 60)))) {
                                    return true;
                                }
                            }
                            if(enemyTank.getDirect()==2||enemyTank.getDirect()==3) {
                                if ((j >= ex && j <= ex + 60 && y >= ey && y <= ey + 40) || (j >= ex && j <= ex + 60 && ((y + 1) >= ey) && ((y + 1) <= ey + 40))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int j=getX();j<(getX()+40);j++) {
                    if (getHero().isAlive()) {
                        if (getHero().getDirect() == 0 || getHero().getDirect() == 1) {
                            if ((j >= hx && j <= hx + 40 && y + 60 >= hy && y + 60 <= hy + 60) || (j >= hx && j <= hx + 40 && ((y + 59) >= hy) && ((y + 59) <= hy + 60))) {
                                return true;
                            }
                        }
                        if (getHero().getDirect() == 2 || getHero().getDirect() == 3) {
                            if ((j >= hx && j <= hx + 60 && y + 60 >= hy && y + 60 <= hy + 40) || (j >= hx && j <= hx + 60 && ((y + 59) >= hy) && ((y + 59) <= hy + 40))) {
                                return true;
                            }
                        }
                    }
                }
                for(int i=0;i<enemyTanks.size();i++){
                    enemyTank = enemyTanks.get(i);
                    if(enemyTank.isAlive()&&enemyTank.hashCode()!=this.hashCode()){
                        ex = enemyTank.getX();
                        ey = enemyTank.getY();
                        for(int j=getX();j<(getX()+40);j++){
                            if(enemyTank.getDirect()==0||enemyTank.getDirect()==1) {
                                if ((j >= ex && j <= (ex + 40) && (y+60) >= ey && (y+60) <= (ey + 60)) || (j >= ex && j <= (ex + 40) && ((y+59) >= ey) && ((y+59) <= (ey + 60)))) {
                                    return true;
                                }
                            }
                            if(enemyTank.getDirect()==2||enemyTank.getDirect()==3) {
                                if ((j >= ex && j <= ex + 60 && (y+60) >= ey && (y+60) <= ey + 40) || (j >= ex && j <= ex + 60 && ((y+59) >= ey) && ((y+59) <= ey + 40))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int j=getY();j<(getY()+40);j++) {
                    if (getHero().isAlive()) {
                        if (getHero().getDirect() == 0 || getHero().getDirect() == 1) {
                            if ((x >= hx && x <= hx + 40 && j >= hy && j <= hy + 60) || (x >= hx && (x + 1) <= hx + 40 && (j >= hy) && (j <= hy + 60))) {
                                return true;
                            }
                        }
                        if (getHero().getDirect() == 2 || getHero().getDirect() == 3) {
                            if ((x >= hx && x <= hx + 60 && j >= hy && j <= hy + 40) || (x >= hx && (x + 1) <= hx + 60 && (j >= hy) && (j <= hy + 40))) {
                                return true;
                            }
                        }
                    }
                }
                for(int i=0;i<enemyTanks.size();i++){
                    enemyTank = enemyTanks.get(i);
                    if(enemyTank.isAlive()&&enemyTank.hashCode()!=this.hashCode()){
                        ex = enemyTank.getX();
                        ey = enemyTank.getY();
                        for(int j=getY();j<(getY()+40);j++){
                            if(enemyTank.getDirect()==0||enemyTank.getDirect()==1) {
                                if ((j >= ey && j <= ey + 40 && x+60 >= ex && x+60 <= ex + 60) || (j >= ey && j <= ey + 40 && ((x + 59) >= ex) && ((x + 59) <= ex + 60))) {
                                    return true;
                                }
                            }
                            if(enemyTank.getDirect()==0||enemyTank.getDirect()==1) {
                                if ((j >= ey && j <= ey + 60 && x+59 >= ex && x+59 <= ex + 40) || (j >= ey && j <= ey + 60 && ((x + 59) >= ex) && ((x + 59) <= ex + 40))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int j=getY();j<(getY()+40);j++) {
                    if (getHero().isAlive()) {
                        if (getHero().getDirect() == 0 || getHero().getDirect() == 1) {
                            if ((x + 60 >= hx && x + 60 <= hx + 40 && j >= hy && j <= hy + 60) || (x + 59 >= hx && (x + 59) <= hx + 40 && (j >= hy) && (j <= hy + 60))) {
                                return true;
                            }
                        }
                        if (getHero().getDirect() == 2 || getHero().getDirect() == 3) {
                            if ((x + 60 >= hx && x + 60 <= hx + 60 && j >= hy && j <= hy + 40) || (x + 59 >= hx && (x + 59) <= hx + 60 && (j >= hy) && (j <= hy + 40))) {
                                return true;
                            }
                        }
                    }
                }
                for(int i=0;i<enemyTanks.size();i++){
                    enemyTank = enemyTanks.get(i);
                    if(enemyTank.isAlive()&&enemyTank.hashCode()!=this.hashCode()){
                        ex = enemyTank.getX();
                        ey = enemyTank.getY();
                        for(int j=getY();j<(getY()+40);j++){
                            if(enemyTank.getDirect()==0||enemyTank.getDirect()==1) {
                                if ((j >= ey && j <= ey + 40 && x+60 >= ex && x+60 <= ex + 60) || (j >= ey && j <= ey + 40 && ((x + 59) >= ex) && ((x + 59) <= ex + 60))) {
                                    return true;
                                }
                            }
                            if(enemyTank.getDirect()==2||enemyTank.getDirect()==3) {
                                if ((j >= ey && j <= ey + 60 && x+60 >= ex && x+60 <= ex + 40) || (j >= ey && j <= ey + 60 && ((x + 59) >= ex) && ((x + 59) <= ex + 40))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true){
            if(!this.isAlive())
                break;
            if(getShots().size()>0){
                Shot shot = getShots().get(0);
                if(!shot.isAlive())
                    getShots().remove(shot);
            }
            //射击
            if(getShots().size()<1){
                shot();
            }
            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        moveUp();
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        moveDown();
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        moveLeft();
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        moveRight();
                    };
                    break;
            }
            int i = chooseDirection();
            setDirect(i);
        }
    }
}
