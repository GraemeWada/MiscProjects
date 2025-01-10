package com.tetris;

//import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
public class Main {

	public static void main(String[] args) {
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		 String fonts[] = ge.getAvailableFontFamilyNames();

//	        // Getting the font family names
//	        for (String i : fonts) {
//	            System.out.println(i + " ");
//	        }
		JFrame window = new JFrame("This is a game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		Panel p = new Panel();
		window.add(p);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		p.start();
	}
}
