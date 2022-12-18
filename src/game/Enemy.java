package game;

import java.awt.Color;

/*
Attatched to SpaceShip, allows for enemy ships
*/

public class Enemy extends SpaceShip {
	int health;

	Enemy() {
		width = 100;
		height = 100;

		xx = SpaceMain.PANW;
		yy = (Math.random() * (SpaceMain.PANH - height));

		x = (int) xx;
		y = (int) yy;

		vx = SpaceMain.speed;
		vy = 0;

		clr = new Color(100, 0, 0);

		// how many bullets it takes to kill enemy
		health = 3;
	}

	void move() {
		xx -= vx;
		x = (int) xx;

		// if enemy reaches the leftmost side, take off a life and remove enemy ship
		if (x <= 0) {
			SpaceMain.ship.lives--;
			SpaceMain.enemyList.remove(this);
		}
	}
}
