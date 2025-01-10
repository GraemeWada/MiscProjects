package guesser;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameFunctions {
	static Scanner s = new Scanner(System.in);
	
	public static int checkGuess(int guess, int max, int ans) {
		if(guess == ans) {
			return 1;
		}
		int diff = Math.abs(guess-ans);
		int hotRange = 2;
		int warmRange = (int) (max*0.3);
		if(diff<=hotRange) {
			return 2;
		} else if (diff<=warmRange) {
			return 3;
		} else {
			return 4;
		}
	}
	
	public static void game(int difficulty) {
		int maximum = difficulty * 10; int number = (int) (Math.random()*(maximum)+1); int guesses = 0 ;int allowedGuesses = 8; int correct;
		while (guesses < allowedGuesses) {
			try {
				System.out.println("Guess a number between 1 and "+maximum+". Guesses left: "+(allowedGuesses-guesses));
				int guess = s.nextInt();
				if(guess>0&&guess<=maximum) {
					guesses++;
					correct = checkGuess(guess, maximum, number);
					switch (correct) {
					case 1:
						System.out.println("You win!\n");
						return;
					case 2:
						System.out.println("Hot!");
						break;
					case 3: 
						System.out.println("Warm!");
						break;
					case 4:
						System.out.println("Cold!");
						break;
					}
				} else {
					System.out.println("Please enter a number within the range.");
					s.nextLine();
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				s.nextLine();
			}
		}
		System.out.println("Out of guesses! The number was: "+number);
	}
	
	public static int start() { 
		int input = -1;
		System.out.println("Enter 1 for Easy Difficulty,\n2 for Medium Difficulty,\n3 for Hard Difficulty, or\n0 to exit the program.");
		while (input == -1) {
			try {
				input = s.nextInt();
				if(input >= 0 && input < 4) {
					continue;
				} else {
					System.out.println("Please enter 1, 2, 3, or 0.");
					input = -1;
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				s.nextLine();
				input = -1;
			}
		}
		return input;
	}
}