package com.hspjava.tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.LinkedList;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable, Serializable {
    public static final int width = 1000;
    public static final int length = 750;
    public static final String filePath = "D:\\TankGame\\game.dat";
    private Hero hero = null;
    private Vector<EnemyTank> enemyTanks = new Vector<>();
    private Vector<Bomb> bombs = new Vector<>();
    private static final Image image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/20190215210241592.png"));
    private static final Image image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/20190215210304713.png"));
    private static final Image image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/20190215210323305.png"));
    private static final Long serialVersionUID = 1L;
    private Boolean isAlive = true;
    public Hero getHero() {
        return hero;
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    //重新开始游戏
    public MyPanel() throws InterruptedException {
        //初始化坦克
        hero = new Hero(100,100,0,0,0,2,enemyTanks);
        EnemyTank tank1 = new EnemyTank(70,200,1,1,2,2,hero,enemyTanks);
        EnemyTank tank2 = new EnemyTank(140,10,1,1,2,2,hero,enemyTanks);
        EnemyTank tank3 = new EnemyTank(210,10,1,1,2,2,hero,enemyTanks);
        enemyTanks.add(tank1);
        enemyTanks.add(tank2);
        enemyTanks.add(tank3);
        Thread thread1 = new Thread(tank1);
        Thread thread2 = new Thread(tank2);
        Thread thread3 = new Thread(tank3);
        thread1.start();
        thread2.start();
        thread3.start();
//        将这段代码在drawImage()之前调用，MediaTracker可以确保你的图片在DRAW前被加载以备使用。
//         通过addImage方法加入一个Image并符上一个ID号，waitForAll()等待加入的所有图片被加载完毕。
        MediaTracker t = new MediaTracker(this);
        t.addImage(image1, 0);
        t.addImage(image2, 0);
        t.addImage(image3, 0);
        t.waitForAll();
    }

    //通过加载文件继续游戏
    public void LoadMyPanel() throws InterruptedException {
        MediaTracker t = new MediaTracker(this);
        t.addImage(image1, 0);
        t.addImage(image2, 0);
        t.addImage(image3, 0);
        t.waitForAll();
        //开启所有线程
        isAlive = true;
        for (int i = 0; i < hero.getShots().size(); i++) {
            Shot shot = hero.getShots().get(i);
            shot.setAlive(true);
            Thread thread = new Thread(shot);
            thread.start();
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            Vector<Shot> shots = enemyTank.getShots();
            for(int j=0;j<shots.size();j++){
                shots.get(j).setAlive(true);
                Thread thread = new Thread(shots.get(j));
                thread.start();
            }
            enemyTank.setAlive(true);
            Thread thread = new Thread(enemyTank);
            thread.start();
        }
    }

    @Override
    public void paint(Graphics g) {
        //绘制背景
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,length);
        //绘制爆炸
        for(int i=0;i<bombs.size();i++){
            Bomb bomb = bombs.get(i);
            if(bomb.getLife()<0){
                bombs.remove(i);
                i--;
                continue;
            }
            if(bomb.getLife()>=6){
                boolean b = g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
            }else if(bomb.getLife()>=3&&bomb.getLife()<=6){
                g.drawImage(image2,bomb.getX(),bomb.getY(),60,60,this);
            }else if(bomb.getLife()>=0&&bomb.getLife()<3){
                g.drawImage(image3,bomb.getX(),bomb.getY(),60,60,this);
            }
            bomb.decline();
        }
        //绘制我方坦克
        if(hero.isAlive()) {
            drawTank(hero, g);
        }
        //绘制我方子弹
        for(int i=0;i<hero.getShots().size();i++){
            if(hero.getShots().get(i).isAlive()){
                g.draw3DRect(hero.getShots().get(i).getX(),hero.getShots().get(i).getY(),2,2,false);
            }else {
                hero.getShots().remove(i);
                i--;
            }
        }
        //绘制敌方坦克
        for(int i=0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.isAlive()){
                drawTank(enemyTank,g);
                if(enemyTank.getShots().size()>0){
                    Shot shot = enemyTank.getShots().get(0);
                    g.draw3DRect(shot.getX(),shot.getY(),2,2,false);
                }
            }else{
                if(enemyTank.getShots().size()>0){
                    Shot shot = enemyTank.getShots().get(0);
                    g.setColor(Color.CYAN);
                    g.draw3DRect(shot.getX(),shot.getY(),2,2,false);
                }else {
                    enemyTanks.remove(enemyTank);
                    i--;
                }
            }
        }
    }

    public void drawTank(Tank tank,Graphics g){
        switch (tank.getType()) {
            case 0:
                g.setColor(Color.YELLOW);
                break;
            case 1:
                g.setColor(Color.CYAN);
                break;
        }
        switch (tank.getDirect()){
            case 0:
                g.fill3DRect(tank.getX(),tank.getY(),10,60,false);//wheel
                g.fill3DRect(tank.getX()+30,tank.getY(),10,60,false);//wheel
                g.fill3DRect(tank.getX()+10,tank.getY()+10,20,40,false);
                g.fillOval(tank.getX()+10,tank.getY()+20,20,20);
                g.drawLine(tank.getX()+20,tank.getY()+30,tank.getX()+20,tank.getY());
                break;
            case 1:
                g.fill3DRect(tank.getX(),tank.getY(),10,60,false);//wheel
                g.fill3DRect(tank.getX()+30,tank.getY(),10,60,false);//wheel
                g.fill3DRect(tank.getX()+10,tank.getY()+10,20,40,false);
                g.fillOval(tank.getX()+10,tank.getY()+20,20,20);
                g.drawLine(tank.getX()+20,tank.getY()+30,tank.getX()+20,tank.getY()+60);
                break;
            case 2:
                g.fill3DRect(tank.getX(),tank.getY(),60,10,false);//wheel
                g.fill3DRect(tank.getX(),tank.getY()+30,60,10,false);//wheel
                g.fill3DRect(tank.getX()+10,tank.getY()+10,40,20,false);
                g.fillOval(tank.getX()+20,tank.getY()+10,20,20);
                g.drawLine(tank.getX()+30,tank.getY()+20,tank.getX(),tank.getY()+20);
                break;
            case 3:
                g.fill3DRect(tank.getX(),tank.getY(),60,10,false);//wheel
                g.fill3DRect(tank.getX(),tank.getY()+30,60,10,false);//wheel
                g.fill3DRect(tank.getX()+10,tank.getY()+10,40,20,false);
                g.fillOval(tank.getX()+20,tank.getY()+10,20,20);
                g.drawLine(tank.getX()+30,tank.getY()+20,tank.getX()+60,tank.getY()+20);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W)
        {
            hero.setDirect(0);
            hero.moveUp();
        }
        else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(1);
            hero.moveDown();
        }
        else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(2);
            hero.moveLeft();
        }
        else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(3);
            hero.moveRight();
        }
        else if(e.getKeyCode()==KeyEvent.VK_J){
            hero.shot();
        }
        //关闭线程
        else if(e.getKeyCode()==KeyEvent.VK_Q){
            try {
                quitGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void quitGame() throws IOException {
        //关闭所有线程
        isAlive = false;
        for (int i = 0; i < hero.getShots().size(); i++) {
            Shot shot = hero.getShots().get(i);
            shot.setAlive(false);
        }
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            Vector<Shot> shots = enemyTank.getShots();
            for(int j=0;j<shots.size();j++){
                shots.get(j).setAlive(false);
            }
            enemyTank.setAlive(false);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);//默认为覆盖原文件中的内容，因此上次记录会被清除。
        //将游戏状态保存至文件中
        ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
        oos.writeObject(this);
        oos.close();
        //关闭frame
        System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {
       LinkedList<Integer> list = new LinkedList<>();
    }

    @Override
    public void run() {
        while (isAlive){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断敌方坦克此时是否被我方坦克击中
            for (int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isAlive()==false){
                    continue;
                }
                Shot shot = null;
                if(hero.getShots().size()==0){
                    break;
                }
                for (int j=0;j<hero.getShots().size();j++){
                    shot = hero.getShots().get(j);
                    if(shot.isAlive()) {
                        if (hitTank(enemyTank, shot) == true) {
                            shot.setAlive(false);
                            enemyTank.setAlive(false);
                            bombs.add(new Bomb(enemyTank.getX(), enemyTank.getY()));
                            hero.getShots().remove(shot);
                            break;
                        }
                    }
                }
            }
            //判断我方坦克此时是否被击中
            for(int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isAlive()&&enemyTank.getShots().size()>0){
                    Shot shot = enemyTank.getShots().get(0);
                    boolean b = hitTank(hero, shot);
                    if(b==true){
                        shot.setAlive(false);
                        bombs.add(new Bomb(hero.getX(),hero.getY()));
                        hero.setAlive(false);
                        break;
                    }
                }
            }
            //重绘
            repaint();
        }
    }

    public boolean hitTank(Tank tank,Shot shot){
        switch (tank.getDirect()){
            case 0:
            case 1:
                if((shot.getX()>tank.getX())&&(shot.getX()<(tank.getX()+40
                ))&&(shot.getY()>tank.getY())&&(shot.getY()<(tank.getY()+60))){
                    return true;
                }
                break;
            case 2:
            case 3:
                if((shot.getX()>tank.getX())&&(shot.getX()<(tank.getX()+60
                ))&&(shot.getY()>tank.getY())&&(shot.getY()<(tank.getY()+40))){
                    return true;
                }
                break;
        }
        return false;
    }
}
