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
 * <p>Brick</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-05 16:39
 **/
public class BrickWall extends Element implements Collidable,Hitable, Recyclable {


    public BrickWall(int x, int y){
        super(x,y);
        this.setPath(Constant.WallPtah);
        getSize();
        super.hp = 3;
    }


    public BrickWall(){

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
