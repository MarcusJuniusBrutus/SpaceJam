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
import java.util.ArrayList;

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
	static final Color BACK = new Color(168, 185, 190);

	public static ArrayList<Laser> laserList = new ArrayList<Laser>();
	public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	DrawingPanel panel = new DrawingPanel();
	static SpaceShip ship = new SpaceShip();
	Timer maine = new Timer(10, this);
	BetterKeyListener bKL = new BetterKeyListener();

	public static void main(String[] args) {
		// using this makes animation more reliable
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SpaceMain();
			}
		});
	}

	SpaceMain() {
		JFrame window = new JFrame("SpaceJam");

		enemyList.add(new Enemy());

		maine.start();

		window.add(panel);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addKeyListener(bKL);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	private class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setPreferredSize(new Dimension(PANW, PANH));
			this.setBackground(BACK);
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
			drawLasers(g);
			drawEnemyShips(g);
		}

		void drawSpaceShip(Graphics g) {
			g.setColor(ship.clr);
			g.fillRect(ship.x, ship.y, ship.width, ship.height);
		}

		void drawLasers(Graphics g) {
			for (int x = 0; x < laserList.size(); x++) {
				g.setColor(laserList.get(x).clr);
				g.fillRect(laserList.get(x).x, laserList.get(x).y, laserList.get(x).width, laserList.get(x).height);
			}
		}

		void drawEnemyShips(Graphics g) {
			for (int x = 0; x < enemyList.size(); x++) {
				g.setColor(enemyList.get(x).clr);
				g.fillRect(enemyList.get(x).x, enemyList.get(x).y, enemyList.get(x).width, enemyList.get(x).height);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// moves ship depending on which key pressed
		if (bKL.isKeyDown('A') || bKL.isKeyDown(37))
			ship.move('A');
		if (bKL.isKeyDown('W') || bKL.isKeyDown(38))
			ship.move('W');
		if (bKL.isKeyDown('D') || bKL.isKeyDown(39))
			ship.move('D');
		if (bKL.isKeyDown('S') || bKL.isKeyDown(40))
			ship.move('S');

		for (int x = 0; x < laserList.size(); x++) {
			laserList.get(x).move();
		}
		for (int x = 0; x < enemyList.size(); x++) {
			enemyList.get(x).move();
		}

		panel.repaint();
	}
}
