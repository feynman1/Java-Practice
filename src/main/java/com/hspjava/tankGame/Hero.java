package com.hspjava.tankGame;

import java.io.Serializable;
import java.util.Vector;

public class Hero extends Tank implements Serializable {
    public Hero(int x, int y, int direct, int type, int color, int speed, Vector<EnemyTank> enemyTanks) {
        super(x, y, direct, color, type, speed,null,enemyTanks);
    }
    public boolean isOverlap(int direction){
        Vector<EnemyTank> enemyTanks = getEnemyTanks();
        EnemyTank enemyTank = null;
        int ex = 0,ey = 0,y = getY(),x = getX();
        switch (direction){
            case 0:
                for(int i=0;i<enemyTanks.size();i++){
                    enemyTank = enemyTanks.get(i);
                    if(enemyTank.isAlive()){
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
                for(int i=0;i<enemyTanks.size();i++){
                    enemyTank = enemyTanks.get(i);
                    if(enemyTank.isAlive()){
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
                for(int i=0;i<enemyTanks.size();i++){
                    enemyTank = enemyTanks.get(i);
                    if(enemyTank.isAlive()){
                        ex = enemyTank.getX();
                        ey = enemyTank.getY();
                        for(int j=getY();j<(getY()+40);j++){
                            if(enemyTank.getDirect()==0||enemyTank.getDirect()==1) {
                                if ((j >= ey && j <= (ey + 60) && x >= ex && x <= (ex + 40)) || (j >= ey && j <= (ey + 60) && ((x + 1) >= ex) && ((x + 1) <= (ex + 40)))) {
                                    return true;
                                }
                            }
                            if(enemyTank.getDirect()==2||enemyTank.getDirect()==3) {
                                if ((j >= ey && j <= (ey + 40) && x >= ex && x <= (ex + 60)) || (j >= ey && j <= (ey + 40) && ((x + 1) >= ex) && ((x + 1) <= (ex + 60)))) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i=0;i<enemyTanks.size();i++){
                    enemyTank = enemyTanks.get(i);
                    if(enemyTank.isAlive()){
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
}
