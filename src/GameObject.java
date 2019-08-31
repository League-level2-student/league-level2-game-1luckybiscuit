import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	double x;
	double y;
	int width;
	int height;
	boolean active;
	Rectangle colBox;
	GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		active = true;
		colBox = new Rectangle(x,y,width,height);
	}
	void setBounds() {
		colBox = new Rectangle((int) x,(int) y,width,height);
	}
}
