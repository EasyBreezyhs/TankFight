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
        //��ȡ��ǰ�����ӵ��Ŀ���ʱ��
        long currentFireTime = System.currentTimeMillis();

        if (currentFireTime - lastFireTime >= interval) {
            try {
                SoundUtils.play(Constant.FireMusic);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bullet = new Bullet(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.d);
            //���¼�¼���һ�η����ӵ���ʱ��
            lastFireTime = currentFireTime;
        }
        return bullet;
    }




    public boolean isCollide(Element e) {

        //�������������Ԫ�ؾ���̹���Լ�
        if (e == this) {
            return false;
        }

        //��ȡ̹�˵Ŀ������
        int x1 = this.getX();
        int y1 = this.getY();
        int w1 = this.getWidth();
        int h1 = this.getHeight();
        //��һ����� ����̹�˼�ʱ���������ȥ��ǽ��������������ж� ���ǽ���̹�˷�����ײ ̹���Ѿ�������� ��ʵ����Ƕ����ǽ���е�״̬
        //ʵ���� ���ǿ�����̹�˵�����Ԥ��һЩֵ �ü���֮�������ֵȥ��ǽ����бȽ�

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


        //��ȡǽ��Ŀ������
        int x2 = e.getX();
        int y2 = e.getY();
        int w2 = e.getWidth();
        int h2 = e.getHeight();

//        System.out.println(x1 + ":" + y1 + ":" + w1 + ":" + h1);
//        System.out.println(x2 + ":" + y2 + ":" + w2 + ":" + h2);
        boolean flag = CollisionUtils.isCollisionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);


        if (flag) {
            unMoved = d;
            //��̹�˲����ƶ�֮�� ��������׾���
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
            //��̹���ƶ��Ĵ��뱻�ж�֮ǰ ��̹������ʣ�����׾���
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


        //������ݽ����ķ���͵�ǰ̹�˷�����ͬ ����ľ��Ǹı�����
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
            //������ݽ����ķ���͵�ǰ̹�˷���һ�� ��ô��Ҫ�ȸı���Ƶ�ͼƬ�ķ��� ���޸�����
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

            //��¼��ǰ̹�˵�����ָ��
            this.d = d;
        }


        //��������ƶ�x������ֵ��ɸ���˵��Խ�� �Ͱ�x��ֵ����Ϊ���ֵ0
        if (this.getX() < 0) {
            this.setX(0);
        }

        //��������ƶ�y������ֵ��ɸ���˵��Խ�� �Ͱ�y��ֵ����Ϊ���ֵ0
        if (this.getY() < 0) {
            this.setY(0);
        }

        //��������ƶ�x������ֵ������Ļ���-̹�˿�� �Ͱ�x��ֵ����Ϊ(��Ļ���-̹�˿��)
        if (this.getX() > Constant.width - this.getWidth()) {
            this.setX(Constant.width - this.getWidth()) ;
        }
        //��������ƶ�y������ֵ������Ļ�߶�-̹�˸߶� �Ͱ�y��ֵ����Ϊ(��Ļ�߶�-̹�˸߶�)
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
        //��ȡ��ǰϵͳʱ��
        long currentRandomTime = System.currentTimeMillis();
        //�жϴ˴�ʱ��-��һ�������������ʱ���Ƿ�������
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
