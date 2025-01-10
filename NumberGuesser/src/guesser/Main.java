package guesser;

public class Main {
	public static void main(String[] args) {
		System.out.println("----------------------NUMBER GUESSER----------------------\n");
		while(true) {
			int userChoice = GameFunctions.start();
			if(userChoice == 0) {
				System.out.println("Exiting Number Guesser...");
				System.exit(0);
			} else {
				GameFunctions.game(userChoice);
			}
		}
	}
}