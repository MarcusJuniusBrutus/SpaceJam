package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/*
Attatched to SpaceMain, contains laser info 
*/

public class Laser extends Rectangle {
	double xx, yy;
	double vx, vy;
	Color clr;

	Laser() {
		width = 50;
		height = 10;

		xx = SpaceMain.ship.xx + SpaceMain.ship.width + 50;
		yy = SpaceMain.ship.yy + SpaceMain.ship.height / 2;

		x = (int) xx;
		y = (int) yy;

		vx = 10;
		vy = 0;

		clr = new Color(0, 0, 100);
	}

	void move() {
		xx += vx;
		yy += vy;

		// update location
		x = (int) xx;
		y = (int) yy;
	}
}
