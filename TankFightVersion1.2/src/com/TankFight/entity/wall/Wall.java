package com.TankFight.entity.wall;

import com.TankFight.entity.tank.Blast;

public abstract class Wall {

    private int x;
    private int y;

    private int width;
    private int height;

    private String path ;

    private int hp;


    public Wall(){

    }

    public Wall(int x, int y, int width, int height, String path) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.path = path;
    }


    public abstract void draw();
    public abstract void wallAttribute();

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Blast showBlast(){

        this.hp--;

        Blast blast = new Blast(this.getX(),this.getY(),this.width,this.height);

        return  blast;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
