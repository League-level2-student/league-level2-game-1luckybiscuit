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
	int floor;
	int ceiling;
	boolean saveCeiling;
	int vertSpace;
	int formation = 0;
	int randInterval;
	int randHeight;
	int randSpace;
	int coinFlip;
	int interval = 150;
	int oppositeInt;
	int intervalCount = 0;
	int counter = 1;
	int counterCounter = 1;
	int oof = 1;
	int score = 0;
	boolean stopped = false;
	ObjectRunner(Jumper jump) {
		this.jump = jump;
		addBlock(new Block(0, 50, GravityGuy.WIDTH + 100, 50));
		addBlock(new Block(0, 650, GravityGuy.WIDTH + 100, 50));
	}
	void newForm() {
		formation = generator.nextInt(2);
	}
	void manageBlocks() {
		prepFormation(formation);
		if(intervalCount < 5) {
			ceiling = 100;
			floor = 650;
			formation = 0;
			if(System.currentTimeMillis() - initialTimer > 0) {
				if(intervalCount == 0) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 400));
				}else if(intervalCount == 1) {
					addBlock(new Block(GravityGuy.WIDTH, 250, 50, 400));
				}else if(intervalCount == 2) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 250));
					addBlock(new Block(GravityGuy.WIDTH, 600, 50, 50));
				}else if(intervalCount == 3) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 200));
					addBlock(new Block(GravityGuy.WIDTH, 350, 50, 300));
				}
				initialTimer = System.currentTimeMillis()+1000;
				intervalCount++; 
			}
		}else if(intervalCount > 5 && intervalCount <= 15) {
			formation=1;
			interval = 149;
		}else if(intervalCount > 15 && intervalCount <= 30) {
			formation = 2;
			interval = 148;
		}else if(intervalCount > 30 && intervalCount <= 55) {
			formation = 0;
			interval = 147;
		}else if(intervalCount > 55 && intervalCount <= 100) {
			formation = 2;
			interval = 146;
		}else if(intervalCount > 100 && intervalCount <= 200) {
			formation = 1;
			interval = 145;
		}
	}
	void prepFormation(int formation) {
		if(System.currentTimeMillis() - timer >= interval) {
			if(counterCounter == 1) {
				oof = 1;
				counterCounter = 5;
			}else if(counterCounter == 13) {
				oof = -1;
				counterCounter = 9;
			}
			else if(counterCounter < 5) {
				counter = 1;
			}else if(counterCounter >= 5 && counterCounter < 10) {
				counter = 2;
			}else if(counterCounter >= 10 && counterCounter < 14) {
				counter = 3;
			}
			//gravity guy formation
			if(formation == 0) {
				ceiling = 100;
				floor = 650;
				//ceiling = generator.nextInt(4)*50;
				//floor = 800-ceiling;
				//floor and ceiling
				addBlock(new Block(GravityGuy.WIDTH, ceiling-50, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, floor, oppositeInt*10, 50));
				if(intervalCount >= 5) {
					randomWalls();
				}
			}
			//twin wave formation
			if(formation == 1) { 
				ceiling = 50+counter*50;
				floor = 700-(counter*50);
				addBlock(new Block(GravityGuy.WIDTH, ceiling-50, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, floor, oppositeInt*10, 50));
				randomWalls();
			}
			//single wave formation
			if(formation == 2) { 
				ceiling = counter*50;
				floor = ceiling + 550;
				addBlock(new Block(GravityGuy.WIDTH, ceiling, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, ceiling + 550, oppositeInt*10, 50));
				randomWalls();
			}
			counterCounter+=oof;
			timer = System.currentTimeMillis();
			oppositeInt = 157 - interval;
			score++;
		}
	}
	void randomWalls() {
		//vertSpace must not be less than 50
		reroll();
		if(System.currentTimeMillis() - randTimer >= randInterval) {
			//System.out.println(vertSpace);
			//System.out.println(randSpace);
			if(coinFlip == 0) {
				addBlock(new Block(GravityGuy.WIDTH, ceiling, 50, randHeight));
				//System.out.println("epic");
			}else if(coinFlip == 1) {
				addBlock(new Block(GravityGuy.WIDTH, floor-randHeight, 50, randHeight));
			}else if(coinFlip == 2) {
				addBlock(new Block(GravityGuy.WIDTH, ceiling, 50, randHeight));
				//System.out.println(randHeight + " " + randSpace);
				addBlock(new Block(GravityGuy.WIDTH, ceiling+randHeight+randSpace, 50, floor-ceiling-randHeight-randSpace));
			}else if(coinFlip == 3) {
				addBlock(new Block(GravityGuy.WIDTH, ceiling, 50, randHeight));
				addBlock(new Block(GravityGuy.WIDTH, ceiling+vertSpace+randHeight, 50, floor-vertSpace-ceiling -randHeight));
			}else if(coinFlip == 4) {
				addBlock(new Block(GravityGuy.WIDTH, ceiling+randHeight, 50, 50));
			}else if(coinFlip == 5) {
				addBlock(new Block(GravityGuy.WIDTH, ceiling+randHeight, 50, 50));
				addBlock(new Block(GravityGuy.WIDTH, ceiling+randSpace+randHeight, 50, 50));
			}
			intervalCount++; 
			score+=5;
			//System.out.println(intervalCount);
			randInterval = (generator.nextInt(3) + 1)*(700 - intervalCount*10);
			randTimer = System.currentTimeMillis();
		}
	}
	void reroll() {
		int vertSpace = (floor - ceiling)/5;
		coinFlip = generator.nextInt(5);
		randHeight = generator.nextInt(5)*vertSpace; 
		randSpace = (generator.nextInt(4) + 1)*vertSpace;
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
