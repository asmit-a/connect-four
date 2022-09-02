// PIC 20A Homework 4, RandomAI.java

// Purpose: Create an AI that chooses
// a column to play randomly. 

// Author: Asmita Majumder
// Date: 2020/11/30

package hw4; 

public class RandomAI implements CFPlayer { 
	
	// Returns, but does not play, a random legal column. 
	public int nextMove(CFGame g) {
		int randomColumn = (int) ((Math.random() * 7) + 1); // generates random int between 1 and 7
		return randomColumn; 
	}
	
	// Returns the name of the AI (Random Player). 
	public String getName() { // check modifiers, as per usual
		return "Random Player"; 
	}
}