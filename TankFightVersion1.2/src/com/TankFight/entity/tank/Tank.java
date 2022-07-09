package com.TankFight.entity.tank;

import com.TankFight.Utils.SoundUtils;
import com.TankFight.entity.Constant;
import com.TankFight.entity.Direction;
import com.TankFight.entity.wall.Wall;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>Tank</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 14:17
 **/
public abstract class Tank {
    private int x;
    private int y;

    private int width;
    private int height;

    private int speed;

    private String path ;

    private int hp;

    Direction d =Direction.UP;



    public Tank(){

    }
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;

    }


    public abstract void draw();

   public abstract void move(Direction d);


   long interval = 500;
   long lastFrieTime;
   public Bullet fire(int x, int y,int width,int height){
       Bullet bullet = null;
       long currentFireTime = System.currentTimeMillis();

       if (currentFireTime-lastFrieTime>=interval){
           try {
               SoundUtils.play(Constant.FireMusic);
           } catch (IOException e) {
               e.printStackTrace();
           }

           bullet = new Bullet(x,y,width,height,this.d);
           lastFrieTime = currentFireTime;
       }
       return bullet;
   };







    public void sleep(double second){
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getD() {
        return d;
    }

    public void setD(Direction d) {
        this.d = d;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

}
