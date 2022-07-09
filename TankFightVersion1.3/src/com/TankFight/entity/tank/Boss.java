package com.TankFight.entity.tank;

import com.TankFight.entity.Constant;
import com.TankFight.entity.Element;
import com.TankFight.entity.Tankinterface.Hitable;
import com.TankFight.entity.Tankinterface.Recyclable;

/**
 * <h3>test0628</h3>
 * <p>Boss</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-07-09 10:09
 **/
public class Boss extends Element implements Hitable, Recyclable {

    public Boss() {
    }

    public Boss(int x, int y) {
        super(x, y);
        super.setPath(Constant.BossPtah);
        super.getSize();
        super.hp = 2;
    }

    //x 是墙的x y是墙的y 还需要传入墙的宽和高
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
}