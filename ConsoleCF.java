// PIC 20A Homework 4, ConsoleCF.java

// Purpose: Create a command-line 
// implementation of the Connect Four game. 

// Author: Asmita Majumder
// Date: 2020/11/30

package hw4; 

import java.util.Scanner; 

public class ConsoleCF extends CFGame {
	
	private CFPlayer redPlayer; 
	private CFPlayer blackPlayer;
	
	// CONSTRUCTORS
	
	// Sets up a human vs. AI game. Red player is randomly decided. 
	public ConsoleCF(CFPlayer ai) {
		CFPlayer human = new HumanPlayer(); 
		int randomInt = (int) (Math.random() * 2);
		if (randomInt == 1) { 
			redPlayer = ai; 
			blackPlayer = human;  
		}
		else {
			redPlayer = human; 
			blackPlayer = ai; 
		}
	}
	
	// Sets up an AI vs. AI game. Red player is randomly decided. 
	public ConsoleCF(CFPlayer ai1, CFPlayer ai2) {
		int randomInt = (int) (Math.random() * 2);
		if (randomInt == 1) {
			redPlayer = ai1; 
			blackPlayer = ai2; 
		}
		else {
			redPlayer = ai2; 
			blackPlayer = ai1; 
		}
	}
	
	// PUBLIC METHODS
	
	// Plays the game until the game is over. 
	public void playOut() {
		while(!this.isGameOver()) {  
			boolean validPlay = false; 
			while(!validPlay) {
				int nextColumn = redPlayer.nextMove(this); 
				validPlay = this.play(nextColumn); 
			}
			
			if (this.isGameOver()) { break; }
			
			validPlay = false; 
			while (!validPlay) {
				int nextColumn = blackPlayer.nextMove(this);			
				validPlay = this.play(nextColumn);  
			}
			
		}
		
		System.out.println("This game has now ended. The winner is " + this.getWinner() + "."); 
		
	}
	
	// Returns name of winner (or "draw" if game ends without winner). 
	public String getWinner() {
		if (this.winner() == 1) {
			return redPlayer.getName(); 
		}
		else if (this.winner() == -1) {
			return blackPlayer.getName(); 
		}
		else {
			return "draw"; 
		}
	}
	
	// PRIVATE METHODS + CLASSES
	
	// Converts an int on our CF board to the appropriate char. 
	// This is used when printing the state of the board. 
	private static char intToCFChar (int i) {
		if (i == 1) { return 'R'; }
		if (i == -1) { return 'B'; }
		return 'x'; 
	}
	
	// Creates a class for a human player to play Connect Four. 
	private class HumanPlayer implements CFPlayer {
		
		// Returns the column that the human player inputs. 
		// If invalid, the human is prompted for another input. 
		public int nextMove(CFGame g) {
			boolean valid = false; 
			int nextMove = 0; 
			int[][] currentState = g.getState();
			
			// Prints the state of the board to the command line. 
			for (int j=5; j>=0; j--) {
				System.out.println(ConsoleCF.intToCFChar(currentState[0][j]) + " " + ConsoleCF.intToCFChar(currentState[1][j]) + " " + ConsoleCF.intToCFChar(currentState[2][j]) + " " + 
					ConsoleCF.intToCFChar(currentState[3][j]) + " " + ConsoleCF.intToCFChar(currentState[4][j]) + " " + ConsoleCF.intToCFChar(currentState[5][j]) + " " + ConsoleCF.intToCFChar(currentState[6][j]));  
			} 
			
			// Prompts human player for a column. 
			// Asks human player to try again if desired column is not playable. 
			while (!valid) {
				
				System.out.println("Please enter the column you wish to play."); 
				Scanner cin = new Scanner(System.in);
				nextMove = cin.nextInt(); 
				valid = true; 
				
				if (nextMove < 1 || nextMove > 7) {
					System.out.println("Invalid move input. Try again."); 
					valid = false; 
				}
				
				currentState = g.getState();  
				if (currentState[nextMove - 1][5] != 0) {
					System.out.println("Invalid move input. Try again.");
					valid = false; 
				}
			}
			
			return nextMove; 
		}
		
		// Returns the name of the player (Human Player). 
		public String getName() {
			return "Human Player"; 
		}
	}
	
}