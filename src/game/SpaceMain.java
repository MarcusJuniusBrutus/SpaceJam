package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/*
 * Kunala, 2022 Dec 12 - Dec 14
 */

public class SpaceMain {
	static final int PANW = 1200;
	static final int PANH = 900;

	DrawingPanel panel = new DrawingPanel();
	SpaceShip ship = new SpaceShip();

	public static void main(String[] args) {
		// using this makes animation more reliable
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SpaceMain();
			}
		});
	}

	// holds the spaceship coordinates and sizes
	private class SpaceShip extends Rectangle {
		double xx, yy;
		double vx, vy;
		Color clr = new Color(100, 0, 100);
		int width, height;

		SpaceShip() {
			xx = PANW / 5;
			yy = PANH / 2;

			x = (int) xx;
			y = (int) yy;

			width = 100;
			height = 50;

			vx = 10;
			vy = 10;
		}

		void moveShip(int key) {
			switch (key) {
			case 'W':
			case KeyEvent.VK_UP:
				yy -= vy;
				y = (int) yy;
				break;
			case 'S':
			case KeyEvent.VK_DOWN:
				yy += vy;
				y = (int) yy;
				break;
			case 'A':
			case KeyEvent.VK_LEFT:
				xx -= vx;
				x = (int) xx;
				break;
			case 'D':
			case KeyEvent.VK_RIGHT:
				xx += vx;
				x = (int) xx;
				break;
			}
			panel.repaint();
		}
	}

	SpaceMain() {
		JFrame window = new JFrame("SpaceJam");

		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	private class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setPreferredSize(new Dimension(PANW, PANH));
			this.setBackground(new Color(168, 185, 190));

			this.addKeyListener(new KL());
			this.setFocusable(true);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// horrible antialiasing code
			Graphics2D g2 = (Graphics2D) g;
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHints(rh);

			drawSpaceShip(g);
		}

		void drawSpaceShip(Graphics g) {
			g.setColor(ship.clr);
			g.fillRect(ship.x, ship.y, ship.width, ship.height);
		}
	}

	class KL implements KeyListener {
		private boolean keysDown[] = new boolean[256];

		public boolean isKeyDown(int key) {
			return keysDown[key];
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() < 256)
				keysDown[e.getKeyCode()] = true;
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
}
