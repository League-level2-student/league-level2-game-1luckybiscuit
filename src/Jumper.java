import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Jumper extends GameObject {
	int gravity = 1;
	int velocity = 1;
	boolean gravityChanged = false;
	boolean stopped = false;
	public static BufferedImage spriteImg;
	Jumper(int x, int y, int width, int height, String type) {
		super(x, y, width, height, type);
		// TODO Auto-generated constructor stub
		
	}
	void draw(Graphics g) {
		//g.setColor(Color.GREEN);
		//g.fillRect((int)x, (int)y, width, height);
		try {
            spriteImg = ImageIO.read(this.getClass().getResourceAsStream("gravityLad.png"));
		} catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
		}
		if(gravity == 1) {
			g.drawImage(spriteImg, x, y, width, height, null);
		}else if(gravity == -1) {
			g.drawImage(spriteImg, x, y + height, width, 0 - height, null);
		}
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
	void checkSpeed(float difficulty) {
		if(stopped == false && x < 400) {
			x = x+ 2 + (int)difficulty;
		}
	}
}
