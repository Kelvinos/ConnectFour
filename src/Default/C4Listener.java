package Default;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class C4Listener implements MouseListener {
	C4Main main;
	C4Check check;
	C4Ai ai;

	// make all components
	public C4Listener(C4Main main, C4Check check, C4Ai ai) {
		this.main = main;
		this.check = check;
		this.ai = ai;
		main.addListener(this);
	}

	// check for mouse clicking action
	public void mouseClicked(MouseEvent e) {

		JLabel squareOnGrid = (JLabel) e.getComponent();
		int r = main.getRow(squareOnGrid);
		int c = main.getColumn(squareOnGrid);

		if (check.hasWon() != true) {

			// System.out.println("Clicking at: " + r + ", " + c + "");
			r = check.checkBelow(c);

			if (r != -1) {
				
				if (main.currentPlayer == 1) {

					check.setPlayer1(r, c);
					main.set(r, c);
					check.storeGame(r, c);

					if (check.hasWon() == false
							&& main.gameType.equals("Computer")) {
						ai.giveMove(r, c, check);
					}
					
				} else {
					check.setPlayer2(r, c);
					main.set(r, c);
					check.storeGame(r, c);
				}
				
			} else {
				
				if (check.storeGame[41][0]!=-1) {
					
						JOptionPane.showMessageDialog(null, "Game is drawn! restart to play again.", " Game Draw!",
								JOptionPane.PLAIN_MESSAGE);
					
				}
				
				else {
					System.out.println("Invalid move");
					JOptionPane.showMessageDialog(null, "Try somewhere else!", "Invalid move",
							JOptionPane.PLAIN_MESSAGE);
				}

			}
		}

		// check to see if theres a win
		check.checkWin();
		check.showGrid();
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

}
