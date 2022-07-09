package com.TankFight.entity.tank;

import com.TankFight.Utils.CollisionUtils;
import com.TankFight.Utils.SoundUtils;
import com.TankFight.entity.Constant;
import com.TankFight.entity.Direction;
import com.TankFight.entity.Element;
import com.TankFight.entity.Tankinterface.Collidable;
import com.TankFight.entity.Tankinterface.Hitable;
import com.TankFight.entity.Tankinterface.Recyclable;

import java.io.IOException;
import java.util.Random;

/**
 * <h3>test0628</h3>
 * <p> EnemyTank</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-09 14:39
 **/
public class EnemyTank extends Element implements Hitable, Recyclable, Collidable {

    int EnemyTankSpeed = Constant.EnemyTankSpeed;

    Direction d = Direction.RIGHT;
    Direction unMoved;

    int surplus = 0;

    public EnemyTank(){

    }


    public EnemyTank(int x,int y){
        super(x,y);
        super.setPath(Constant.EnemyTankRight);
        getSize();
        super.hp = 2;
    }



//
//    @Override
//    public void draw() {
//        super.draw();
//    }



    long interval = 500;
    long lastFireTime;

    public Bullet fire() {
        Bullet bullet = null;
        //获取当前发射子弹的开火时间
        long currentFireTime = System.currentTimeMillis();

        if (currentFireTime - lastFireTime >= interval) {
            try {
                SoundUtils.play(Constant.FireMusic);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bullet = new Bullet(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.d);
            //重新记录最后一次发射子弹的时间
            lastFireTime = currentFireTime;
        }
        return bullet;
    }




    public boolean isCollide(Element e) {

        //如果遍历出来的元素就是坦克自己
        if (e == this) {
            return false;
        }

        //获取坦克的宽高坐标
        int x1 = this.getX();
        int y1 = this.getY();
        int w1 = this.getWidth();
        int h1 = this.getHeight();
        //第一种情况 是用坦克即时的坐标参数去和墙的坐标参数进行判断 如果墙体和坦克放生碰撞 坦克已经绘制完毕 事实上是嵌入在墙体中的状态
        //实际上 我们可以让坦克的坐标预加一些值 用加完之后的坐标值去和墙体进行比较

        switch (d) {
            case UP:
                y1 -= EnemyTankSpeed;
                break;

            case DOWN:
                y1 += EnemyTankSpeed;
                break;

            case LEFT:
                x1 -= EnemyTankSpeed;
                break;

            case RIGHT:
                x1 += EnemyTankSpeed;
                break;
            default:

        }


        //获取墙体的宽高坐标
        int x2 = e.getX();
        int y2 = e.getY();
        int w2 = e.getWidth();
        int h2 = e.getHeight();

//        System.out.println(x1 + ":" + y1 + ":" + w1 + ":" + h1);
//        System.out.println(x2 + ":" + y2 + ":" + w2 + ":" + h2);
        boolean flag = CollisionUtils.isCollisionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);


        if (flag) {
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
        } else {
            unMoved = null;
        }

        return flag;
    }


    @Override
    public Blast showBlast() {
        hp--;

        Blast blast = new Blast(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        return blast;
    }

    @Override
    public boolean isOutside() {

        return hp <= 0;
    }


    public void move(Direction d) {
        if (unMoved != null && unMoved == d) {
            //在坦克移动的代码被中断之前 让坦克走完剩下留白距离
            switch (d) {
                case UP:
                    this.setY(this.getY()-surplus);
                    break;
                case DOWN:
                    this.setY(this.getY()+surplus);
                    break;
                case LEFT:
                    this.setX(this.getX()-surplus);
                    break;
                case RIGHT:
                    this.setX(this.getX()+surplus);
                    break;
                default:

            }
            return;
        }


        //如果传递进来的方向和当前坦克方向相同 纯粹的就是改变坐标
        if (d == this.d) {
            switch (d) {
                case UP:
                    this.setY(this.getY()-EnemyTankSpeed);
                    break;

                case DOWN:
                    this.setY(this.getY()+EnemyTankSpeed);
                    break;

                case LEFT:
                    this.setX(this.getX()-EnemyTankSpeed);
                    break;

                case RIGHT:
                    this.setX(this.getX()-EnemyTankSpeed);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + d);
            }
        } else {
            //如果传递进来的方向和当前坦克方向不一致 那么需要先改变绘制的图片的方向 再修改坐标
            switch (d) {
                case UP:
                    this.setPath(Constant.EnemyTankUp);
                    break;

                case DOWN:
                    this.setPath(Constant.EnemyTankDown);
                    break;

                case LEFT:
                    this.setPath(Constant.EnemyTankLeft);
                    break;

                case RIGHT:
                    this.setPath(Constant.EnemyTankRight);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + d);
            }

            //记录当前坦克的真正指向
            this.d = d;
        }


        //如果向左移动x的坐标值变成负数说明越界 就把x的值修正为起点值0
        if (this.getX() < 0) {
            this.setX(0);
        }

        //如果向上移动y的坐标值变成负数说明越界 就把y的值修正为起点值0
        if (this.getY() < 0) {
            this.setY(0);
        }

        //如果向右移动x的坐标值大于屏幕宽度-坦克宽度 就把x的值修正为(屏幕宽度-坦克宽度)
        if (this.getX() > Constant.width - this.getWidth()) {
            this.setX(Constant.width - this.getWidth()) ;
        }
        //如果向下移动y的坐标值大于屏幕高度-坦克高度 就把y的值修正为(屏幕高度-坦克高度)
        if (this.getY() > Constant.HEIGHT - this.getHeight()) {
            this.setY(Constant.HEIGHT - this.getHeight());
        }
    }


    Random r = new Random();


    long e_interval = 1500;
    long e_lastRandomTime;

    @Override
    public void draw() {
        super.draw();
        //获取当前系统时间
        long currentRandomTime = System.currentTimeMillis();
        //判断此次时间-上一次生成随机数的时间是否满足间隔
        if (currentRandomTime - e_lastRandomTime >= e_interval) {
            int num = r.nextInt(4) + 1;
            switch (num) {
                case 1:
                    this.move(Direction.UP);
                    break;
                case 2:
                    this.move(Direction.DOWN);
                    break;
                case 3:
                    this.move(Direction.LEFT);
                    break;
                case 4:
                    this.move(Direction.RIGHT);
                    break;
                default:

            }
            e_lastRandomTime = currentRandomTime;
        } else {
            this.move(d);
        }

    }



}
