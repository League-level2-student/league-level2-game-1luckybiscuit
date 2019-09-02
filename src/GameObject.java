import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	double x;
	double y;
	int width;
	int height;
	boolean active;
	Rectangle vertBox;
	Rectangle horiBox;
	GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		active = true;
		vertBox = new Rectangle(x,y,width,height);
		horiBox = new Rectangle(x,y,width,height);
	}
	void setBounds() {
		vertBox = new Rectangle((int) x,(int) y,width,height);
		horiBox = new Rectangle((int) x,(int) y + 1,width,height - 2);
	}
}
