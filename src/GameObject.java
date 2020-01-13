import java.awt.Rectangle;

public class GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean active;
	String type;
	int checkPoint;
	Rectangle vertBox;
	Rectangle horiBox;
	Rectangle box;
	Rectangle sensorBox;
	GameObject(int x, int y, int width, int height, String type) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		if(type.equals("top")) {
			checkPoint = y+height;
		}else if(type.equals("bottom")) {
			checkPoint = y;
		}
		active = true;
		vertBox = new Rectangle(x+1,y,width-1,height);
		horiBox = new Rectangle(x,y + 1,width,height - 2);
		box = new Rectangle(x,y,width,height);
		sensorBox = new Rectangle(x+width/2-75,checkPoint-50,150,100);
	}
	void setHeight(int height) {
		this.height = height;
	}
	void shorten() {
		setHeight(height-50);
		if(this.type.equals("bottom")) {
			setY(y+50);
		}
	}
	void setY (int y) {
		this.y = y;
	}
	void setBounds() {
		if(type.equals("top")) {
			checkPoint = y+height;
		}else if(type.equals("bottom")) {
			checkPoint = y;
		}
		box = new Rectangle(x,y,width,height);
		vertBox = new Rectangle(x+1,y,width-1,height);
		horiBox = new Rectangle(x,y + 1,width,height - 2);
		sensorBox = new Rectangle(x+width/2-75,checkPoint-50,150,100);
	}
}
