package com.tetris;

import java.awt.Color;

public class Square extends Piece {
	public Square() {
		Color pieceColor = GameManager.getColorPalette(3);
		create(pieceColor);
		pieceType = 3;
	}
	
	public void setXY(int x, int y) {
		b[0].x = x;
		b[0].y = y;
		b[1].x = b[0].x;
		b[1].y = b[0].y - Block.SIZE;
		b[2].x = b[0].x + Block.SIZE;
		b[2].y = b[0].y;
		b[3].x = b[0].x + Block.SIZE;
		b[3].y = b[0].y - Block.SIZE;
	}
	public void getDir1() {
		//1
		//0
		//23
		tempB[0].x = b[0].x;
		tempB[0].y = b[0].y;
		tempB[1].x = b[0].x;
		tempB[1].y = b[0].y - Block.SIZE;
		tempB[2].x = b[0].x + Block.SIZE;
		tempB[2].y = b[0].y;
		tempB[3].x = b[0].x + Block.SIZE;
		tempB[3].y = b[0].y - Block.SIZE;
		
		updateXY(1);
	}
	public void getDir2() {
		getDir1();
	}
	public void getDir3() {
		getDir1();
	}
	public void getDir4() {
		getDir1();
	}
}
