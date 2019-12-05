import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {
	public static BufferedImage gradient;
	Timer time;
	Jumper jumper;
	ObjectRunner or;
	final static int MENU_STATE = 0;
	final static int ACTIVE_STATE = 1;
	final static int END_STATE = 2;
	int currentState = 0;
	Game() {
		time = new Timer(1000/60,this);
		jumper = new Jumper(0,200,50,50, "jumper");
		or = new ObjectRunner(jumper);
		try {
            gradient = ImageIO.read(this.getClass().getResourceAsStream("gradient.jpg"));
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
	}
	void drawMenu(Graphics sad) {
		sad.drawImage(Game.gradient, 0, 0, GravityGuy.WIDTH+400, GravityGuy.HEIGHT, null);
		or.draw(sad);
		sad.setColor(Color.CYAN);
		sad.setFont(new Font("Arial", Font.BOLD, 100));
		sad.drawString("GRAVITY LAD", 50, 250);
		sad.setFont(new Font("Arial", Font.PLAIN, 25));
		sad.drawString("A \"completely original\" game by Lukas Nepomuceno", 100, 350);
		sad.drawString("Press Q for mission debrief", 240, 450);
		sad.drawString("Press ENTER to start", 270, 550);
	}
	void drawGame(Graphics g) {
		g.drawImage(Game.gradient, 0, 0, GravityGuy.WIDTH+400, GravityGuy.HEIGHT, null);
		or.draw(g);
		g.setFont(new Font("Arial", Font.BOLD, 45));
		g.setColor(Color.GREEN);
		g.drawString(Integer.toString(or.score), GravityGuy.WIDTH - 100, 40);
	}
	void update() {
		if(currentState == MENU_STATE) {
			or.update(false);
		}else {
			or.update(true);
		}
		if(jumper.active == false) {
			System.out.println("ded");
			currentState = END_STATE;
		}
	}
	void drawEnd(Graphics sad) {
		sad.setColor(Color.BLACK);
		sad.fillRect(0, 0, GravityGuy.WIDTH, GravityGuy.HEIGHT);
		sad.setColor(Color.ORANGE);
		sad.setFont(new Font("Arial", Font.BOLD, 100));
		sad.drawString("GAME OVER", 100, 250);
		sad.setFont(new Font("Arial", Font.PLAIN, 30));
		sad.drawString("Your survival score was: " + or.score, 210, 350);
		sad.drawString("Press ENTER to find an alternate universe", 140, 450);
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU_STATE){
	           drawMenu(g);
			}else if(currentState == ACTIVE_STATE){
	           drawGame(g);
			}else if(currentState == END_STATE){
	           drawEnd(g);
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if(currentState == ACTIVE_STATE || currentState == MENU_STATE){
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
			JOptionPane.showMessageDialog(null, "GRAVITY LAD is stuck in an infinitely long passage being sucked by a black hole! Reality is breaking apart! \nPress SPACE to switch gravity.\nPerhaps he can survive long enough to die of old age instead.");
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && currentState == MENU_STATE) {
			currentState = ACTIVE_STATE;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER && currentState == END_STATE) {
			currentState = MENU_STATE;
			time = new Timer(1000/60,this);
			jumper = new Jumper(0,200,50,50, "jumper");
			or = new ObjectRunner(jumper);
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
