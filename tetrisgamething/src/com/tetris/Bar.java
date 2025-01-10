package com.tetris;

import java.awt.Color;

public class Bar extends Piece {
	public Bar() {
		Color pieceColor = GameManager.getColorPalette(2);
		create(pieceColor);
		pieceType = 2;
	}
	
	public void setXY(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x - Block.SIZE;
		b[1].y = b[0].y;
		b[2].x = b[0].x + Block.SIZE;
		b[2].y = b[0].y;
		b[3].x = b[0].x + Block.SIZE * 2;
		b[3].y = b[0].y;
	}
	public void getDir1() {
		//1023
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x - Block.SIZE;
		tempB[1].y = b[0].y;
		tempB[2].x = b[0].x + Block.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x + Block.SIZE * 2;
		tempB[3].y = b[0].y;
		
		updateXY(1);
	}
	public void getDir2() {
		//1
		//0
		//2
		//3
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y - Block.SIZE;
		tempB[2].x = b[0].x;
		tempB[2].y = b[0].y + Block.SIZE;
		tempB[3].x = b[0].x;
		tempB[3].y = b[0].y + Block.SIZE * 2;
				
		updateXY(2);
	}
	public void getDir3() {
		getDir1();
	}
	public void getDir4() {
		getDir2();
	}
}
