import java.awt.Color;
import java.awt.Graphics;

public class Block extends GameObject{

	Block(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int)y, width, height);
	}
	void update() {
		x-=5;
		setBounds();
		checkBounds();
	}
	void checkBounds() {
		if(x + width < 0) {
			active = false;
		}
	}
	
}
