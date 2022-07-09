package com.TankFight.entity.tank;

import com.sun.javafx.scene.traversal.Direction;

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

    Direction d =Direction.UP;



    public Tank(){

    }
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public abstract void draw();

   public abstract void move(Direction d);






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



}
