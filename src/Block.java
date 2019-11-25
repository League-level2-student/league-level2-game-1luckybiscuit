import java.awt.Color;
import java.awt.Graphics;

public class Block extends GameObject{
	Boolean alerted = false;
	Block(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	void draw(Graphics g) {
		if(alerted) {
			g.setColor(Color.RED);
			g.fillOval(x+width/2-75, y-50, 150, 100);
			g.fillOval(x+width/2-75, y+height-50, 150, 100);
		}else {
			g.setColor(Color.CYAN);
		}
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
	void setHeight(int height) {
		this.height = height;
	}
	void setY (int y) {
		this.y = y;
	}
}
