package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Kunala, 2022 Dec 12
 */

public class SpaceMain {
	static final int PANW = 1200;
	static final int PANH = 900;
	Rectangle ship = new Rectangle(PANW / 2 - 50, PANH / 2 - 25, 100, 50);

	public static void main(String[] args) {
		new SpaceMain();
	}

	// holds the spaceship coordinates and sizes
	private class Rectangle {
		int x, y, l, h;

		Rectangle(int x, int y, int l, int h) {
			this.x = x;
			this.y = y;
			this.l = l;
			this.h = h;
		}
	}

	SpaceMain() {
		JFrame window = new JFrame("SpaceJam");
		DrawingPanel panel = new DrawingPanel();

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
			g.drawRect(ship.x, ship.y, ship.l, ship.h);
		}
	}
}
