package com.TankFight.entity.wall;

import com.TankFight.Utils.DrawUtils;
import com.TankFight.entity.Constant;
import com.TankFight.entity.Element;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>Gress</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 16:55
 **/
public class Grass extends Element {

    public Grass(){

    }

    public Grass(int x, int y){
        super(x,y);
        this.setPath(Constant.GrassPtah);
        getSize();
    }


}
