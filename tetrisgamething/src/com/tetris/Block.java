package com.tetris;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x, y;
	public static final int SIZE = 30;
	public Color c;
	
	public Block (Color c) {
		this.c = c;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.fillRect(x+1, y+1, SIZE-2, SIZE-2);
	}
}
