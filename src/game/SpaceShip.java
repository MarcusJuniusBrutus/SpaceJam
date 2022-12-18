package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/*
 * Attatched to SpaceMain, contains SpaceShip coordinates, size, et velocity
 */

public class SpaceShip extends Rectangle {
	double xx, yy;
	double vx, vy;
	Color clr;
	int lives = 3;

	SpaceShip() {
		// size, width et height are properties of Rectangle class
		width = 100;
		height = 50;

		// double coordinate values to make movement more precise
		xx = SpaceMain.PANW / 2 - width / 2;
		yy = SpaceMain.PANH / 2 - height / 2;

		// integer coordinate values, also properties of Rectangle class
		x = (int) xx;
		y = (int) yy;

		// velocity in the x et y directions
		vx = 10;
		vy = 10;

		clr = new Color(100, 0, 100);
	}

	// updates coordinates based on KeyPressed
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

		// wraps spaceship around if it moves off screen
		if (xx < 0)
			xx = SpaceMain.PANW;
		if (xx > SpaceMain.PANW)
			xx = 0;
		if (yy < 0)
			yy = SpaceMain.PANH;
		if (yy > SpaceMain.PANH)
			yy = 0;

		// update location
		x = (int) xx;
		y = (int) yy;
	}
}
