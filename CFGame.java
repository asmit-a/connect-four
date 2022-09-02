// PIC 20A Homework 4, CFGame.java

// Purpose: Provide the game logic 
// for a Connect Four game. 

// Author: Asmita Majumder
// Date: 2020/11/30

package hw4;

public class CFGame {
  //state[i][j]= 0 means the i,j slot is empty
  //state[i][j]= 1 means the i,j slot has red
  //state[i][j]=-1 means the i,j slot has black
  private final int[][] state;
  private boolean isRedTurn;
  
  private int winner; 
  private int lastOccupiedIndex; 
  
  // Constructing an empty Connect Four board
  { 
    state = new int[7][6];
    for (int i=0; i<7; i++)
      for (int j=0; j<6; j++)
        state[i][j] = 0;
    isRedTurn = true; // red goes first
  }
    
	// PUBLIC METHODS 
	
	// Retrieves the current state of the CF board. 
	public int[][] getState() {
		int[][] ret_arr = new int[7][6];
		for (int i=0; i<7; i++)
			for (int j=0; j<6; j++)
				ret_arr[i][j] = state[i][j];
		return ret_arr;
	}
  
	// Indicates whether it is the red player's turn. 
	public boolean isRedTurn() {
		return isRedTurn;
	}
  
	// Plays a given column. 
	public boolean play(int column) {  
		if (column < 1 || column > 7) { return false; } // accounts for invalid columns
		else { 
			lastOccupiedIndex = -1; 
			int playColor;

			if (isRedTurn) { playColor = 1; }
			else { playColor = -1; }
			
			int j = 0; 
			while (j<6) {
				lastOccupiedIndex = j - 1; 
				if (state[column - 1][j] == 0) { break; }
				j++; 
				lastOccupiedIndex++;
			}
			
			if (lastOccupiedIndex == 5) { // accounts for full columns
				return false; 
			} 
			else {
				state[column - 1][lastOccupiedIndex + 1] = playColor; // updates the slot we wish to fill
				isRedTurn = !isRedTurn; // switches turns
				return true; 
			}
			
		} 
	}
	  
	// Indicates whether the game is over. 
	public boolean isGameOver() {
		
		if (this.connectFourFound()) { return true; } // if winner 
		else if (this.allFull()) { return true; } // if no more possible moves
		else { return false; }
	}
  
	// Indicates the winner of the game with an int 
	// (1 for red, -1 for black, 0 for draw). 
	public int winner() {
		boolean allFull = this.allFull(); 
		boolean connectFourFound = this.connectFourFound(); // updates winner
		
		if (allFull && !connectFourFound) { winner = 0; }
		
		return winner;
	}
	
	
	// PRIVATE METHODS 
	
	// Checks if all slots in CF board are full. 
	private boolean allFull() {
		for (int i=0; i<7; i++) {
			for (int j=0; j<6; j++) {
				if (state[i][j] == 0) { return false; } 
			}
		}
		return true; 
	}
	
	// Checks if any player has achieved a Connect Four. 
	private boolean connectFourFound() {
		
		// Checking for vertical win: 
		for (int i=0; i<7; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i][j+1] && state[i][j+1] == state[i][j+2] && state[i][j+2] == state[i][j+3] && state[i][j] != 0) {
					winner = state[i][j]; 
					return true; 
				}	
			}
		}
		
		// Checking for horizontal win: 
		for (int i=0; i<4; i++) {
			for (int j=0; j<6; j++) {
				if (state[i][j] == state[i+1][j] && state[i+1][j] == state[i+2][j] && state[i+2][j] == state[i+3][j] && state[i][j] != 0) {
					winner = state[i][j]; 
					return true; 
				}
				
			}
		}
		
		// Checking for left-leaning diagonal win: 
		for (int i=3; i<7; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i-1][j+1] && state[i-1][j+1] == state[i-2][j+2] && state[i-2][j+2] == state[i-3][j+3] && state[i][j] != 0) {
					winner = state[i][j]; 
					return true; 
				}
			}
		}
		
		// Checking for right-leaning diagonal win: 
		for (int i=0; i<4; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i+1][j+1] && state[i+1][j+1] == state[i+2][j+2] && state[i+2][j+2] == state[i+3][j+3] && state[i][j] != 0) {
					winner = state[i][j]; 
					return true; 
				}
				
			}
		}
		
		return false; 	
	}
	
}