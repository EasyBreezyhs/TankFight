
package com.TankFight.Utils;

import com.TankFight.entity.tank.Bullet;
import com.TankFight.entity.tank.Tank;
import com.TankFight.entity.wall.Wall;

public class CollisionUtils {
	private CollisionUtils() {

	}

	/**
	 * 判断两个矩形是否碰撞
	 * 
	 * @param x1
	 *            第一个矩形的 x坐标
	 * @param y1
	 *            第一个矩形的 y坐标
	 * @param w1
	 *            第一个矩形的 宽度
	 * @param h1
	 *            第一个矩形的 高度
	 * @param x2
	 *            第二个矩形的 x坐标
	 * @param y2
	 *            第二个矩形的 y坐标
	 * @param w2
	 *            第二个矩形的 宽度
	 * @param h2
	 *            第二个矩形的 高度
	 * @return
	 */
	public static boolean isCollisionWithRect(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		if (x1 >= x2 && x1 >= x2 + w2) {
			return false;
		} else if (x1 <= x2 && x1 + w1 <= x2) {
			return false;
		} else if (y1 >= y2 && y1 >= y2 + h2) {
			return false;
		} else if (y1 <= y2 && y1 + h1 <= y2) {
			return false;
		}
		return true;
	}


	public static boolean isCollision(Tank tank, Wall wall) {
		if (tank.getX() >= wall.getX() && tank.getX() >= wall.getX() + wall.getWidth()) {
			return false;
		} else if (tank.getX() <= wall.getX() && tank.getX() + tank.getWidth() <= wall.getX()) {
			return false;
		} else if (tank.getY() >= wall.getY() && tank.getY() >= wall.getY() + wall.getHeight()) {
			return false;
		} else if (tank.getY() <= wall.getY() && tank.getY() + tank.getHeight() <= wall.getY()) {
			return false;
		}

		return true;
	}


	public static boolean isBulletCollision(Bullet bullet, Wall wall) {
		if (bullet.getX() >= wall.getX() && bullet.getX() >= wall.getX() + wall.getWidth()) {
			return false;
		} else if (bullet.getX() <= wall.getX() && bullet.getX() + bullet.getWidth() <= wall.getX()) {
			return false;
		} else if (bullet.getY() >= wall.getY() && bullet.getY() >= wall.getY() + wall.getHeight()) {
			return false;
		} else if (bullet.getY() <= wall.getY() && bullet.getY() + bullet.getHeight() <= wall.getY()) {
			return false;
		}
		return true;
	}
	

}
