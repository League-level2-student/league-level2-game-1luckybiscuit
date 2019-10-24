import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectRunner {
	Jumper jump;
	ArrayList<Block> BlockList = new ArrayList();
	long initialTimer = 1000;
	long timer;
	Random generator = new Random();
	int floor;
	int ceiling;
	int vertSpace;
	int formation = 0;
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
	int chunkCount = 0;
	int randomChunk = 5;
	int addCounter = 5;
	int function = addCounter;
	int dimension;
	int altDimension;
	boolean stopped = false;
	ObjectRunner(Jumper jump) {
		this.jump = jump;
		dimension = 10+(generator.nextInt(10)+1)*13;
		altDimension = 10+(generator.nextInt(10)+1)*13;
		addBlock(new Block(0, 50, GravityGuy.WIDTH + 100, 50));
		addBlock(new Block(0, 650, GravityGuy.WIDTH + 100, 50));
	}
	void manageBlocks() {
		if(intervalCount == function) {
			interval--;
			if(intervalCount < 75) {
				formation++;
			}else {
				formation = generator.nextInt(5);
			}
			addCounter += 3;
			function += addCounter;
		}
		if(intervalCount <= 5) {
			if(System.currentTimeMillis() - initialTimer > 0) {
				if(intervalCount == 1) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 400));
				}else if(intervalCount == 2) {
					addBlock(new Block(GravityGuy.WIDTH, 250, 50, 400));
				}else if(intervalCount == 3) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 150));
					addBlock(new Block(GravityGuy.WIDTH, 500, 50, 150));
				}else if(intervalCount == 4) {
					addBlock(new Block(GravityGuy.WIDTH, 100, 50, 250));
					addBlock(new Block(GravityGuy.WIDTH, 400, 50, 250));
				}
				score+=5;
				initialTimer = System.currentTimeMillis()+1000;
				intervalCount++; 
			}
			prepFormation(0);
		}else {
			prepFormation(formation);
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
				if(intervalCount > 5) {
					randomWalls();
				}
				//floor and ceiling
				addBlock(new Block(GravityGuy.WIDTH, ceiling-50, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, floor, oppositeInt*10, 50));
			}
			//twin wave formation
			else if(formation == 1) { 
				ceiling = 50+counter*50;
				floor = 700-(counter*50);
				addBlock(new Block(GravityGuy.WIDTH, ceiling-50, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, floor, oppositeInt*10, 50));
				randomWalls();
			}
			//single wave formation
			else if(formation == 2) { 
				ceiling = counter*50;
				floor = ceiling + 550;
				addBlock(new Block(GravityGuy.WIDTH, ceiling, oppositeInt*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, ceiling + 550, oppositeInt*10, 50));
				randomWalls();
			}
			//morse code formation
			else if(formation == 3) { 
				ceiling = 100;
				floor = 650;
				addBlock(new Block(GravityGuy.WIDTH, ceiling-50, oppositeInt*10-generator.nextInt(7)*10, 50));
				addBlock(new Block(GravityGuy.WIDTH, floor, oppositeInt*10-generator.nextInt(7)*10, 50));
				randomWalls();
			}
			//far lands formation
			else if(formation == 4) { 
				ceiling = 50+generator.nextInt(6)*50;
				floor = 750-generator.nextInt(6)*50;
				addBlock(new Block(GravityGuy.WIDTH, ceiling-50, (oppositeInt*generator.nextInt(3)+3)*15, 50));
				addBlock(new Block(GravityGuy.WIDTH, floor, (oppositeInt*generator.nextInt(3)+3)*15, 50));
				randomWalls(); 
			}
			//squares formation
			else if(formation == 5) { 
				ceiling = generator.nextInt(5)*50;
				floor = 800-generator.nextInt(5)*50;
				addBlock(new Block(GravityGuy.WIDTH, ceiling, dimension, dimension));
				reroll();
				addBlock(new Block(GravityGuy.WIDTH, floor-dimension, dimension, dimension));
				randomWalls();
			}
			//rect formation
			else if(formation == 6) { 
				ceiling = generator.nextInt(5)*50;
				floor = 800-generator.nextInt(5)*50;
				addBlock(new Block(GravityGuy.WIDTH, ceiling, dimension*2, altDimension/2+20));
				reroll();
				addBlock(new Block(GravityGuy.WIDTH, floor-dimension, dimension*2, altDimension/2+20));
				randomWalls();
			}
			if(intervalCount > 5) {
				chunkCount++;
			}
			//System.out.println(chunkCount);
			counterCounter+=oof;
			timer = System.currentTimeMillis();
			oppositeInt = 157 - interval;
			score++;
		}
	}
	void randomWalls() {
		//vertSpace must not be less than 50
		reroll();
		if(chunkCount == randomChunk) {
			//System.out.println("Vert " + vertSpace);
			//System.out.println("Rand " + randSpace);
			if(formation != 6 && formation != 5 && formation != 4) {
				place();
			}
			chunkCount = 0;
			randomChunk = (generator.nextInt(10)+3);
			intervalCount++; 
			System.out.println(intervalCount);
		}
	}
	void place() {
		if(coinFlip == 0) {
			addBlock(new Block(GravityGuy.WIDTH, ceiling, 50, randHeight-50));
			//System.out.println("epic");
		}else if(coinFlip == 1) {
			addBlock(new Block(GravityGuy.WIDTH, floor-randHeight+50, 50, randHeight-50));
		}else if(coinFlip == 2) {
			addBlock(new Block(GravityGuy.WIDTH, ceiling, 50, randHeight-50));
			//System.out.println(randHeight + " " + randSpace);
			addBlock(new Block(GravityGuy.WIDTH, ceiling+randHeight+randSpace+50, 50, floor-ceiling-randHeight-randSpace-50));
		}else if(coinFlip == 3) {
			addBlock(new Block(GravityGuy.WIDTH, ceiling, 50, randHeight-50));
			addBlock(new Block(GravityGuy.WIDTH, ceiling+vertSpace+randHeight+50, 50, floor-vertSpace-ceiling-randHeight-50));
		}else if(coinFlip == 4) {
			for(int i = 0;i<5;i++) {
				addBlock(new Block(GravityGuy.WIDTH+i*100, ceiling+generator.nextInt(floor - ceiling), 50, 50));
			}
		}else if(coinFlip == 5) {
			for(int i = 0;i<5;i++) {
				addBlock(new Block(GravityGuy.WIDTH+i*100, ceiling+generator.nextInt(floor - ceiling), 50, 50));
				addBlock(new Block(GravityGuy.WIDTH+i*100, floor-generator.nextInt(floor - ceiling), 50, 50));
			}
		}
	}
	void reroll() {
		dimension = 10+(generator.nextInt(9)+1)*13;
		altDimension = 10+(generator.nextInt(9)+1)*13;
		vertSpace = (floor - ceiling)/5;
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
