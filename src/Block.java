import java.awt.Color;
import java.awt.Graphics;

public class Block extends GameObject{

	Block(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
	void update() {
		x-=5;
	}
	void checkBounds() {
		if(x < 0) {
			active = false;
		}
	}
	
}
