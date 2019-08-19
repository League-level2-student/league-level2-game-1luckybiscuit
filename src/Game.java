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
	final static boolean ACTIVE_STATE = true;
	boolean currentState = true;
	Game() {
		time = new Timer(1000/60,this);
		jumper = new Jumper(40,HEIGHT+40,40,40);
		or = new ObjectRunner(jumper);
	}
	void drawGame(Graphics g) {
		or.draw(g);
		or.manageBlocks();
		or.collide();
	}
	void update() {
		or.update();
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
