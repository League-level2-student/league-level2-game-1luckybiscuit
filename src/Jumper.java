import java.awt.Color;
import java.awt.Graphics;

public class Jumper extends GameObject {
	int gravity = 1;
	int velocity = 1;
	boolean gravityChanged = false;
	Jumper(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		
	}
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, width, height);
	}
	void update() {
		velocity += gravity;
		//System.out.println(velocity);
		y += velocity;
		setBounds();
		checkBounds();
		//replace with actual velocity
	}
	void checkBounds() {
		if(x < -50 || y > GravityGuy.HEIGHT || y < 0) {
			active = false;
		}
	}
}
