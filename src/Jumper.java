import java.awt.Color;
import java.awt.Graphics;

public class Jumper extends GameObject {
	int gravity = 1;
	int velocity = 10;
	int speed;
	Jumper(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		
	}
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
	void update() {
		speed = gravity*velocity*2;
		y += speed;
		setBounds();
		//replace with actual velocity
	}
}
