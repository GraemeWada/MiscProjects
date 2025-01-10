package com.tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class GameManager {
	
	boolean started = false;
	//Block area bounds
	final int WIDTH = 300;
	final int HEIGHT = 600;
	
	static int leftX, rightX, topY, bottomY;
	
	static Piece currentPiece;
	 int Xi;
	 int Yi;
	static Piece next;
	 int nX;
	 int nY;
	 Piece holdPiece;
	 int hX = 490;
	 int hY = 150;
	public static ArrayList<Block> staticBlocks = new ArrayList<>();
	
	//dropping
	public static int dropInterval = 19; //every 60 frames
	private final static int[] intervals = {48, 48, 43, 38, 33, 28, 23, 18, 13, 8, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1};
	
	//score & level
	public int score = 0;
	public static int level = 0;
	private final int[] firstScores = {40, 100, 300, 1200};
	public int levelClears = 0;
	final int lineClearsPerLevel = 10;
	
	public static Color[][] palettes = {
//			{new Color(0x152525, false), new Color(0x152525, false), new Color(0x152525, false), new Color(0x152525, false), new Color(0x152525, false), new Color(0x152525, false), new Color(0x152525, false)},
			{Color.ORANGE, Color.BLUE, Color.CYAN, Color.YELLOW, new Color(0xA000F0, false), Color.RED, Color.GREEN},
			{new Color(0x797d62, false), new Color(0x9b9b7a, false), new Color(0xd9ae94, false), new Color(0xf1dca7, false), new Color(0xffcb69, false), new Color(0xd08c60, false), new Color(0x997b66, false)},
			{new Color(0xff9900, false), new Color(0xffc800, false), new Color(0xffe000, false), new Color(0xfff700, false), new Color(0xb8f500, false), new Color(0x95e214, false), new Color(0x72ce27, false)},
			{new Color(0x588b8b, false), new Color(0xcccccc, false), new Color(0xffd5c2, false), new Color(0xf28f3b, false), new Color(0xc8553d, false), new Color(0x4d5067, false), new Color(0x93b7be, false)},
			{new Color(0xffffff, false), new Color(0xeeeeee, false), new Color(0xdddddd, false), new Color(0xcccccc, false), new Color(0xbbbbbb, false), new Color(0xaaaaaa, false), new Color(0x999999, false)},
			{new Color(0xea9ba2, false), new Color(0x83203e, false), new Color(0xb8475d, false), new Color(0xcdb9ae, false), new Color(0xe6dcd7, false), new Color(0xdddddd, false), new Color(0x3f4c51, false)},
			{new Color(0xffecee, false), new Color(0xffe0e6, false), new Color(0xffd4de, false), new Color(0xffc9d6, false), new Color(0xfebdce, false), new Color(0xfeb1c6, false), new Color(0xfea5be, false)},
			{new Color(0x0ad2ff, false), new Color(0x2962ff, false), new Color(0x9500ff, false), new Color(0xff0059, false), new Color(0xff8c00, false), new Color(0xb4e600, false), new Color(0x0fffdb, false)},
			{Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE}
	};
	
	boolean gameOver = false;
	
	static Queue<Piece> nextList = new LinkedList<>();
	static int[] bag = {0,1,2,3,4,5,6};
	static int bagCounter = 0;
	
	public GameManager () {
		gameSetup();
	}
	
	public void gameSetup() {
		leftX = (Panel.WIDTH/2) - (WIDTH / 2);
		rightX = leftX + WIDTH;
		topY = 50;
		bottomY = topY + HEIGHT;
		
		Xi = leftX + (WIDTH/2) - Block.SIZE;
		Yi = topY + Block.SIZE;
		
		nX = rightX + 175;
		nY = topY + 300;
		
		hX = leftX - 225;
		hY = topY + 100;
		
		shuffleBag();
		
		currentPiece = pick();
		currentPiece.setXY(Xi, Yi);
		next = pick();
		next.setXY(nX, nY);
		holdPiece = pick();
		holdPiece.setXY(hX, hY);
		holdPiece.held = true;
		
		for(int i = 1; i < 3; i ++ ) {
			Piece p = pick();
			nextList.offer(p);
			p.setXY(nX, nY + 120*i);
		}
		
		dropInterval = intervals[level];
	}
	
	private static Piece pick() {
//		Piece p = null;
//		int i = new Random().nextInt(7);
//		try {
//			while(i == next.getPieceType() && next.getPieceType() == nextList.peek().getPieceType()) {
//				i = new Random().nextInt(7);
//			}
//		}
//		catch (NullPointerException e) {}
		
//		return p;
		Piece p = null;
		switch(bag[bagCounter]) {
		case 0: p = new L1(); break;
		case 1: p = new L2(); break;
		case 2: p = new	Bar(); break;
		case 3: p = new Square(); break;
		case 4: p = new T(); break;
		case 5: p = new Z1(); break;
		case 6: p = new Z2(); break;
		}
		bagCounter++;
		if(bagCounter > 6) {
			bagCounter = 0;
			shuffleBag();
		}
		return p;
	}
	
	static void shuffleBag()
	  {
	    Random rnd = new Random();
	    for (int i = bag.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      int a = bag[index];
	      bag[index] = bag[i];
	      bag[i] = a;
	    }
	    for(int w = 0; w < 7; w++) {
	    	System.out.print(bag[w]);
	    }
	    System.out.println();
	  }
	
	public void tick() throws Exception {
		if(gameOver) {
			reset();
		}
		
		if(!currentPiece.active) {
			for (int i = 0; i < currentPiece.b.length; i++) {
				staticBlocks.add(currentPiece.b[i]);
			}
			
			if(currentPiece.b[0].x == Xi && currentPiece.b[0].y == Yi) {
				gameOver = true;
			}
			
			currentPiece = next;
			currentPiece.setXY(Xi, Yi);
			next = nextList.poll();
			next.setXY(nX, nY);
			Piece newP = pick();
			nextList.offer(newP);
			Iterator<Piece> it = nextList.iterator();
			int i = 1;
	         while (it.hasNext()) {
	           it.next().setXY(nX, nY +(120*i++));
	         }
			
			checkLines();
		} else {
			currentPiece.tick();
		}
		
		if(KeyHandler.hold) {
			hold();
			KeyHandler.hold = false;
		}
	}
	
	public void hold() {
		if(!currentPiece.held) {
			currentPiece.held = true;
			currentPiece.setXY(hX, hY);
			holdPiece.setXY(Xi, Yi);
			Piece p = currentPiece;
			currentPiece = holdPiece;
			holdPiece = p;
		}
	}
	
	public void checkLines() {
		int lineClears = 0;
		int x = leftX;
		int y = topY;
		int blockCount = 0;
		
		while (x < rightX && y < bottomY) {
			
			for (int i = 0; i < staticBlocks.size(); i ++) {
				if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
					blockCount++;
				}
			}
			
			x += Block.SIZE;
			
			if(x == rightX) {
				if(blockCount == 10) { // line width 10
					lineClears++;
					//remove
					for( int i  = staticBlocks.size()-1; i > -1; i--) {
						if(staticBlocks.get(i).y == y) {
							staticBlocks.remove(i);
						}
					}
					
					//shift
					for (int i = 0; i < staticBlocks.size(); i++) {
						if(staticBlocks.get(i).y < y) {
							staticBlocks.get(i).y += Block.SIZE;
						}
					}
				}
				
				blockCount = 0;
				x = leftX;
				y += Block.SIZE;
			}
		}
//		System.out.println(lineClears);
		levelClears += lineClears;
		if(levelClears >= lineClearsPerLevel) {
			level++;
			levelClears -= lineClearsPerLevel;
			if(level <= 29) {
				dropInterval = intervals[level];
			}
		}
		//add score
		if(lineClears > 0) {
			score += firstScores[lineClears-1]*(level+1);
		} 
	}
	
	public void draw(Graphics2D g) {
		//main play area
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(4f));
		g.drawRect(leftX-4, topY-4, WIDTH+8, HEIGHT+8);
		g.setColor(new Color(0x676767, false));
		g.setStroke(new BasicStroke(1f));
		for(int i = 1; i < 10; i++) {
			g.drawLine(leftX+Block.SIZE*i, topY, leftX+Block.SIZE*i, topY+HEIGHT);
		}
		for(int i = 1; i < 20; i++) {
			g.drawLine(leftX, topY+Block.SIZE*i, leftX+WIDTH, topY+Block.SIZE*i);
		}
		
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(4f));
		//next piece display box
		int x = rightX + 100;
		int y = topY + 206;
		g.drawRect(x, y, 200, 400);
		g.setFont(new Font("Arial", Font.PLAIN, 30));
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawString("NEXT", x + 60, y + 60);
		
		//Score
		int x2 = rightX + 100;
		int y2 = topY-4;
		g.drawRect(x2, y2, 200, 200);
		g.drawString("SCORE", x2 + 40, y2 + 60);
		g.drawString(toStringScore(), x2 + 40, y2 +110);
		
		
		
		//hold
		int x3 = leftX - 300;
		g.drawRect(x3, y2, 200, 200);
		g.drawString("HOLD", x3 + 60, y2 + 60);
		
		g.drawRect(x3, y2 + 210, 200, 150);
		g.drawString("LEVEL", x3 + 40, y2 + 270);
		g.drawString(Integer.toString(level), x3 + 40, y2 +320);
		if(holdPiece != null) {
			holdPiece.draw(g);
		}
		
		if(currentPiece != null ) {
			g.setColor(new Color(0x30ffffff, true));
			for (int i = 0; i < 4; i++) {
				g.fillRect(currentPiece.b[i].x, currentPiece.b[i].y, Block.SIZE, HEIGHT-currentPiece.b[i].y+52);
			}
			currentPiece.draw(g);
		}
		
		 Iterator<Piece> it = nextList.iterator();
	      
         // Iterating Queue
         while (it.hasNext()) {
           it.next().draw(g);
         }
		
		next.draw(g);
		
		for (int i = 0; i < staticBlocks.size(); i++) {
			staticBlocks.get(i).draw(g);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(50f));
		if(KeyHandler.pause) {
			x = leftX + 70;
			y = topY + 320;
			g.drawString("PAUSE", x, y);
		}
		
		g.setColor(new Color(0xfc036f, false));
		g.setFont(g.getFont().deriveFont(50f));
		if(gameOver) {
			x = leftX;
			y = topY + 320;
			g.drawString("GAME OVER", x, y);
		}
		
	}
	
	public String toStringScore() {
		if(score >= 100000) {
			return Integer.toString(score);
		} else if (score >= 10000) {
			return "0" + score;
		} else if (score >= 1000) {
			return "00" + score;
		} else if (score >= 100) {
			return "000" + score;
		} else if (score >= 10) {
			return "0000" + score;
		} else if (score >= 1) {
			return "00000" + score;
		} else if (score == 0) {
			return "000000";
		}
		return "999999";
	}
	
	public void reset () throws Exception {
		Thread.sleep(3000);
		gameOver = false;
		staticBlocks.clear();
		shuffleBag();
		level = 0;
		currentPiece = pick();
		currentPiece.setXY(Xi, Yi);
		next = pick();
		next.setXY(nX, nY);
		holdPiece = pick();
		holdPiece.setXY(hX, hY);
		score = 0;
		levelClears = 0;
		dropInterval = intervals[level];
		started = false;
	}
	
	public void setLevel(int level) {
		GameManager.level = level;
		dropInterval = intervals[level];
	}
	
	public static Color getColorPalette(int piece) {
		
		return palettes[level%9][piece];
	}
}
