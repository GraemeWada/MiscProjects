package com.tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	static boolean w, a, s, d, space, comma, slash, pause, hold, start, plus, minus;

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP || code == KeyEvent.VK_PERIOD || code == KeyEvent.VK_X)
			w = true;
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
			a = true;
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
			s = true;
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
			d = true;
		if (code == KeyEvent.VK_SPACE)
			space = true;
		if(code == KeyEvent.VK_P)
			pause = !pause;
		if(code == KeyEvent.VK_COMMA || code == KeyEvent.VK_Z)
			comma = true;
		if(code == KeyEvent.VK_C || code == KeyEvent.VK_SHIFT)
			hold = true;
		if(code == KeyEvent.VK_ENTER)
			start = true;
		if(code == KeyEvent.VK_EQUALS)
			plus = true;
		if(code == KeyEvent.VK_MINUS)
			minus = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		int code = e.getKeyCode();
//				
//		if (code == KeyEvent.VK_W)
//			w = false;
//		if (code == KeyEvent.VK_A)
//			a = false;
//		if (code == KeyEvent.VK_S)
//			s = false;
//		if (code == KeyEvent.VK_D)
//			d = false;
//		if (code == KeyEvent.VK_SPACE)
//			space = false;
	}

}
