import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {
	Timer time;
	GameObject jumper;
	Game() {
		time = new Timer(1000/60,this);
		jumper = new GameObject(0,HEIGHT-100,100,100);
	}
	void draw(Graphics g) {
		g.fillRect(jumper.x, jumper.y, jumper.width,jumper.height);
	}
	void update() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("does this work");
	}
	void start() {
		time.start();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
