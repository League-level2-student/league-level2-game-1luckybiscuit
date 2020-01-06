import java.awt.Color;
import java.awt.Graphics;

public class Block extends GameObject{
	Block(int x, int y, int width, int height, String type) {
		super(x, y, width, height, type);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);
	}
	void update() {
		setBounds();
		checkBounds();
	}
	void checkBounds() {
		if(x + width < 0) {
			active = false;
		}
	}
}
