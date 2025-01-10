package com.tetris;

import java.awt.Color;
import java.awt.Graphics2D;

public class Piece {
	public Block[] b = new Block[4];
	public Block[] tempB = new Block[4];
	int ADC = 0;
	
	protected int pieceType;
	
	public int direction = 1;
	
	boolean leftCol, rightCol, bottomCol;
	
	public boolean active = true;
	boolean de;
	int dc = 0; 
	
	boolean held = false;
	
	public void create(Color c) {
		b[0] = new Block(c);
		b[1] = new Block(c);
		b[2] = new Block(c);
		b[3] = new Block(c);
		tempB[0] = new Block(c);
		tempB[1] = new Block(c);
		tempB[2] = new Block(c);
		tempB[3] = new Block(c);
	}
	
	public int getPieceType() {
		return pieceType;
	}
	
	public void setXY(int x, int y) {}
	public void updateXY(int direction) {
		checkRotCol();
		
		if(!leftCol && !rightCol && !bottomCol) {
			this.direction = direction;
			for (int i = 0; i < 4; i++) {
				b[i].x = tempB[i].x;
				b[i].y = tempB[i].y;
			}
		}
	}
	
	public void getDir1() {}
	public void getDir2() {}
	public void getDir3() {}
	public void getDir4() {}
	
	public void checkMoveCol() {
		leftCol = false;
		rightCol = false;
		bottomCol = false;
		
		checkStaticCol();
		
		for (int i = 0; i < b.length; i++) {
			if (b[i].x == GameManager.leftX) {
				leftCol = true;
			}
			if (b[i].x +Block.SIZE == GameManager.rightX) {
				rightCol = true;
			}
			if (b[i].y + Block.SIZE == GameManager.bottomY) {
				bottomCol = true;
			}
		}
		
	}
	public void checkRotCol() {
		leftCol = false;
		rightCol = false;
		bottomCol = false;
		
		checkStaticCol();
		
		for (int i = 0; i < b.length; i++) {
			if (tempB[i].x < GameManager.leftX) {
				leftCol = true;
			}
			if (tempB[i].x +Block.SIZE > GameManager.rightX) {
				rightCol = true;
			}
			if (tempB[i].y + Block.SIZE > GameManager.bottomY) {
				bottomCol = true;
			}
		}
	}
	
	private void checkStaticCol () {
		for (int i = 0; i < GameManager.staticBlocks.size(); i++) {
			int targetX = GameManager.staticBlocks.get(i).x;
			int targetY = GameManager.staticBlocks.get(i).y;
			
			for (int j = 0; j < b.length; j++) {
				if(b[j].y + Block.SIZE == targetY && b[j].x == targetX) {
					bottomCol=true;
				}
				if(b[j].x - Block.SIZE == targetX && b[j].y == targetY) {
					leftCol=true;
				}
				if(b[j].x + Block.SIZE == targetX && b[j].y == targetY) {
					rightCol=true;
				}
			}
		}
	}
	
	public void de() {
		dc++;
		if(dc == 30) {
			dc = 0;
			checkMoveCol();
			if(bottomCol) {
				active = false;			
			}
			
		}
	}
	
	public void tick() {
		if(de) {
			de();
		}
		
		if(KeyHandler.w) { //rotate clockwise
			switch(direction) {
			case 1:
				getDir2();
				break;
			case 2:
				getDir3();
				break;
			case 3:
				getDir4();
				break;
			case 4:
				getDir1();
				break;
			}
			KeyHandler.w = false;
		}
		
		if(KeyHandler.comma) { //rotate clockwise
			switch(direction) {
			case 1:
				getDir4();
				break;
			case 2:
				getDir1();
				break;
			case 3:
				getDir2();
				break;
			case 4:
				getDir3();
				break;
			}
			KeyHandler.comma = false;
		}
		
		checkMoveCol();
		
		if(KeyHandler.a) { //move left
			if(!leftCol) {
				for (Block x : b)
					x.x -= Block.SIZE;
			}
			KeyHandler.a = false;
		}
		if(KeyHandler.s) { //soft drop
			if(!bottomCol) {
				for (Block x : b)
					x.y += Block.SIZE;
				ADC = 0;
			}
			KeyHandler.s = false;
		}
		if(KeyHandler.d) { //move right
			if(!rightCol) {
				for (Block x : b)
					x.x += Block.SIZE;
			}
			KeyHandler.d = false;
		}
		if(KeyHandler.space) { //hard drop
			while(!bottomCol) {
				for (Block x : b)
					x.y += Block.SIZE;
				checkMoveCol();
			}
			active = false;
			KeyHandler.space = false;
		}
		
		if(bottomCol)
			de = true;
		else {
			ADC++;
			if (ADC == GameManager.dropInterval) {
				for (Block x : b)
					x.y += Block.SIZE;
				ADC = 0;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		
		g.setColor(b[0].c);
		g.fillRect(b[0].x+1, b[0].y+1, Block.SIZE-2, Block.SIZE-2);
		g.fillRect(b[1].x+1, b[1].y+1, Block.SIZE-2, Block.SIZE-2);
		g.fillRect(b[2].x+1, b[2].y+1, Block.SIZE-2, Block.SIZE-2);
		g.fillRect(b[3].x+1, b[3].y+1, Block.SIZE-2, Block.SIZE-2);
	}
}
