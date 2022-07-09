package com.TankFight.start;

import com.TankFight.Utils.CreateMap;
import com.TankFight.Utils.SoundUtils;
import com.TankFight.Utils.Window;
import com.TankFight.entity.*;
import com.TankFight.entity.tank.Bullet;
import com.TankFight.entity.tank.MyTank;
import com.TankFight.entity.wall.*;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static com.TankFight.Utils.CreateMap.randomPrimCreateM;

/**
 * <h3>test0628</h3>
 * <p>New window</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 13:46
 **/
public class Mywindow extends Window {

    boolean flag;
    boolean bulletFlag;
    MyTank tank1;

    Bullet myBullet;
    ArrayList<Bullet> bs=new ArrayList<Bullet>();

    Random r = new Random();
    Wall w1;

    ArrayList<Wall> wallList = new ArrayList<>();

//    Ëæ»úÃÔ¹¬
    Wall[][] walls = new Wall[Constant.width/64][Constant.HEIGHT/64];


    public Mywindow(String title, int width, int height, int fps) {
        super(title, width, height, fps);
    }

    @Override
    protected void onCreate() {
        int row = r.nextInt(Constant.WallQuantityRow);
        int col = r.nextInt(Constant.WallQuantityCol);
        tank1 = new MyTank(row*64,col*64);

        int[][] maps = CreateMap.printMazeRemake(randomPrimCreateM());

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[j].length; j++) {
                if (maps[i][j]==1){
                    if (tank1.getX()==i*64&&tank1.getY()==j*64){
                            continue;
                    }
                    int type = r.nextInt(Constant.WallType);
                    switch (type){
                        case 0:
                            w1 = new BrickWall(i*64, j*64);
                            break;
                        case 1:
                            w1 = new Water(i*64, j*64);

                            break;
                        case 2:
                            w1 = new Steel(i*64, j*64);
                            break;
                        case 3:
                            w1 = new Grass(i*64, j*64);
                            break;
                        default:
                    }
                    walls[i][j] = w1;
                }

            }

        }


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
            System.out.println("²¥·ÅÒôÀÖ");
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
                tank1.move(Direction.UP);
                break;
            case Keyboard.KEY_S:
                tank1.move(Direction.DOWN);
                break;
            case Keyboard.KEY_A:
                tank1.move(Direction.LEFT);
                break;
            case Keyboard.KEY_D:
                tank1.move(Direction.RIGHT);
                break;
            case Keyboard.KEY_J:
                myBullet = tank1.fire(tank1.getX(),tank1.getY(),tank1.getWidth(),tank1.getHeight());
                if (myBullet!=null){
                    bs.add(myBullet);
                }
                break;
            default:
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

    @Override
    protected void onDisplayUpdate() {


//
//        for (int i=0;i< wallList.size();i++) {
//           if (wallList.get(i)!=null){
//               Wall w02 = wallList.get(i);
//               w02.draw();
//           }
//        }

        for (int i = 0; i < walls.length; i++) {
            for (int j = 0; j < walls[j].length; j++) {
                if (walls[i][j]!=null){
                    walls[i][j].draw();
                }
            }
        }

        tank1.draw();



        for (int i = 0; i <bs.size() ; i++) {
            Bullet b01 = bs.get(i);
            b01.draw();
            if (b01.isOutside()){
                bs.remove(b01);
            }

        }




    }
}
