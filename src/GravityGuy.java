import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;


public class GravityGuy {
	JFrame window;
	Game game;
	final static int WIDTH = 800;
	final static int HEIGHT = 500;
	GravityGuy() {
		game = new Game();
		window = new JFrame();
	}
	public static void main(String[] args) {
		GravityGuy runner = new GravityGuy();
		runner.setup();
	}
	void setup() {
		window.add(game);
		window.addKeyListener(game);
		window.setVisible(true);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setSize(new Dimension(WIDTH,HEIGHT));
		game.start();
	}
}
