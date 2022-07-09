package com.TankFight.entity.tank;

import com.TankFight.Utils.CollisionUtils;
import com.TankFight.Utils.DrawUtils;
import com.TankFight.entity.Constant;
import com.TankFight.entity.Direction;
import com.TankFight.entity.Element;
import com.TankFight.entity.Tankinterface.Recyclable;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>Bullet</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-06 10:19
 **/
public class Bullet extends Element implements Recyclable {
    private  int BulletSpeed = 8;



    Direction d;

    public Bullet(){
        super();
    }

    public Bullet(int x,int y){
        super(x,y);
        super.setPath(Constant.Bulletup);
        getSize();
    }

    public Bullet(int x, int y, int width, int height, Direction d){
        this.d = d;
        switch (d) {
            case UP:
                this.setPath(Constant.Bulletup);
                getSize();
                this.setX(x+(width-this.getWidth())/2);
                this.setY(y-this.getHeight());

                break;
            case DOWN:
                this.setPath(Constant.BulletDown);
                getSize();

                this.setX(x+(width-this.getWidth())/2);
                this.setY(y+this.getHeight()*2);

                break;
            case LEFT:
                this.setPath(Constant.BulletLeft);
                getSize();
                this.setX(x-this.getWidth());
                this.setY(y+(height-this.getHeight())/2);

                break;
            case RIGHT:
                this.setPath(Constant.BulletRight);
                getSize();
                this.setX(x+this.getWidth()*2);
                this.setY(y+(height-this.getHeight())/2);

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + d);
        }

    }


    //子弹碰到墙壁或者出界
    @Override
    public boolean isOutside(){
        boolean flag =false;
        if (this.getX()+this.getWidth()<0||this.getY()+this.getHeight()<0
                ||this.getX()>Constant.width||this.getY()>Constant.HEIGHT){
            flag = true;
        }
        return flag;
    }

    public boolean isCollide(Element e){
        //获取子弹的宽高坐标
        int x1 = this.getX();
        int y1 = this.getY();
        int w1 = this.getWidth();
        int h1 = this.getHeight();

        //获取墙体的宽高坐标
        int x2 = e.getX();
        int y2 = e.getY();
        int w2 = e.getWidth();
        int h2 = e.getHeight();

        return CollisionUtils.isCollisionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
    }



    @Override
    public void draw() {
        super.draw();

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

    public int getBulletSpeed() {
        return BulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        BulletSpeed = bulletSpeed;
    }



}
