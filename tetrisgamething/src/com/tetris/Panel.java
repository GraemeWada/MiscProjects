package com.tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	final int FPS = 60;
	Thread t;
	GameManager gm;
	
//	boolean started = false;
	float R = 1;
	float G = 1;
	float B = 1;
	int mr = 1;
	int mg = 1;
	int mb = 1;
	int level = 0;
	
	public Panel () {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(new Color(0x102020, false));
		this.setLayout(null);
		
		this.addKeyListener(new KeyHandler());
		this.setFocusable(true);
		
		gm = new GameManager();
	}
	
	public void start() {
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		double interval = 1000000000/FPS;
		double deltaTime = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (t != null) {
			currentTime = System.nanoTime();
			deltaTime += (currentTime - lastTime) / interval;
			lastTime = currentTime;
			
			if(deltaTime >= 1) {
				try {tick();} catch (Exception e) {}
				repaint();
				deltaTime--;
			}
		}
	}
	
	//update every tick
	public void tick () throws Exception {
		if(!KeyHandler.pause && gm.started) {
			gm.tick();
		}
		if(!gm.started) {
			if(KeyHandler.start) {
				gm.started = true;
				gm.setLevel(level);
				KeyHandler.start = false;
			}
			if(KeyHandler.plus) {
				if(level < 29) {
					level ++;
				}
//				System.out.println("up");
				KeyHandler.plus = false;
			}
			if(KeyHandler.minus) {
				if(level > 0) {
					level--;
				}
//				System.out.println("down");
				KeyHandler.minus = false;
			}
		}
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		if(gm.started) {
			gm.draw(g2);
		} else {
			colourChange();
			g2.setColor(new Color(R, G, B));
			g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 100));
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.drawString("BAD TETRIS", WIDTH/2-300, HEIGHT/2-50);
			g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
			g2.drawString("Press Enter to play", WIDTH/2-210, HEIGHT/2+50);
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			g2.drawString("Press plus or minus to change start level.", 25, 25);
			g2.drawString("Start level: " + Integer.toString(level), 25, 50);
		}
		
	}
	
	public void colourChange() {
		R += 0.013 * mr;
		if( R > 1) {
			R = 1f;
			mr *= -1;
		} else if (R < 0) {
			R = 0f;
			mr *= -1;
		}
		G -= 0.009 * mg;
		if( G > 1) {
			G = 1f;
			mg *= -1;
		} else if (G < 0) {
			G = 0f;
			mg *= -1;
		}
		B += 0.011 * mb;
		if( B > 1) {
			B = 1f;
			mb *= -1;
		} else if (B < 0) {
			B = 0f;
			mb *= -1;
		}
	}
}
