import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {
	Timer time;
	Jumper jumper;
	ObjectRunner or;
	final static int MENU_STATE = 0;
	final static int ACTIVE_STATE = 1;
	final static int END_STATE = 2;
	int currentState = 0;
	Game() {
		time = new Timer(1000/60,this);
		jumper = new Jumper(300,GravityGuy.HEIGHT/2 - 50,50,50);
		or = new ObjectRunner(jumper);
	}
	void drawMenu(Graphics sad) {
		sad.setColor(Color.BLUE);
		//sad.fillRect(0, 0, GravityGuy.WIDTH, GravityGuy.HEIGHT);
		sad.setFont(new Font("Arial", Font.BOLD, 45));
		sad.drawString("GRAVITY LAD", 25, 300);
		sad.setFont(new Font("Arial", Font.PLAIN, 20));
		sad.drawString("'A completely original game by Lukas Nepomuceno'", 150, 400);
		sad.drawString("Press Q for mission debrief", 200, 500);
		sad.drawString("Press ENTER to start", 250, 600);
	}
	void drawGame(Graphics g) {
		or.draw(g);
		g.drawString(Integer.toString(or.intervalCount), GravityGuy.WIDTH - 50, 50);
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
		if(currentState == MENU_STATE){
	           drawMenu(g);
			}else if(currentState == ACTIVE_STATE){
	           drawGame(g);
			}else if(currentState == END_STATE){
	           //drawEnd(g);
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if(currentState == ACTIVE_STATE){
            update();
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
		if(e.getKeyCode() == KeyEvent.VK_Q && currentState == MENU_STATE) {
			JOptionPane.showMessageDialog(null, "GRAVITY LAD is stuck in an infinitel long passage being sucked by a black hole!\nPress SPACE to help him avoid obstacles.");
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && currentState == MENU_STATE) {
			currentState = ACTIVE_STATE;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
