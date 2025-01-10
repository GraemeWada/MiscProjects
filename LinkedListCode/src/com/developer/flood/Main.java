package com.developer.flood;

import com.developer.list.LinkedGrid;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
		int in;
		System.out.println("Enter 1 to play, Enter 0 to exit.");
		in = tryNextInt(0,1);
		while(in != 0){
			if(in == -1) {
				in = tryNextInt(0,1);
				continue;
			}
			game();
			s.nextLine();
			in = -1;
			System.out.println("Enter 1 to play again, Enter 0 to exit.");
		}
		s.close();
		System.exit(0);
	}
	
	public static int tryNextInt(int min, int max) {
		System.out.println("Enter a number between "+min+" and "+max);
		
		int input = Integer.MAX_VALUE;
		while (input == Integer.MAX_VALUE) {
			try {
				input = s.nextInt();
				if(input <= min-1 || input >= max+1) {
					System.out.println("Enter a number between "+min+" and "+max);
					input = Integer.MAX_VALUE;
					s.nextLine();
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a number between "+min+" and "+max);
				s.nextLine();
			}
		}
		return input;
	}
	
	public static void game() {
		Scanner s = new Scanner(System.in);
		LinkedGrid g = new LinkedGrid(60,5);
		g.display();
		
		int tries = 75;
		int input;
		boolean flooded = false;
		
		while (tries > 0) {
			input = tryNextInt(1, 6);
			tries--;
			g.search(input);
			System.out.println("Tries left: "+tries);
			g.display();
			flooded = g.checkFlood();
			if(flooded) {
				System.out.println("You win!"); break;
			}
			
//			String in1 = s.next();
//			String[] in2 = in1.split("");
//			for (String i : in2) {
//				int j = Integer.valueOf(i);
//				g.search(j);
//			}
//			g.display();
//			flooded = g.checkFlood();
//			if(flooded) {
//				System.out.println("win!"); break;
//			}
		}
		if(tries == 0 && !flooded)
			System.out.println("You lose!");
	}
}
