// PIC 20A Homework 4, GUICF.java

// Purpose: Create a graphical implementation 
// of the Connect Four game. 

// Author: Asmita Majumder
// Date: 2020/11/30

package hw4; 

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;


public class GUICF extends CFGame {
	
	private GameBoard this_board;
	
	private CFPlayer redPlayer; 
	private CFPlayer blackPlayer;
	
	private int columnToPlay; 
	private boolean buttonClick = false;
	private String gameWinner; 
	private int playButtonTrack = -1; 
	
	// CONSTRUCTORS
	
	// Sets up and starts a human vs. AI game. 
	// Red player is randomly decided. 
	public GUICF(CFPlayer ai) {
		
		this_board = new GameBoard(); 
		(this_board.headerPanel).setLayout(new GridLayout(1, 7)); 
		
		// Adding column buttons to GUI: 
		JButton[] columnButtons = new JButton[7]; 
		for (int i = 0; i<7; i++) {
			columnButtons[i] = new JButton("\u2193"); 
			(this_board.headerPanel).add(columnButtons[i]);
		}
		
		(this_board).frame.revalidate(); 
		
		columnButtons[0].addActionListener(new ColumnOne()); 
		columnButtons[1].addActionListener(new ColumnTwo()); 
		columnButtons[2].addActionListener(new ColumnThree()); 
		columnButtons[3].addActionListener(new ColumnFour()); 
		columnButtons[4].addActionListener(new ColumnFive()); 
		columnButtons[5].addActionListener(new ColumnSix()); 
		columnButtons[6].addActionListener(new ColumnSeven()); 
		 
		// Picking whether AI or human goes first: 
		int randomInt = (int) (Math.random() * 2);
		if (randomInt == 1) { 
			this.playGUI(ai.nextMove(this)); 
		}
		 
		// Playing out game: 
		while (!this.isGameOver()) {
			if (buttonClick) { 
				while(!this.playGUI(columnToPlay)) {} 
				if (this.isGameOver()) { break; } 
				while(!this.playGUI(ai.nextMove(this))) {}  
				buttonClick = false; 
			}
		}
		
		// Displaying winner: 
		if (this.winner() == 1) {
			if (randomInt == 1) { gameWinner = ai.getName(); }
			else { gameWinner = "Human Player"; }
		}
		else if (this.winner() == -1) {
			if (randomInt == 1) { gameWinner = "Human Player"; }
			else { gameWinner = ai.getName(); }
		}
		else {
			gameWinner = "Draw"; 
		}
		
		for (int i = 0; i<7; i++) {
			(this_board.headerPanel).remove(columnButtons[i]);
		}
		(this_board.headerPanel).setLayout(new GridLayout(1, 1)); 
		(this_board).headerPanel.add(new JLabel("This game has now ended. The winner is " + gameWinner + ".")); 
		(this_board).frame.revalidate(); 
		
	}
	
	// Sets up and starts an AI vs. AI game. 
	// Red player is randomly decided. 
	public GUICF(CFPlayer ai1, CFPlayer ai2) {
		
		this_board = new GameBoard(); 
		
		// Adding "Play" button to GUI: 
		JButton playButton = new JButton("Play"); 
		playButton.addActionListener(new playButton()); 
		(this_board.headerPanel).add(playButton);
		(this_board).frame.revalidate(); 		
		
		// Picking which AI goes first: 
		int randomInt = (int) (Math.random() * 2);
		if (randomInt == 1) {
			redPlayer = ai1; 
			blackPlayer = ai2; 
		}
		else {
			redPlayer = ai2; 
			blackPlayer = ai1; 
		}
		
		// Playing out game: 
		while (!this.isGameOver()) {
			if (buttonClick) {
				
				if (playButtonTrack == 1) { 
					while(!this.playGUI(redPlayer.nextMove(this))) {}
				}
				else {
					while(!this.playGUI(blackPlayer.nextMove(this))) {}
				}
				
				buttonClick = false; 
			}
			
		}
		
		// Displaying winner: 
		if (this.winner() == 1) {
			gameWinner = redPlayer.getName(); 
		}
		else if (this.winner() == -1) {
			gameWinner = blackPlayer.getName(); 
		}
		else {
			gameWinner = "Draw"; 
		}
		(this_board.headerPanel).remove(playButton);
		(this_board).headerPanel.add(new JLabel("This game has now ended. The winner is " + gameWinner + ".")); 
		(this_board).frame.revalidate(); 

	}
	
	// PRIVATE CLASSES + METHODS
	
	// A class that represents the game board. 
	private class GameBoard extends javax.swing.JPanel {

		private JFrame frame; 
		private JLabel[][] labelArray = new JLabel[7][6]; 
		private JPanel headerPanel; 
		
		// Sets up an empty board. 
		private GameBoard() {
			frame = new JFrame("Connect Four");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			frame.setSize(700, 600); 
			
			JPanel connectFourGame = new JPanel(); 
			connectFourGame.setLayout(new GridLayout(6, 7)); 
			
			for (int j = 5; j>=0; j--) {
				for (int i = 0; i<7; i++) {
					labelArray[i][j] = new JLabel(); 
					(labelArray[i][j]).setOpaque(true); 
					(labelArray[i][j]).setBorder(BorderFactory.createLineBorder(Color.black)); 
					connectFourGame.add(labelArray[i][j]); 
				}
			}
			
			headerPanel = new JPanel(); // this is where buttons will go
			frame.getContentPane().add(headerPanel, BorderLayout.NORTH); 
			frame.getContentPane().add(connectFourGame); 
			
			frame.setVisible(true); 
		}
		
		// Paints specificed coordinate red or black. 
		// Let x = column, y = row. 
		private void paint(int x, int y, int color) {
			if (color == 1) {(labelArray[x-1][y-1]).setBackground(Color.RED); } 
			if (color == -1) {(labelArray[x-1][y-1]).setBackground(Color.BLACK); }
			
		}
	}

	private class ColumnOne implements ActionListener {
		public void actionPerformed (ActionEvent e) { 
			columnToPlay = 1; 	
			buttonClick = true;			
		}
	}
	private class ColumnTwo implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			columnToPlay = 2; 
			buttonClick = true;
		}
	}
	private class ColumnThree implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			columnToPlay = 3; 	
			buttonClick = true;
		}
	}
	private class ColumnFour implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			columnToPlay = 4; 	
			buttonClick = true;
		}
	}
	private class ColumnFive implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			columnToPlay = 5; 	
			buttonClick = true;
		}
	}
	private class ColumnSix implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			columnToPlay = 6; 	 	
			buttonClick = true;
		}
	}
	private class ColumnSeven implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			columnToPlay = 7; 	 
			buttonClick = true;
		}
	}
	private class playButton implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			playButtonTrack = -(playButtonTrack);
			buttonClick = true; 			
		}
	}
	
	// Plays a given column. 
	// This involves updating both the internal game logic
	// and the visual representation of the CF board. 
	private boolean playGUI(int c) {
		
		int color; 
		boolean redTurn = this.isRedTurn(); 
		if (redTurn) { color = 1; }
		else { color = -1; }
		
		boolean successfulPlay = this.play(c); 
		
		if (successfulPlay) {
			
			int[][] currentState = this.getState(); 
			int toColor = 0; 
			for (int i=0; i<6; i++) {
				if (currentState[c-1][i] != 0) { toColor = i; }
			}
			this_board.paint(c, toColor + 1, color); 

			return true; 
		}
		else { return false; }

	}
	
}