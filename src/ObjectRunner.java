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
	int randInterval = (generator.nextInt(3) + 1)*1000;
	int randHeight = (generator.nextInt(4) + 1)*100;
	int coinFlip = generator.nextInt(2);
	int interval = 150;
	int intervalCount = 0;
	boolean stopped = false;
	ObjectRunner(Jumper jump) {
		this.jump = jump;
		addBlock(new Block(0, 50, GravityGuy.WIDTH + 50, 50));
		addBlock(new Block(0, 650, GravityGuy.WIDTH + 50, 50));
	}
	void manageBlocks() {
		if(System.currentTimeMillis() - timer >= interval) {
			if(intervalCount <= 10) {
				addBlock(new Block(GravityGuy.WIDTH, 50, 50, 50));
				addBlock(new Block(GravityGuy.WIDTH, 650, 50, 50));
			}
			timer = System.currentTimeMillis();
			//System.out.println(intervalCount);
		}
		if(System.currentTimeMillis() - randTimer >= randInterval) {
			//System.out.println(coinFlip);
			if(coinFlip == 0) {
				addBlock(new Block(GravityGuy.WIDTH, 100, 50, randHeight));
			}else if(coinFlip == 1) {
				addBlock(new Block(GravityGuy.WIDTH, 650-randHeight, 50, randHeight));
			}
			coinFlip = generator.nextInt(2);
			randHeight = (generator.nextInt(4) + 1)*100;
			randInterval = (generator.nextInt(5) + 1)*1000;
			randTimer = System.currentTimeMillis();
			intervalCount++; 
			System.out.println(intervalCount);
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
			BlockList.get(i).x -= 5;
			BlockList.get(i).update();
		}
		jump.vertBox.y += jump.velocity;
		jump.horiBox.x++;
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
			if(jump.horiBox.intersects(i.horiBox)) {
				if(jump.horiBox.getMaxX() > i.vertBox.getMinX()) {
					jump.stopped = true;
					jump.x = (int) (i.vertBox.getMinX()-jump.width);
				}
			}else{
				jump.stopped = false;
				if(jump.vertBox.intersects(i.vertBox)) {
					if(i.vertBox.getMaxY() > jump.vertBox.getMinY() && jump.vertBox.getMinY() > i.vertBox.getMinY()) {
						jump.y = (int) (i.vertBox.getMaxY());
						jump.velocity = 0;
					}
					if(i.vertBox.getMaxY() > jump.vertBox.getMaxY() && jump.vertBox.getMaxY() > i.vertBox.getMinY()) {
						jump.y = (int) (i.vertBox.getMinY() - jump.height);
						jump.velocity = 0;
					}
					
				}
			}
		}
	}
}
