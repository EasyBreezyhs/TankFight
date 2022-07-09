package com.TankFight.entity.wall;

import com.TankFight.entity.Constant;
import com.TankFight.entity.Element;
import com.TankFight.entity.Tankinterface.Collidable;

/**
 * <h3>test0628</h3>
 * <p>Water</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 16:57
 **/
public class Water extends Element implements Collidable {

    public Water(){

    }

    public Water(int x, int y){
        super(x,y);
        this.setPath(Constant.WaterPtah);
        getSize();
    }


}
