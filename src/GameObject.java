import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean active;
	Rectangle vertBox;
	Rectangle horiBox;
	Rectangle box;
	Rectangle topColBox;
	Rectangle botColBox;
	GameObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		active = true;
		vertBox = new Rectangle(x+1,y,width-1,height);
		horiBox = new Rectangle(x,y + 1,width,height - 2);
		box = new Rectangle(x,y,width,height);
		topColBox = new Rectangle(x + width/2,y-50, 150, 100);
		botColBox = new Rectangle(x + width/2,7+height-50,150,100);
	}
	void setBounds() {
		box = new Rectangle(x,y,width,height);
		vertBox = new Rectangle(x+1,y,width-1,height);
		horiBox = new Rectangle(x,y + 1,width,height - 2);
		topColBox = new Rectangle(x + width/2,y-50, 150, 100);
		botColBox = new Rectangle(x + width/2,7+height-50,150,100);
	}
}
