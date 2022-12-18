package game;

import java.awt.Color;

/*
Attatched to SpaceShip, allows for enemy ships
*/

public class Enemy extends SpaceShip {
	int healthPoints;

	Enemy() {
		width = 100;
		height = 100;

		xx = SpaceMain.PANW / 5 * 4;
		yy = SpaceMain.PANH / 2;

		x = (int) xx;
		y = (int) yy;

		vx = 0;
		vy = 10;

		clr = new Color(100, 0, 0);
		
		//how many bullets it takes to kill enemy
		healthPoints = 3;
	}

	void move() {
		yy -= vy;
		y = (int) yy;

		//makes enemy ship go up and down
		if (yy < 0 || yy > SpaceMain.PANH - height)
			vy *= -1;
	}
}
