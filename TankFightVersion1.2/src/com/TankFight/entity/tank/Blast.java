package com.TankFight.entity.tank;

import com.TankFight.Utils.DrawUtils;
import com.TankFight.entity.Constant;
import com.TankFight.entity.Element;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>Blast</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-07 15:07
 **/
public class Blast extends Element {

    String[] paths = Constant.BlastPath;

    public Blast(){

    }

    public Blast(int x, int y){
        super(x,y);
        this.setPath(paths[0]);
        getSize();
    }

    public Blast(int x, int y,int width,int hight){
        this.setPath(paths[0]);
        getSize();
       this.setX(x-(this.getWidth()-width)/2);
       this.setY(y-(this.getHeight()-hight)/2);

    }

    boolean blastFlag;
    int index = 0;
    @Override
    public void draw() {

        if (index >= paths.length){
            blastFlag = true;
            index = 0;
        }
        String path = paths[index++];

        try {
            DrawUtils.draw(path,this.getX(),this.getY());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOutside() {
        return blastFlag;
    }


}
