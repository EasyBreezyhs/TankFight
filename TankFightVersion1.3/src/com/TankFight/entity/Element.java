package com.TankFight.entity;

import com.TankFight.Utils.DrawUtils;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>Bullet</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-06 10:15
 **/
public abstract class Element {

    private int x;
    private int y;

    private int width;
    private int height;

    private String path ;

    public  int hp;

    public Element(){

    }

    public Element(int x, int y, int width, int height, String path) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.path = path;
    }


    public void draw(){
        try {
            DrawUtils.draw(path, x, y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public void  getSize(){
        try {
            int[] size  = DrawUtils.getSize(this.path);
            this.width = size[0];
            this.height = size[1];
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Element(int x, int y) {
        this.x = x;
        this.y = y;
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

}
