package com.TankFight.start;

import com.TankFight.Utils.SoundUtils;
import com.TankFight.Utils.Window;
import com.TankFight.entity.*;
import com.TankFight.entity.tank.MyTank;
import com.TankFight.entity.wall.*;
import com.sun.javafx.scene.traversal.Direction;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * <h3>test0628</h3>
 * <p>New window</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 13:46
 **/
public class Mywindow extends Window {

    boolean flag;
    MyTank tank1;
    Random r = new Random();
    Wall w1;
    ArrayList<Wall> wallList = new ArrayList<>();
//    ArrayList<Wall> wall=new ArrayList<Wall>();

    public Mywindow(String title, int width, int height, int fps) {
        super(title, width, height, fps);
    }

    @Override
    protected void onCreate() {

        int row = r.nextInt(Constant.WallQuantityRow);
        int col = r.nextInt(Constant.WallQuantityCol);
        tank1 = new MyTank(row*64,col*64);

        for (int i=0;i<Constant.AllWall;i++){
             row = r.nextInt(Constant.WallQuantityRow);
             col = r.nextInt(Constant.WallQuantityCol);
             int type = r.nextInt(Constant.WallType);
             if (tank1.getX()==row*64&&tank1.getY()==col*64){
                 continue;
             }
             switch (type){
                 case 0:
                     w1 = new BrickWall(row*64, col*64);
                     break;
                 case 1:
                     w1 = new Water(row*64, col*64);

                     break;
                 case 2:
                     w1 = new Steel(row*64, col*64);
                     break;
                 case 3:
                     w1 = new Grass(row*64, col*64);
                     break;
                 default:
             }

             wallList.add(w1);


        }



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
        tank1.draw();

        for (int i=0;i< wallList.size();i++) {
           if (wallList.get(i)!=null){
               Wall w02 = wallList.get(i);
               w02.draw();
           }
        }


    }
}
