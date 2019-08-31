import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectRunner {
	Jumper jump;
	ArrayList<Block> BlockList = new ArrayList();
	long timer;
	long randTimer;
	Random generator = new Random();
	int formPicker = generator.nextInt(5);
	int randInterval = generator.nextInt((5) + 1)*1000;
	int coinFlip = generator.nextInt(2);
	int interval = 150;
	int intervalCount = 0;
	ObjectRunner(Jumper jump) {
		this.jump = jump;
		addBlock(new Block(0, 50, GravityGuy.WIDTH + 50, 50));
		addBlock(new Block(0, 650, GravityGuy.WIDTH + 50, 50));
	}
	void manageBlocks() {
		if(System.currentTimeMillis() - timer >= interval) {
			if(intervalCount <= 100) {
				addBlock(new Block(GravityGuy.WIDTH, 50, 50, 50));
				addBlock(new Block(GravityGuy.WIDTH, 650, 50, 50));
			}
			timer = System.currentTimeMillis();
			intervalCount++;
			//System.out.println(intervalCount);
		}
		if(System.currentTimeMillis() - randTimer >= randInterval) {
			System.out.println(coinFlip);
			if(coinFlip == 0) {
				addBlock(new Block(GravityGuy.WIDTH, 100, 50, 200));
			}else if(coinFlip == 1) {
				addBlock(new Block(GravityGuy.WIDTH, 450, 50, 200));
			}
			coinFlip = generator.nextInt(2);
			randInterval = generator.nextInt((5) + 1)*1000;
			randTimer = System.currentTimeMillis();
		}
	}
	void purgeObjects() {
		for(int i = 0;i < BlockList.size();i++) {
			if(BlockList.get(i).active == false) {
				BlockList.remove(i);
			}
		}
	}
	void draw(Graphics g) {
		jump.draw(g);
		for(int i = 0;i < BlockList.size();i++) {
			BlockList.get(i).draw(g);
		}
	}
	void update() {
		for(int i = 0;i < BlockList.size();i++) {
			BlockList.get(i).update();
		}
		jump.colBox.y += jump.velocity;
		collide();
		jump.update();
		manageBlocks();
		purgeObjects();
	}
	void addBlock(Block b) {
		BlockList.add(b);
	}
	void collide() {
		for(Block i: BlockList) {
			if(jump.colBox.intersects(i.colBox)) {
				if(i.colBox.getMaxY() > jump.colBox.getMinY() && jump.colBox.getMinY() > i.colBox.getMinY()) {
					jump.y = (int) (i.colBox.getMaxY());
					jump.velocity = 0;
				}
				if(jump.colBox.getMaxX() < i.colBox.getMinX()) {
					System.out.println("horizontalCollision");
				}
				if(i.colBox.getMaxY() > jump.colBox.getMaxY() && jump.colBox.getMaxY() > i.colBox.getMinY()) {
					jump.y = (int) (i.colBox.getMinY() - jump.height);
					jump.velocity = 0;
				}
			}
		}
	}
}
