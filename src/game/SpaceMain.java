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

public class SpaceMain implements ActionListener {
	static final int PANW = 1200;
	static final int PANH = 900;

	DrawingPanel panel = new DrawingPanel();
	SpaceShip ship = new SpaceShip();
	
	Timer t = new Timer(100, this);

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
		double vx = 5, vy = 5;
		Color clr = new Color (100, 0, 100);
		static int width = 100;
		static int height = 50;

		SpaceShip() {
			xx = PANW / 5;
			yy = PANH / 2;

			x = (int) xx;
			y = (int) yy;
		}
	}

	SpaceMain() {
		JFrame window = new JFrame("SpaceJam");
		
		t.start();

		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	private class DrawingPanel extends JPanel implements KeyListener {
		DrawingPanel() {
			this.setPreferredSize(new Dimension(PANW, PANH));
			this.setBackground(new Color(168, 185, 190));
			
			this.addKeyListener(this);
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

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				ship.yy -= 100;
			}
			panel.repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ship.xx += ship.vx;
		ship.yy += ship.vy;
		panel.repaint();
	}
}
