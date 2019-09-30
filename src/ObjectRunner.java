import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectRunner {
	Jumper jump;
	ArrayList<Block> BlockList = new ArrayList();
	long initialTimer = 1000;
	long timer;
	long randTimer;
	Random generator = new Random();
	int formation = 0;
	int randInterval;
	int randHeight;
	int randSpace;
	int coinFlip;
	int interval = 150;
	int oppositeInt;
	int intervalCount = 0;
	int counter = 0;
	int counterCounter = 0;
	int oof = 1;
	int score = 0;
	boolean stopped = false;
	ObjectRunner(Jumper jump) {
		this.jump = jump;
		addBlock(new Block(0, 50, GravityGuy.WIDTH + 100, 50));
		addBlock(new Block(0, 650, GravityGuy.WIDTH + 100, 50));
	}
	void manageBlocks() {
		prepFormation(formation);
		if(intervalCount < 5) {
			formation = 0;
			if(System.currentTimeMillis() - initialTimer > 0) {
				if(intervalCount == 0) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 400));
				}else if(intervalCount == 1) {
					addBlock(new Block(GravityGuy.WIDTH, 250, 50, 400));
				}else if(intervalCount == 2) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 300));
					//System.out.println(randHeight + " " + randSpace);
					addBlock(new Block(GravityGuy.WIDTH, 550, 50, 100));
				}else if(intervalCount == 3) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 200));
					addBlock(new Block(GravityGuy.WIDTH, 350, 50, 300));
				}
				initialTimer = System.currentTimeMillis()+1000;
				intervalCount++; 
				System.out.println(intervalCount);
			}
		}else if(intervalCount > 5 && intervalCount <= 10) {
			formation = 0;
			interval = 149;
		}else if(intervalCount > 10 && intervalCount <= 30) {
			formation = 0;
			interval = 148;
		}else if(intervalCount > 30 && intervalCount <= 55) {
			interval = 147;
		}
	}
	void prepFormation(int formation) {
		if(System.currentTimeMillis() - timer >= interval) {
			//gravity guy formation
			if(formation == 0) {
				//floor and ceiling
				addBlock(new Block(GravityGuy.WIDTH, 50, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, 650, oppositeInt*10, 50));
				if(intervalCount >= 5) {
					randomWalls(100,650);
				}
			}
			//twin wave formation
			if(formation == 1) { 
				if(counterCounter == 0) {
					oof = 1;
				}else if(counterCounter == 15) {
					oof = -1;
				}
				else if(counterCounter < 5) {
					counter = 1;
				}else if(counterCounter >= 5 && counterCounter < 10) {
					counter = 2;
				}else if(counterCounter >= 10 && counterCounter < 15) {
					counter = 3;
				}
				addBlock(new Block(GravityGuy.WIDTH, 50+counter*50, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, 650-(counter*50), oppositeInt*10, 50));
				counterCounter+=oof;
			}
			//single wave formation
			if(formation == 1) { 
				if(counterCounter == 0) {
					oof = 1;
				}else if(counterCounter == 15) {
					oof = -1;
				}
				else if(counterCounter < 5) {
					counter = 1;
				}else if(counterCounter >= 5 && counterCounter < 10) {
					counter = 2;
				}else if(counterCounter >= 10 && counterCounter < 15) {
					counter = 3;
				}
				addBlock(new Block(GravityGuy.WIDTH, 50+counter*50, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, 650-(counter*50), oppositeInt*10, 50));
				counterCounter+=oof;
			}
			timer = System.currentTimeMillis();
			oppositeInt = 158 - interval;
			score++;
		}
	}
	void randomWalls(int floor, int ceiling) {
		int vertSpace = ceiling - floor;
		coinFlip = generator.nextInt(3);
		randHeight = (generator.nextInt(4) + 1)*vertSpace/5; 
		randSpace = (generator.nextInt(4)+1)*vertSpace/10;
		if(System.currentTimeMillis() - randTimer >= randInterval) {
			if(coinFlip == 0) {
				addBlock(new Block(GravityGuy.WIDTH, floor, 50, randHeight));
			}else if(coinFlip == 1) {
				addBlock(new Block(GravityGuy.WIDTH, ceiling-randHeight, 50, randHeight));
			}else if(coinFlip == 2) {
				addBlock(new Block(GravityGuy.WIDTH, floor, 50, randHeight));
				//System.out.println(randHeight + " " + randSpace);
				addBlock(new Block(GravityGuy.WIDTH, floor+randHeight+randSpace, 50, ceiling-floor-randHeight-randSpace));
			}else if(coinFlip == 3) {
				addBlock(new Block(GravityGuy.WIDTH, floor, 50, randHeight));
				addBlock(new Block(GravityGuy.WIDTH, floor+vertSpace/5+randHeight, 50, ceiling-vertSpace/5-floor-randHeight));
			}
			intervalCount++; 
			score+=5;
			System.out.println(intervalCount);
			randInterval = (generator.nextInt(3) + 1)*(700 - intervalCount*10);
			randTimer = System.currentTimeMillis();
		}
	}
	void reroll(int floor, int ceiling) {
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
