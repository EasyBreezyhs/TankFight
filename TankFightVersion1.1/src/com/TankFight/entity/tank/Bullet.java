package com.TankFight.entity.tank;

import com.TankFight.Utils.DrawUtils;
import com.TankFight.entity.Constant;
import com.TankFight.entity.Direction;
import com.TankFight.entity.Element;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>Bullet</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-06 10:19
 **/
public class Bullet extends Element {
    private static final int BulletSpeed = 8;
    Direction d;

    public Bullet(){

    }

    public Bullet(int x, int y, int width, int height, Direction d){
        this.d = d;
        this.setHeight(Constant.BulletHeight);
        this.setWidth(Constant.BulletWidth);
        switch (d) {
            case UP:
                this.setX(x+(width-this.getWidth())/2);
                this.setY(y-this.getHeight());
                this.setPath(Constant.Bulletup);
                break;
            case DOWN:
                this.setX(x+(width-this.getWidth())/2);
                this.setY(y+this.getHeight()*2);
                this.setPath(Constant.BulletDown);
                break;
            case LEFT:
                this.setX(x-this.getHeight());
                this.setY(y+(height-this.getHeight())/2);
                this.setPath(Constant.BulletLeft);
                break;
            case RIGHT:
                this.setX(x+this.getHeight()*2);
                this.setY(y+(height-this.getHeight())/2);
                this.setPath(Constant.BulletRight);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + d);
        }

    }


    public boolean isOutside(){
        boolean flag =false;
        if (this.getX()+this.getWidth()<0||this.getY()+this.getHeight()<0
                ||this.getX()>Constant.width||this.getY()>Constant.HEIGHT){
            flag = true;
        }
        return flag;
    }



    @Override
    public void draw() {
        try {
            DrawUtils.draw(this.getPath(),this.getX(),this.getY());
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (this.d){
            case UP:
                this.setY(this.getY()-BulletSpeed);
                break;
            case DOWN:
                this.setY(this.getY()+BulletSpeed);
                break;
            case LEFT:
                this.setX(this.getX()-BulletSpeed);
                break;
            case RIGHT:
                this.setX(this.getX()+BulletSpeed);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + d);
        }


    }




}
