import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {
	Timer time;
	Jumper jumper;
	ObjectRunner or;
	final static int MENU_STATE = 0;
	final static int ACTIVE_STATE = 1;
	final static int END_STATE = 2;
	int currentState = 1;
	Game() {
		time = new Timer(1000/60,this);
		jumper = new Jumper(50,GravityGuy.HEIGHT/2 - 50,50,50);
		or = new ObjectRunner(jumper);
	}
	void drawGame(Graphics g) {
		or.draw(g);
	}
	void update() {
		or.update();
		if(jumper.active == false) {
			//System.out.println("ded");
			//currentState = 2;
		}
	}
	@Override
	public void paintComponent(Graphics g){
		drawGame(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if(currentState == ACTIVE_STATE){
            update();
		}else {
			
		}
		
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
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			jumper.gravity *= -1;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
