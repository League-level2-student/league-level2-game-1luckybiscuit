import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectRunner {
	Jumper jump;
	ArrayList<Block> topBlockList = new ArrayList();
	ArrayList<Block> botmBlockList = new ArrayList();
	long timer;
	Random generator = new Random();
	int randInterval = generator.nextInt(5)*100;
	int interval = 130;
	ObjectRunner(Jumper jump) {
		this.jump = jump;
	}
	void manageBlocks() {
		if(System.currentTimeMillis() - timer >= interval) {
			addTopBlock(new Block(GravityGuy.WIDTH, 100, 40, 40));
			addBotmBlock(new Block(GravityGuy.WIDTH, 600, 40, 40));
			timer = System.currentTimeMillis();
		}
	}
	void purgeObjects() {
		for(int i = 0;i < topBlockList.size();i++) {
			if(topBlockList.get(i).active == false) {
				topBlockList.remove(i);
			}
		}
		for(int i = 0;i < botmBlockList.size();i++) {
			if(botmBlockList.get(i).active == false) {
				botmBlockList.remove(i);
			}
		}
	}
	void draw(Graphics g) {
		jump.draw(g);
		for(int i = 0;i < topBlockList.size();i++) {
			topBlockList.get(i).draw(g);
		}
		for(int i = 0;i < botmBlockList.size();i++) {
			botmBlockList.get(i).draw(g);
		}
		
	}
	void update() {
		jump.update();
		for(int i = 0;i < topBlockList.size();i++) {
			topBlockList.get(i).update();
		}
		for(int i = 0;i < botmBlockList.size();i++) {
			botmBlockList.get(i).update();
		}
	}
	void addTopBlock(Block b) {
		topBlockList.add(b);
	}
	void addBotmBlock(Block b) {
		botmBlockList.add(b);
	}
	void collide() {
		for(Block i: topBlockList) {
			if(jump.colBox.intersects(i.colBox)) {
				System.out.println("collision");
			}
		}
		for(Block i: botmBlockList) {
			if(jump.colBox.intersects(i.colBox)) {
				System.out.println("collision");
			}
		}
	}
}
