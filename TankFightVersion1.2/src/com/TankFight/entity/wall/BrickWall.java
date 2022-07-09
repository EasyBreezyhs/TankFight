package com.TankFight.entity.wall;

import com.TankFight.Utils.DrawUtils;
import com.TankFight.entity.Constant;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>Brick</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 16:39
 **/
public class BrickWall extends Wall{


    public BrickWall(int x, int y){
        super(x,y);
        this.setHeight(Constant.wallHeight);
        this.setWidth(Constant.wallWidth);
        this.setPath(Constant.WallPtah);
        super.setHp(3);
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
    public void wallAttribute() {

    }
}
