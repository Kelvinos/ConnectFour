package Default;

import java.util.Random;

public class C4Ai {

	private C4Check check;
	private Random random;
	private boolean movePlaced = false;

	public C4Ai(C4Check check) {
		this.check = check;
	}

	// get last moved played
	public void giveMove(int r, int c, C4Check check) {
		setMove();
	}

	// Check for the best available space to play
	// Whether it be to block a winning move, or play a winning move, or random
	public void setMove() {

		checkNextTurnWin();
		checkTrapWin();
		checkWinAbove();
		setRandomPiece();
		movePlaced = false;

	}

	// If a win can be obtained in the next turn whether it be the player or computer
	// - then block or play in that selected place
	public void checkNextTurnWin() {

		// The current game grid:
		int[][] grid = check.grid;
		
		int currentColumn = 0;
		int currentRow = 0;

		for (int columnToCheck = 0; columnToCheck < 7; columnToCheck++) {

			currentColumn = columnToCheck;
			currentRow = check.checkBelow(currentColumn);

			// If a move is not yet played
			if (movePlaced == false) {
				
				// If the row
				if (currentRow != -1) {
					grid[currentRow][currentColumn] = 1;

					/*
					 * Note that the check for computer win is before
					 * check for player win. This ensures that if there's a point
					 * where both player and computer can win in the next turn and that
					 * it's the computer's turn, that the computer takes the win first.
					 */
					
					// if computer is about to win (next turn), play there
					if (movePlaced == false) {
						if (currentRow != -1) {
							grid[currentRow][currentColumn] = 2;
							if (check.hasWon() == true) {
								check.setPlayer2(currentRow, currentColumn);
								check.storeGame(currentRow, currentColumn);
								check.main.set(currentRow, currentColumn);
								
								// Move has been played
								movePlaced = true;
							}
						}

					}
					
					// if player is about to win (next turn), block them
					if (check.hasWon() == true) {
						check.setPlayer2(currentRow, currentColumn);
						check.storeGame(currentRow, currentColumn);
						check.main.set(currentRow, currentColumn);
						
						// Move has been played
						movePlaced = true;
					}

					// else reset testing on grid
					if (movePlaced == false) {
						grid[currentRow][currentColumn] = 0;
					}

				}
			}
		}
	}

	// If the computer plays a move does it allow the user to win from it?
	// If so then don't play in that position
	public void checkTrapWin() {

		/* check trap wins work by seeing if there is a place the computer
		 * can go to prevent them automatically loosing - this can be
		 * done by getting two potential wins with one move so the computer
		 * most block this in order to stay in the game
		 */
		
		// win count checks to see if the user can win in 2 positions at once
		int winCount = 0;
		
		int r1 = 0; int c1 = 0;
		int r2 = 0; int c2 = 0;
		
		// the current game grid
		int[][] grid = check.grid;

		if (movePlaced == false) {
			// place imaginary opponent counter at r,c
			for (int i = 0; i < 7; i++) {
				c1 = i;
				r1 = check.checkBelow(c1);
				if (r1 != -1) {

					if (movePlaced == false) {

						grid[r1][c1] = 1;
						// place another imaginary counter at r,c and check for
						// wins
						for (int j = 0; j < 7; j++) {
							c2 = j;
							r2 = check.checkBelow(c2);

							if (movePlaced == false) {

								if (r2 != -1) {
									grid[r2][c2] = 1;
									if (check.hasWon()) {
										winCount++;
									}
									grid[r2][c2] = 0;
								}
							}
						}

						// if theres a trap win block it before it
						// happens
						if (winCount >= 2) {

							// block the move that triggered it
							check.setPlayer2(r1, c1);
							check.storeGame(r1, c1);
							check.main.set(r1, c1);
							movePlaced = true;
						}

						winCount = 0;

					}

					if (movePlaced == false) {
						grid[r1][c1] = 0;
					}
				}
			}

		}

	}

	public void checkWinAbove() {

		int r = 0;
		int r2 = 0;
		int c = 0;
		int[][] grid = check.grid;

		// check if playing piecce at ranom that next piece can't be a win
		// play random place

		for (int i = 0; i < 7; i++) {
			if (movePlaced == false) {

				c = i;
				r = check.checkBelow(c);

				if (r != -1) {
					grid[r][c] = 1;

					// check if player can win above
					r2 = check.checkBelow(c);
					
					if (r != -1) {
						if (check.hasWon() == true) {
							check.setPlayer1(r, c+1);
							check.storeGame(r, c+1);
							check.main.set(r, c+1);
							movePlaced = true;
						}
						
					}

				}

			}
		}
	}

	public void setRandomPiece() {

		// play random place
		if (movePlaced == false) {
			
			// get a new random number for the column
			random = new Random();
			int randomC = random.nextInt(7);
			
			// get the next available row in that column
			int randomR = check.checkBelow(randomC);

			// If the row isn't full then play at that position
			if (randomR != -1) {
				check.setPlayer2(randomR, randomC);
				check.storeGame(randomR, randomC);
				check.main.set(randomR, randomC);
			} else {
				// If the current row is full - get a new random piece
				setMove();
			}

		}

	}

}
