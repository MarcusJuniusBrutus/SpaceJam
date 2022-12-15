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
		width = 10;
		height = 10;

		xx = SpaceMain.ship.xx + SpaceMain.ship.width + 50;
		yy = SpaceMain.ship.yy + SpaceMain.ship.height / 2;

		x = (int) xx;
		y = (int) yy;

		vx = 10;
		vy = 10;

		clr = new Color(0, 0, 100);
	}
	
	void move(int key) {
		switch (key) {
		case 'W':
		case KeyEvent.VK_UP:
			yy -= vy;
			break;
		case 'S':
		case KeyEvent.VK_DOWN:
			yy += vy;
			break;
		case 'A':
		case KeyEvent.VK_LEFT:
			xx -= vx;
			break;
		case 'D':
		case KeyEvent.VK_RIGHT:
			xx += vx;
			break;
		}

		// update location
		x = (int) xx;
		y = (int) yy;
	}
}
