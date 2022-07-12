package com.TankFight.entity.tank;

import com.TankFight.Utils.CollisionUtils;
import com.TankFight.Utils.DrawUtils;
import com.TankFight.Utils.SoundUtils;
import com.TankFight.entity.Constant;
import com.TankFight.entity.Direction;
import com.TankFight.entity.Element;
import com.TankFight.entity.Tankinterface.Collidable;


import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>MyTank</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 14:36
 **/
public class MyTank extends Element implements Collidable {

    int surplus = 0;
    private int speed;

    Direction d =Direction.UP;



    Direction unMoved;

    public MyTank(){

    }

    public MyTank(int x,int y){
        super(x,y);
        this.setPath(Constant.TankUp);
        this.setSpeed(Constant.TankSpeed);
        getSize();
        super.hp = 1;
    }



    long interval = 500;
    long lastFrieTime;
    public Bullet fire(){
        Bullet bullet = null;
        long currentFireTime = System.currentTimeMillis();

        if (currentFireTime-lastFrieTime>=interval){
            try {
                SoundUtils.play(Constant.FireMusic);
            } catch (IOException e) {
                e.printStackTrace();
            }

            bullet = new Bullet(this.getX(), this.getY(), this.getWidth(), this.getHeight(),this.d);
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




    public boolean isCollide(Element e){
        if (e == this) {
            return false;
        }

        int x1 = this.getX();
        int y1 = this.getY();
        int w1 = this.getWidth();
        int h1 = this.getHeight();

        switch (this.d) {
            case UP:
                y1 -= this.getSpeed();

                break;
            case DOWN:
                y1 += this.getSpeed();

                break;
            case LEFT:
                x1 -= this.getSpeed();

                break;
            case RIGHT:
                x1 += this.getSpeed();

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + d);
        }

        int x2 = e.getX();
        int y2 = e.getY();
        int w2 = e.getWidth();
        int h2 = e.getHeight();

        boolean flag =CollisionUtils.isCollisionWithRect(x1,y1,w1,h1,x2,y2,w2,h2);

        if (flag){
            unMoved = d;

            //在坦克不能移动之后 计算出留白距离
            switch (d) {
                case UP:
                    surplus = super.getY() - y2 - h2;
                    break;
                case DOWN:
                    surplus = y2 - super.getY() - h1;
                    break;
                case LEFT:
                    surplus = super.getX() - x2 - w2;
                    break;
                case RIGHT:
                    surplus = x2 - super.getX() - w1;
                    break;
                default:
            }

        }else{
            unMoved = null;
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
    }

    public void move(Direction d) {

        if (unMoved != null&&unMoved==d){
            //在坦克移动的代码被中断之前 让坦克走完剩下留白距离
//            switch (d){
//                case UP:
//                    this.setY(this.getY()-surplus);
//                    break;
//                case DOWN:
//                    this.setY(this.getY()+surplus);
//                    break;
//                case LEFT:
//                    this.setX(this.getX()-surplus);
//                    break;
//                case RIGHT:
//                    this.setX(this.getX()+surplus);
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + d);
//            }
            return;
        }

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
        }

        else {
            this.sleep(0.07);
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
}
