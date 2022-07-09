package com.TankFight.start;

import com.TankFight.Utils.*;
import com.TankFight.entity.*;
import com.TankFight.entity.Tankinterface.Collidable;
import com.TankFight.entity.Tankinterface.Hitable;
import com.TankFight.entity.Tankinterface.Recyclable;
import com.TankFight.entity.tank.*;
import com.TankFight.entity.wall.*;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.TankFight.Utils.CreateMap.randomPrimCreateM;

/**
 * <h3>test0628</h3>
 * <p>New window</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 13:46
 **/
public class Mywindow extends Window {

    public Mywindow(String title, int width, int height, int fps) {
        super(title, width, height, fps);
    }



    boolean flag;

    Element e;
    EnemyTank et;
    MyTank t;
    Boss b;

    Bullet myBullet;
    ArrayList<Bullet> bs=new ArrayList<Bullet>();
    CopyOnWriteArrayList<Element> es = new CopyOnWriteArrayList<Element>();

    Random r = new Random();
    Element w1;
//    ArrayList<Wall> wallList = new ArrayList<>();

//    随机迷宫
    Element[][] walls = new Element[Constant.width/64][Constant.HEIGHT/64];


    @Override
    protected void onCreate() {
        int row = r.nextInt(Constant.WallQuantityRow);
        int col = r.nextInt(Constant.WallQuantityCol);
        t = new MyTank(row*64,col*64);


        int Bossrow = r.nextInt(Constant.WallQuantityRow);
        int Bosscol = r.nextInt(Constant.WallQuantityCol);

        b = new Boss(Bossrow*64,Bosscol*65);

        int Enrow = r.nextInt(Constant.WallQuantityRow);
        int Encol = r.nextInt(Constant.WallQuantityCol);

        et = new EnemyTank(Enrow*64,Encol*65);

        es.add(t);
        es.add(et);

        int[][] maps = CreateMap.printMazeRemake(randomPrimCreateM());

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[j].length; j++) {
                if (maps[i][j]==1){

                    if ((t.getX()==i*64&&t.getY()==j*64)
                            ||(et.getX()==i*64&&et.getY()==j*64)){
                            break;
                    }
                    int type = r.nextInt(Constant.WallType);
                    switch (type){
                        case 0:
                            w1 = new BrickWall(i*64, j*64);
                            es.add(w1);
                            break;
                        case 1:
                            w1 = new Water(i*64, j*64);
                            es.add(w1);
                            break;
                        case 2:
                            w1 = new Steel(i*64, j*64);
                            es.add(w1);
                            break;
                        case 3:
                            w1 = new Grass(i*64, j*64);
                            es.add(w1);
                            break;
                        default:
                    }
                    walls[i][j] = w1;
                }

            }

        }


        es.add(b);

//全随机
//        for (int i=0;i<Constant.AllWall;i++){
//             row = r.nextInt(Constant.WallQuantityRow);
//             col = r.nextInt(Constant.WallQuantityCol);
//             int type = r.nextInt(Constant.WallType);
//
//             switch (type){
//                 case 0:
//                     w1 = new BrickWall(row*64, col*64);
//                     break;
//                 case 1:
//                     w1 = new Water(row*64, col*64);
//
//                     break;
//                 case 2:
//                     w1 = new Steel(row*64, col*64);
//                     break;
//                 case 3:
//                     w1 = new Grass(row*64, col*64);
//                     break;
//                 default:
//             }
//             wallList.add(w1);
//        }





        try {
            SoundUtils.play("TankFight/static/music/start.wav");
            System.out.println("播放音乐");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onMouseEvent(int key, int x, int y) {

    }

    @Override
    protected void onKeyEvent(int key) {



        switch (key){
            case Keyboard.KEY_W:
                t.move(Direction.UP);
                break;
            case Keyboard.KEY_S:
                t.move(Direction.DOWN);
                break;
            case Keyboard.KEY_A:
                t.move(Direction.LEFT);
                break;
            case Keyboard.KEY_D:
                t.move(Direction.RIGHT);
                break;
            case Keyboard.KEY_J:
                myBullet = t.fire();
                if (myBullet!=null){
                    es.add(myBullet);
                }
                break;
            default:
        }

//
        if (key == Keyboard.KEY_RETURN){
            System.out.println("结束游戏");
            this.stop();

        }

        if(key == Keyboard.KEY_SPACE){
            if (!flag){
                try {
                    SoundUtils.play("TankFight/static/music/start.wav",true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                flag = true;
            }else {
                SoundUtils.stop("TankFight/static/music/start.wav");
                flag = false;
            }
        }
    }

    boolean gameOverflag = false;

    @Override
    protected void onDisplayUpdate() {

        if (gameOverflag){
            try {
                DrawUtils.draw(Constant.GameOver, 300, 200);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



        for (Element e : es) {
            //遍历出来的元素一定有一个是子弹对象
            e.draw();//编译时检查父类中是否有draw方法,运行时调用的是子类的draw方法

            if (e instanceof Recyclable && e instanceof Boss) {
                Boss boss = (Boss) e;
                if (boss.isOutside()) {
                    es.clear();
                    gameOverflag = true;
                }
            }

            if (e instanceof Recyclable) {
                Recyclable r = (Recyclable) e;
                if (r.isOutside()) {
                    es.remove(r);
                }
            }

            if (e instanceof EnemyTank) {
                EnemyTank enemyTank = (EnemyTank) e;
                Bullet eb = enemyTank.fire();
                if (eb != null) {
                    es.add(eb);
                }
            }


        }

        //遍历出坦克和砖墙对象是否产生了碰撞
        for (Element e1 : es) {
            //判断当前元素是否是Tank类型的元素
            if (e1 instanceof MyTank) {
                MyTank t = (MyTank) e1;
                //遍历出集合中的每一个砖墙对象
                for (Element e2 : es) {
                    // //判断当前元素是否是CollidableSign类型的元素
                    if (e2 instanceof Collidable) {
                        if (t.isCollide(e2)) {
                            break;//结束的是离他最近的循环
                        }
                    }
                }
            }


            if (e1 instanceof EnemyTank) {
                EnemyTank t = (EnemyTank) e1;
                //遍历出集合中的每一个砖墙对象
                for (Element e2 : es) {
                    // //判断当前元素是否是CollidableSign类型的元素
                    if (e2 instanceof Collidable) {
                        if (t.isCollide(e2)) {
                            break;//结束的是离他最近的循环
                        }
                    }
                }
            }
        }


        //遍历出子弹和墙对象是否产生了碰撞
        for (Element e1 : es) {
            //判断当前元素是否是Tank类型的元素
            if (e1 instanceof Bullet) {
                Bullet b = (Bullet) e1;
                //遍历出集合中的每一个砖墙对象
                for (Element e2 : es) {
                    // //判断当前元素是否是CollidableSign类型的元素
                    if (e2 instanceof Hitable) {
                        //如果flag为true说明墙和子弹发生了碰撞
                        if (b.isCollide(e2)) {
                            es.remove(b);//移除的是子弹
//                            if (e2 instanceof BrickWall) {
                            if (e2 instanceof Hitable) {
                                es.add(((Hitable) e2).showBlast());
                                break;//结束的是离他最近的循环
                            }
                        }
                    }
                }
            }
        }




    }
}
