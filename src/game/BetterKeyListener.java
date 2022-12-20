package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Attached to SpaceMain, allows for diagonal movement
 */

public class BetterKeyListener implements KeyListener {
	private boolean keysDown[] = new boolean[256];

	public boolean isKeyDown(int key) {
		return keysDown[key];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 256)
			keysDown[e.getKeyCode()] = true;

		// if space pressed, create laser
		if (keysDown[KeyEvent.VK_SPACE]) {
			if ((SpaceMain.t - SpaceMain.prevT) > 10) {
				SpaceMain.laserList.add(new Laser());
				SpaceMain.prevT = SpaceMain.t;
			}
		}

		// if more than 12 lasers shot, remove first laser in list
		if (SpaceMain.laserList.size() > 5) {
			SpaceMain.laserList.remove(0);
		}

		// removes laser if it goes off screen
		for (int x = 0; x < SpaceMain.laserList.size(); x++) {
			if (SpaceMain.laserList.get(x).x < 0 || SpaceMain.laserList.get(x).x > SpaceMain.PANW
					|| SpaceMain.laserList.get(x).y < 0 || SpaceMain.laserList.get(x).y > SpaceMain.PANH) {
				SpaceMain.laserList.remove(x);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 256)
			keysDown[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
