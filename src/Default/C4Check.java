package Default;

import javax.swing.JOptionPane;

public class C4Check {
	public int[][] grid;
	public int[][] storeGame;
	C4Main main;

	// make all components in constructors
	public C4Check(C4Main main) {
		
		this.main = main;
		
		// Make a new array for storing game play
		storeGame = new int[43][2];
		
		// Make a new grid for storing moves being played
		grid = new int[6][7];

		// Set all positions in storeGrid and grid empty
		for (int row = 0; row < 43; row++) {
			for (int column = 0; column < 2; column++) {
				storeGame[row][column] = -1;
			}
		}
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				grid[row][column] = 0;
			}
		}
	}

	// check to see if a piece is played below
	public int checkBelow(int columnSelected) {
		
		for (int row = 5; row >= 0; row--) {
			if (grid[row][columnSelected] == 0) {
				return row;
			}
			if (row == 0) {
				return -1;
			}
		}
		return 5;
	}

	// store where a piece is played (player 1)
	public void setPlayer1(int rowSelected, int columnSelected) {
		
		// Set Piece:
		grid[rowSelected][columnSelected] = 1;

	}

	// store where a piece is played (player 2)
	public void setPlayer2(int rowSelected, int columnSelected) {
		
		// Set Piece:
		grid[rowSelected][columnSelected] = 2;

	}

	// store game play
	public void storeGame(int rowPlayed, int columnPlayed) {
		boolean findEmptySlot = false;

		for (int storeGamePos = 0; storeGamePos < 42; storeGamePos++) {
			
			// Haven't yet stored a piece:
			if (findEmptySlot == false) {
				
				// If storeGamePos is empty then store here
				if (storeGame[storeGamePos][0] == -1) {
					storeGame[storeGamePos][0] = rowPlayed;
					storeGame[storeGamePos][1] = columnPlayed;
					findEmptySlot = true;
				}
				
			}
		}
		
		// If all positions in storeGame are full all spaces are taken - return a draw
		if (storeGame[41][0] != -1) {
			JOptionPane.showMessageDialog(null,
					"Game is drawn! restart to play again.", " Game Draw!",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	// undo move
	public void undoMove() {
		main.undoMove(storeGame, grid);
	}

	// refresh images
	public void resetImages() {
		main.resetImages(storeGame, grid);
	}

	// reset board
	public void resetBoard() {
		
		// Set grid values all back to empty
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				grid[row][column] = 0;
			}
		}
		
		// Set storeGame values all back to empty
		for (int row = 0; row < 43; row++) {
			for (int column = 0; column < 2; column++) {
				storeGame[row][column] = -1;
			}
		}

		// Print out a message to alert the user the board is reset
		JOptionPane.showMessageDialog(null, "Board has been reset!",
				"Reset board", JOptionPane.PLAIN_MESSAGE);

	}
	

	// Check for a win within the game
	public void checkWin() {
		
		if (hasWon() == true) {

			showGrid();

			// Since the player is incremented before a win is checked - if there is a win
			// then there must be switch back to who actually won:
			main.currentPlayer = (main.currentPlayer % 2) + 1;

			// Print out a win message with who has won:
			JOptionPane.showMessageDialog(null, "Player " + main.getPlayer()
					+ " Wins the game!", " Game won!",
					JOptionPane.PLAIN_MESSAGE);

			// Switch the current player back incase of undo is clicked
			main.currentPlayer = (main.currentPlayer % 2) + 1;

		}
	}

	// Check win function which checks all posibilities
	public boolean hasWon() {

		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				if (grid[row][column] == 1 || grid[row][column] == 2) {

					// check all ways, horizontal, vertical and both diagonals
					boolean hWin = checkForHorizontalWin(row, column);
					boolean vWin = checkForVerticalWin(row, column);
					boolean pWin = checkForPositiveDiagonalWin(row, column);
					boolean nWin = checkForNegativeDiagonalWin(row, column);

					// if any of these functions return true then this method should
					// also return true
					if (hWin == true || vWin == true || pWin == true
							|| nWin == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// Check for horizontal win
	public boolean checkForHorizontalWin(int row, int column) {
		try {
			
			// Check if two pieces next to each other on a horizontal plane are the same
			if (grid[row][column] == grid[row][column + 1]) {
				
				// Check if the next two along are also the same
				if (grid[row][column + 1] == grid[row][column + 2]) {
					
					// Check if the next two along are also the same
					if (grid[row][column + 2] == grid[row][column + 3]) {
						
						// There must be four in a row - return true
						return true;
					}
				}
			}
		} 
		
		// Catch any array out of bound errors
		catch (IndexOutOfBoundsException e) {
		}
		return false;
	}

	// Check for vertical win
	public boolean checkForVerticalWin(int row, int column) {
		try {
			
			// Check if two pieces next to each other on a vertical plane are the same
			if (grid[row][column] == grid[row - 1][column]) {
				
				// Check if the next two along are also the same
				if (grid[row - 1][column] == grid[row - 2][column]) {
					
					// Check if the next two along are also the same
					if (grid[row - 2][column] == grid[row - 3][column]) {
						
						// There must be four in a row - return true
						return true;
					}
				}
			}
		} 
		
		// Catch any array out of bound errors
		catch (IndexOutOfBoundsException e) {
		}
		return false;
	}

	// Check for positive diagonal win
	public boolean checkForPositiveDiagonalWin(int row, int column) {
		try {
			
			// Check if two pieces next to each other on a positive diagonal plane are the same
			if (grid[row][column] == grid[row - 1][column + 1]) {
				
				// Check if the next two along are also the same
				if (grid[row - 1][column + 1] == grid[row - 2][column + 2]) {
					
					// Check if the next two along are also the same
					if (grid[row - 2][column + 2] == grid[row - 3][column + 3]) {
						
						// There must be four in a row - return true
						return true;
					}
				}
			}
		} 
		
		// Catch any array out of bound errors
		catch (IndexOutOfBoundsException e) {
		}
		return false;
	}

	// Check for negative diagonal win
	public boolean checkForNegativeDiagonalWin(int row, int column) {
		try {
			
			// Check if two pieces next to each other on a negative diagonal plane are the same
			if (grid[row][column] == grid[row - 1][column - 1]) {
				
				// Check if the next two along are also the same
				if (grid[row - 1][column - 1] == grid[row - 2][column - 2]) {
					
					// Check if the next two along are also the same
					if (grid[row - 2][column - 2] == grid[row - 3][column - 3]) {
						
						// There must be four in a row - return true
						return true;
					}
				}
			}
		} 
		
		// Catch any array out of bound errors
		catch (IndexOutOfBoundsException e) {
		}
		return false;
	}


	
	
	
	
// show game played
public void showGame() {
	for (int i = 0; i < 42; i++) {
		if (storeGame[i][0] != -1) {
			System.out.print("Play " + (i + 1) + " at: " + storeGame[i][0]
					+ "," + storeGame[i][1]);
			System.out.println("");
		}
	}
}

public void showGrid() {
	for (int row = 0; row < 6; row++) {
		for (int column = 0; column < 7; column++) {
			System.out.print(grid[row][column] + " ");
		}
		System.out.println();
	}
}

}