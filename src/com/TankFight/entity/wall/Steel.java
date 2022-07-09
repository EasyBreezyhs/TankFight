package com.TankFight.entity.wall;

import com.TankFight.Utils.SoundUtils;
import com.TankFight.entity.Constant;
import com.TankFight.entity.Element;
import com.TankFight.entity.Tankinterface.Collidable;
import com.TankFight.entity.Tankinterface.Hitable;
import com.TankFight.entity.Tankinterface.Recyclable;
import com.TankFight.entity.tank.Blast;

import java.io.IOException;

/**
 * <h3>test0628</h3>
 * <p>Steel</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 16:56
 **/
public class Steel extends Element implements Collidable, Hitable, Recyclable {
    public Steel(int x, int y){
        super(x,y);
        this.setHeight(Constant.wallHeight);
        this.setWidth(Constant.wallWidth);
        this.setPath(Constant.SteetPath);
        super.hp = 6;
    }

    public Steel(){

    }


    @Override
    public Blast showBlast(){

        this.hp--;

        Blast blast = new Blast(this.getX(),this.getY(), this.getWidth(), this.getHeight());
        try {
            SoundUtils.play(Constant.BlastMusic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  blast;
    }


    @Override
    public boolean isOutside() {
        return hp<=0;
    }
}
