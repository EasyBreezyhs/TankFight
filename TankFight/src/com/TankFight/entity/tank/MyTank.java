package com.TankFight.entity.tank;

import com.TankFight.Utils.DrawUtils;
import com.TankFight.entity.Constant;
import com.sun.javafx.scene.traversal.Direction;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>MyTank</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 14:36
 **/
public class MyTank extends Tank{



    public MyTank(int x,int y){
        super(x,y);
        this.setPath(Constant.TankUp);
        this.setSpeed(16);
        int[] size;
        try {
            size = DrawUtils.getSize(this.getPath());
            this.setWidth(size[0]);
            this.setHeight(size[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {

        try {
            DrawUtils.draw(this.getPath(),this.getX(),this.getY());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(Direction d) {
        if (d == this.d){
            switch (d){
                case UP:
                    this.setY(this.getY()-this.getSpeed());
                    break;
                case DOWN:
                    this.setY(this.getY()+this.getSpeed());
                    break;
                case LEFT:
                    this.setX(this.getX()-this.getSpeed());
                    break;
                case RIGHT:
                    this.setX(this.getX()+this.getSpeed());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + d);
            }
        }else {
            switch (d) {
                case UP:
                    this.setPath(Constant.TankUp);
                    break;
                case DOWN:
                    this.setPath(Constant.TankDown);
                    break;
                case LEFT:
                    this.setPath(Constant.TankLeft);
                    break;
                case RIGHT:
                    this.setPath(Constant.TankRight);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + d);
            }
            this.d= d;
        }

        if(this.getX()<0){
            this.setX(0);
        }else if (this.getY()<0){
            this.setY(0);
        } else if (this.getX()>=Constant.width-this.getWidth()){
            this.setX(Constant.width-this.getWidth());
        }else if (this.getY()>=Constant.HEIGHT-this.getHeight()){
            this.setY(Constant.HEIGHT-this.getHeight());
        }
    }



}
