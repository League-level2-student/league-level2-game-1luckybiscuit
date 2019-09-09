import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectRunner {
	Jumper jump;
	ArrayList<Block> BlockList = new ArrayList();
	long timer;
	long randTimer;
	Random generator = new Random();
	int formation = 0;
	int randInterval = (generator.nextInt(3) + 1)*1000;
	int randHeight = (generator.nextInt(4) + 1)*100;
	int randSpace = generator.nextInt(4)*50;
	int coinFlip = generator.nextInt(2);
	int interval = 155;
	int oppositeInt = 160 - interval;
	int intervalCount = 0;
	boolean stopped = false;
	ObjectRunner(Jumper jump) {
		this.jump = jump;
		addBlock(new Block(0, 50, GravityGuy.WIDTH + 100, 50));
		addBlock(new Block(0, 650, GravityGuy.WIDTH + 100, 50));
	}
	void manageBlocks() {
		if(formation == 0) {
			if(System.currentTimeMillis() - timer >= interval) {
				addBlock(new Block(GravityGuy.WIDTH, 50, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, 650, oppositeInt*10, 50));
				timer = System.currentTimeMillis();
			}
			if(System.currentTimeMillis() - randTimer >= randInterval) {
				if(coinFlip == 0) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, randHeight));
				}else if(coinFlip == 1) {
					addBlock(new Block(GravityGuy.WIDTH, 650-randHeight, 50, randHeight));
				}else if(coinFlip == 2) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, randHeight));
					addBlock(new Block(GravityGuy.WIDTH, 200+randSpace+randHeight, 50, 450-randHeight-randSpace));
				}
				coinFlip = generator.nextInt(3);
				randHeight = (generator.nextInt(4) + 1)*100; 
				randInterval = (generator.nextInt(3) + 1)*(1000 - intervalCount*10);
				randTimer = System.currentTimeMillis();
				oppositeInt = 157 - interval;
				intervalCount++; 
				System.out.println(intervalCount);
			}
		}
		if(formation == 1) { 
			if(System.currentTimeMillis() - timer >= interval) {
				addBlock(new Block(GravityGuy.WIDTH, 50 + randHeight, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, 550+ randHeight, oppositeInt*10, 50));
				timer = System.currentTimeMillis();
			}
		}
		if(intervalCount <= 5) {
			formation = 0;
		}else if(intervalCount > 5 && intervalCount <= 10) {
			formation = 0;
			interval=152;
		}else if(intervalCount > 10 && intervalCount <= 30) {
			interval=150;
		}else if(intervalCount > 30 && intervalCount <= 55) {
			interval=148;
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
			BlockList.get(i).x -= oppositeInt;
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
