import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectRunner {
	Jumper jump;
	ArrayList<Block> BlockList = new ArrayList();
	ArrayList<Block> WallList = new ArrayList();
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
	int blockCount = 0;
	int chunkCount = 0;
	int randomChunk = 5;
	int intStart = 5;
	int addCounter = intStart;
	int function = addCounter;
	int dimension;
	int altDimension;
	int wallSetPoint = GravityGuy.WIDTH + 200;
	boolean vibeCheck;
	boolean stopped = false;
	ObjectRunner(Jumper jump) {
		this.jump = jump;
		dimension = 10+(generator.nextInt(10)+1)*13;
		altDimension = 10+(generator.nextInt(10)+1)*13;
		addBlock(new Block(0, 50, wallSetPoint, 50, ""));
		addBlock(new Block(0, 650, wallSetPoint, 50, ""));
	}
	void manageBlocks() {
		cheque();
		if(intervalCount == function) {
			interval--;
			formation = generator.nextInt(5);
			addCounter += 3;
			function += addCounter;
		}
		if(intervalCount <= intStart) {
			if(System.currentTimeMillis() - initialTimer > 0) {
				if(intervalCount == intStart-3) {
					//addWall(new Block(GravityGuy.WIDTH, 100, 50, 400, "top"));
					addWall(new Block(GravityGuy.WIDTH, 100, 50, 100, "top"));
					addWall(new Block(GravityGuy.WIDTH, 200, 50, 100, "top"));
					addWall(new Block(GravityGuy.WIDTH, 300, 50, 100, "top"));
					addWall(new Block(GravityGuy.WIDTH, 400, 50, 100, "top"));
				}else if(intervalCount == intStart-2) {
					addWall(new Block(GravityGuy.WIDTH, 250, 50, 400, "bottom"));
				}else if(intervalCount == intStart-1) {
					addWall(new Block(GravityGuy.WIDTH, 100, 50, 150, "top"));
					addWall(new Block(GravityGuy.WIDTH, 500, 50, 150, "bottom"));
				}else if(intervalCount == intStart) {
					addWall(new Block(GravityGuy.WIDTH, 100, 50, 250, "top"));
					addWall(new Block(GravityGuy.WIDTH, 400, 50, 250, "bottom"));
				}
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
				if(intervalCount > intStart) {
					randomWalls();
				}
				//floor and ceiling
				addBlock(new Block(wallSetPoint, ceiling-50, oppositeInt*10, 50, ""));
				addBlock(new Block(wallSetPoint, floor, oppositeInt*10, 50, ""));
			}
			//twin wave formation
			else if(formation == 1) { 
				ceiling = 50+counter*50;
				floor = 700-(counter*50);
				addBlock(new Block(wallSetPoint, ceiling-50, oppositeInt*10, 50, ""));
				addBlock(new Block(wallSetPoint, floor, oppositeInt*10, 50, ""));
				randomWalls();
			}
			//single wave formation
			else if(formation == 2) { 
				ceiling = counter*50;
				floor = ceiling + 550;
				addBlock(new Block(wallSetPoint, ceiling, oppositeInt*10, 50, ""));
				addBlock(new Block(wallSetPoint, ceiling + 550, oppositeInt*10, 50, ""));
				randomWalls();
			}
			//morse code formation
			else if(formation == 3) { 
				ceiling = 100;
				floor = 650;
				addBlock(new Block(wallSetPoint, ceiling-50, oppositeInt*10-generator.nextInt(7)*10, 50, ""));
				addBlock(new Block(wallSetPoint, floor, oppositeInt*10-generator.nextInt(7)*10, 50, ""));
				randomWalls();
			}
			//far lands formation
			else if(formation == 4) { 
				ceiling = 50+generator.nextInt(6)*50;
				floor = 750-generator.nextInt(6)*50;
				addBlock(new Block(wallSetPoint, ceiling-50, (oppositeInt*generator.nextInt(3)+3)*15, 50, ""));
				addBlock(new Block(wallSetPoint, floor, (oppositeInt*generator.nextInt(3)+3)*15, 50, ""));
				randomWalls(); 
			}
			//squares formation
			else if(formation == 5) { 
				ceiling = generator.nextInt(5)*50;
				floor = 800-generator.nextInt(5)*50;
				addBlock(new Block(wallSetPoint, ceiling, dimension, dimension, "top"));
				reroll();
				addBlock(new Block(wallSetPoint, floor-dimension, dimension, dimension, "bottom"));
				randomWalls();
			}
			//rect formation
			else if(formation == 6) { 
				ceiling = generator.nextInt(5)*50;
				floor = 800-generator.nextInt(5)*50;
				addBlock(new Block(wallSetPoint, ceiling, dimension*2, altDimension/2+20, "top"));
				reroll();
				addBlock(new Block(wallSetPoint, floor-dimension, dimension*2, altDimension/2+20, "bottom"));
				randomWalls();
			}
			if(intervalCount > intStart) {
				chunkCount++;
			}
			//System.out.println(chunkCount);
			counterCounter+=oof;
			timer = System.currentTimeMillis();
			oppositeInt = 157 - interval;
			score++;
		}
	}
	void cheque() {
		for(Block i: WallList) {
			for(Block o: BlockList) {
				if(o.box.intersects(i.sensorBox)) {
					i.shorten();
					System.out.println("okay");
				}
			}
		}
	}
	void randomWalls() {
		//vertSpace must not be less than 50
		if(formation != 6 && formation != 5 && formation != 4) {
			if(coinFlip < 4) {
				if(chunkCount == randomChunk) {
					reroll();
					place();
					intervalCount++; 
					chunkCount = 0;
					randomChunk = (generator.nextInt(10)+3);
				}
			}else {
				if(blockCount < 5) {
					if(chunkCount == randomChunk) {
						place();
						blockCount++; 
						chunkCount = 0;
						randomChunk = (generator.nextInt(3)+3);
				}
				}else if(blockCount == 5) {
					blockCount = 0;
					intervalCount++;
					reroll();
				}
			}
		}
	}
	void place() {
		if(coinFlip == 0) {
			addWall(new Block(wallSetPoint, ceiling, 50, randHeight, "top"));
			//System.out.println("epic");
		}else if(coinFlip == 1) {
			addWall(new Block(wallSetPoint, floor-randHeight, 50, randHeight, "bottom"));
			//addWall(new Block(GravityGuy.WIDTH, floor-randHeight+50, 50, randHeight));
		}else if(coinFlip == 2) {
			//System.out.println(vertSpace + " " + randHeight + " " + randSpace);
			addWall(new Block(wallSetPoint, ceiling, 50, randHeight, "top"));
			addWall(new Block(wallSetPoint, ceiling+randHeight+randSpace+50, 50, floor-ceiling-randHeight-randSpace-50, "bottom"));
		}else if(coinFlip == 3) {
			addWall(new Block(wallSetPoint, ceiling, 50, randHeight, "top"));
			addWall(new Block(wallSetPoint, ceiling+randHeight+50, 50, floor-ceiling-randHeight, "bottom"));
		}else if(coinFlip == 4) {
			int hm = generator.nextInt(floor - ceiling);
			addBlock(new Block(wallSetPoint, ceiling + hm, 50, 50, ""));
		}else if(coinFlip == 5) {
			int hm = generator.nextInt(floor - ceiling);
			addBlock(new Block(wallSetPoint, ceiling + hm, 50, 50, ""));
			addBlock(new Block(wallSetPoint, floor - hm - 50, 50, 50, ""));
		}
	}
	void reroll() {
		dimension = 10+(generator.nextInt(9)+1)*13;
		altDimension = 10+(generator.nextInt(9)+1)*13;
		vertSpace = (floor - (ceiling + 50))/50;
		coinFlip = generator.nextInt(6);
		randHeight = (generator.nextInt(vertSpace)+1)*50;
		randSpace = generator.nextInt(vertSpace)*20; 
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
		for(Block i: BlockList) {
			i.draw(g);
		}
		for(Block i: WallList) {
			i.draw(g);
		}
	}
	void update(boolean active) {
		if(active) {
			manageBlocks();
			purgeObjects();
			for(int i = 0;i < BlockList.size();i++) {
				BlockList.get(i).x -= oppositeInt;
				BlockList.get(i).update();
			}
			for(int i = 0;i < WallList.size();i++) {
				WallList.get(i).x -= oppositeInt;
				WallList.get(i).update();
			}
		}
		jump.vertBox.y += jump.velocity;
		if(jump.stopped) {
			
		}
		jump.horiBox.x += oppositeInt;
		collision();
		jump.checkSpeed(oppositeInt/10);
		jump.update();
	}
	void addBlock(Block b) {
		BlockList.add(b);
	}
	void addWall(Block b) {
		WallList.add(b);
	}
	void collision() {
		for(Block i: BlockList) {
			collide(i);
		}
		for(Block i: WallList) {
			collide(i);
		}
	}
	void collide(GameObject i) {
		if(jump.horiBox.intersects(i.horiBox)) {
			if(jump.horiBox.getMaxX() > i.horiBox.getMinX()) {
				jump.stopped = true;
				if(jump.stopped) {
					jump.x = (int) (i.horiBox.getMinX()-jump.width);
				}
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
