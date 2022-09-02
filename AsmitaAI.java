// PIC 20A Homework 4, AsmitaAI.java

// Purpose: Create an AI that chooses a column to play. 
// This decision is made based on whether there are any moves that 
// can win the game, or block the opponent from winning. 

// Author: Asmita Majumder
// Date: 2020/11/30

package hw4; 
 
public class AsmitaAI implements CFPlayer { 
	
	// Returns, but does not play, a legal column. 
	public int nextMove(CFGame g) {
		int[][] state = g.getState();
		int myColor = 0; 
		
		if (g.isRedTurn()) { myColor = 1; }
		else { myColor = -1; }
		
		// Checking for way to win (vertically): 
		for (int i=0; i<7; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i][j+1] && state[i][j+1] == state[i][j+2] && state[i][j+3] == 0 && state[i][j] == myColor) {
					return (i + 1); 
				}	
			}
		}
		
		// Checking for way to win (horizontally): 
		for (int i=0; i<4; i++) {
			for (int j=0; j<6; j++) {
				if (state[i][j] == state[i+1][j] && state[i+1][j] == state[i+2][j] && state[i+3][j] == 0 && state[i][j] == myColor) { // invalid index?
					if (j == 0) { return (i + 3 + 1); }
					else if (state[i+3][j-1] != 0) { return (i+3 + 1); }
				}
			}
		}
		
		for (int i=1; i<4; i++) {
			for (int j=0; j<6; j++) {
				if (state[i][j] == state[i+1][j] && state[i+1][j] == state[i+2][j] && state[i-1][j] == 0 && state[i][j] == myColor && (j == 0 || state[i-1][j-1] != 0)) {
					return (i - 1 + 1); 
				}
			}
		}
		
		// Checking for way to win (diagonally, left-leaning): 
		for (int i=2; i<6; i++) {
			for (int j=1; j<3; j++) {
				if (state[i][j] == state[i-1][j+1] && state[i-1][j+1] == state[i-2][j+2] && state[i+1][j-1] == 0 && state[i][j] == myColor) { // invalid index?
					if (j - 1 == 0) { return (i + 1 + 1); }
					else if (state[i+1][j-2] != 0) { return (i + 1 + 1); } 
				}
			}
		}
		
		for (int i=3; i<7; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i-1][j+1] && state[i-1][j+1] == state[i-2][j+2] && state[i-3][j+3] == 0 && state[i][j] == myColor && state[i-3][j+2] != 0) {
					return (i - 3 + 1); 
				}
			}
		}
		
		// Checking for way to win (diagonally, right-leaning):  
		for (int i=1; i<5; i++) {
			for (int j=1; j<4; j++) {
				if (state[i][j] == state[i+1][j+1] && state[i+1][j+1] == state[i+2][j+2] && state[i-1][j-1] == 0 && state[i][j] == myColor) { // invalid index?
					if (j - 1 == 0) { return (i - 1 + 1); }
					else if (state[i-1][j-2] != 0) { return (i - 1 + 1); }
				}
			}
		}
		 
		for (int i=0; i<4; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i+1][j+1] && state[i+1][j+1] == state[i+2][j+2] && state[i+3][j+3] == 0 && state[i][j] == myColor && state[i+3][j+2] != 0) {
					return (i + 3 + 1); 
				}
			}
		}
		
		// Checking for way to block opponent from winning (vertically): 
		for (int i=0; i<7; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i][j+1] && state[i][j+1] == state[i][j+2] && state[i][j+3] == 0 && state[i][j] == -(myColor)) {
					return (i + 1); 
				}	
			}
		}
		
		// Checking for way to block opponent from winning (horizontally): 
		for (int i=0; i<4; i++) {
			for (int j=0; j<6; j++) {
				if (state[i][j] == state[i+1][j] && state[i+1][j] == state[i+2][j] && state[i+3][j] == 0 && state[i][j] == -(myColor)) { // invalid index?
					if (j == 0) { return (i + 3 + 1); }
					else if (state[i+3][j-1] != 0) { return (i + 3 + 1); }
				}
			}
		}
		
		for (int i=1; i<4; i++) {
			for (int j=0; j<6; j++) {
				if (state[i][j] == state[i+1][j] && state[i+1][j] == state[i+2][j] && state[i-1][j] == 0 && state[i][j] == -(myColor) && (j == 0 || state[i-1][j-1] != 0)) {
					return (i - 1 + 1); 
				}
			}
		}
		
		// Checking for way to block opponent from winning (diagonally, left-leaning): 
		for (int i=2; i<6; i++) {
			for (int j=1; j<3; j++) {
				if (state[i][j] == state[i-1][j+1] && state[i-1][j+1] == state[i-2][j+2] && state[i+1][j-1] == 0 && state[i][j] == -(myColor)) { // invalid index?
					if (j - 1 == 0) { return (i + 1 + 1); }
					else if (state[i+1][j-2] != 0) { return (i + 1 + 1); }
				}
			}
		}
		 
		for (int i=3; i<7; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i-1][j+1] && state[i-1][j+1] == state[i-2][j+2] && state[i-3][j+3] == 0 && state[i][j] == -(myColor) && state[i-3][j+2] != 0) {
					return (i - 3 + 1); 
				}
			}
		}
		
		// Checking for way to block opponent from winning (diagonally, right-leaning):  
		for (int i=1; i<5; i++) {
			for (int j=1; j<4; j++) {
				if (state[i][j] == state[i+1][j+1] && state[i+1][j+1] == state[i+2][j+2] && state[i-1][j-1] == 0 && state[i][j] == -(myColor)) { // invalid index?
					if (j - 1 == 0) { return (i - 1 + 1); }
					else if (state[i-1][j-2] != 0) { return (i - 1 + 1); }
				}
			}
		}
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<3; j++) {
				if (state[i][j] == state[i+1][j+1] && state[i+1][j+1] == state[i+2][j+2] && state[i+3][j+3] == 0 && state[i][j] == -(myColor) && state[i+3][j+2] != 0) {
					return (i + 3 + 1); 
				}
			}
		}
		
		// If no way to win or block an opponent's win is found, 
		// a random column between 1 and 7 is generated. 
		int randomColumn = (int) ((Math.random() * 7) + 1); 

		return randomColumn; 

	}
	
	// Returns the name of the AI (Asmita's AI). 
	public String getName() {
		return "Asmita's AI"; 
	}
	
}