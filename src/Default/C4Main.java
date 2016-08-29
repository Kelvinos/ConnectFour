package Default;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class C4Main extends JPanel implements ActionListener {
	
	// Game window
	private JFrame connectFourWindow;
	
	// Game Panel
	public JPanel connectFourPane;
	
	// Labels for game panel
	private JLabel[][] connectFourGrid;

	// Current player
	public int currentPlayer = 1;

	// Current piece colour for player 1 and 2
	public String pieceColourPlayer1 = "Red";
	public String pieceColourPlayer2 = "Blue";
	
	// Current board colour
	public String boardColour = "Blue";
	
	// Current piece type for player 1 and 2
	public String pieceTypePlayer1 = "Circle";
	public String pieceTypePlayer2 = "Circle";
	
	// Current game type
	public String gameType = "Player";

	// button pics
	// All are set to null so they can be loaded by a method
	public ImageIcon undoPic = null;
	public ImageIcon optionsPic = null;
	public ImageIcon exitPic = null;
	public ImageIcon resetPic = null;
	public ImageIcon helpPic = null;	

	// Rollover pics
	// All are set to null so they can be loaded by a method
	private ImageIcon undoPicRollover = null;
	private ImageIcon helpPicRollover = null;
	private ImageIcon optionsPicRollover = null;
	private ImageIcon exitPicRollover = null;
	private ImageIcon resetPicRollover = null;

	// Current selections for the options chosen in the options class
	public int selection1 = 0;
	public int selection2 = 4;
	public int selection3 = 7;
	public int selection4 = 0;
	public int selection5 = 0;
	public int selection6 = 0;

	// Classes that are needed
	static C4Main main;
	static C4Check check;
	static C4Ai ai;
	static C4Sound sound;

	public static void main(String[] args) {

		sound = new C4Sound();
		main = new C4Main();
		check = new C4Check(main);
		ai = new C4Ai(check);
		C4Listener listener = new C4Listener(main, check, ai);
	}

	// make all components
	public C4Main() {

		// Make all the button and rollover images
		buttonImages();

		// Make the window
		connectFourWindow = new JFrame();

		// Main panel
		Container content = connectFourWindow.getContentPane();

		// Title panel
		JPanel titlePane = new JPanel();
		titlePane.setPreferredSize(new Dimension(300, 50));
		titlePane.setBackground(new Color(69, 89, 171));
		JLabel titleLabel = new JLabel("Connect Four");
		JLabel subTitleLabel = new JLabel(
				"Computing A2 project by Kelvin Hiorns");

		titleLabel.setFont(new Font("sans", Font.HANGING_BASELINE, 32));
		titleLabel.setForeground(new Color(255, 255, 240));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titlePane.add(titleLabel);

		// Button panel
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout());
		buttonPane.setPreferredSize(new Dimension(300, 40));
		buttonPane.setBackground(new Color(69, 89, 171));

		// Connect four grid panel
		connectFourPane = new JPanel();
		connectFourPane.setLayout(new GridLayout(6, 7));
		connectFourPane.setBackground(Color.BLUE);

		// Add panels to container
		content.add(titlePane, BorderLayout.NORTH);
		content.add(buttonPane, BorderLayout.SOUTH);
		content.add(connectFourPane, BorderLayout.CENTER);

		// create jlabels on connect four grid
		connectFourGrid = new JLabel[6][7];
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				connectFourGrid[row][column] = new JLabel();
				connectFourGrid[row][column]
						.setHorizontalAlignment(SwingConstants.CENTER);
				connectFourGrid[row][column]
						.setVerticalAlignment(SwingConstants.CENTER);

				connectFourGrid[row][column].setBorder(new LineBorder(
						Color.black, 1, true));
				
				// Add to panel
				connectFourPane.add(connectFourGrid[row][column]);
			}
		}

		// Make buttons
		JButton resetButton = new JButton(null, resetPic);
		JButton undoButton = new JButton(null, undoPic);
		JButton optionsButton = new JButton(null, optionsPic);
		JButton helpButton = new JButton(null, helpPic);
		JButton exitButton = new JButton(null, exitPic);
		
		// Make action commands
		resetButton.setActionCommand("reset");
		undoButton.setActionCommand("undo");
		optionsButton.setActionCommand("options");
		helpButton.setActionCommand("help");
		exitButton.setActionCommand("exit");
		
		// Add listeners
		resetButton.addActionListener(this);
		undoButton.addActionListener(this);
		optionsButton.addActionListener(this);
		helpButton.addActionListener(this);
		exitButton.addActionListener(this);
		
		// Add hover text
		resetButton.setToolTipText("Reset the pieces on the board");
		undoButton.setToolTipText("Undo previous moves");
		optionsButton.setToolTipText("Bring up the options panel");
		helpButton.setToolTipText("Get help about the game");
		exitButton.setToolTipText("Exit the game");
		
		// Add rollover image
		resetButton.setRolloverIcon(resetPicRollover);
		undoButton.setRolloverIcon(undoPicRollover);
		optionsButton.setRolloverIcon(optionsPicRollover);
		helpButton.setRolloverIcon(helpPicRollover);
		exitButton.setRolloverIcon(exitPicRollover);

		// 
		buttonPane.add(resetButton);
		buttonPane.add(undoButton);
		buttonPane.add(optionsButton);
		buttonPane.add(helpButton);
		buttonPane.add(exitButton);

		connectFourWindow.setSize(1000, 700); // 900 by 600
		connectFourWindow.setLocationRelativeTo(null);
		connectFourWindow.setVisible(true);
		connectFourWindow.setResizable(true);
		connectFourWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// sound.music();

	}

	// make button images
	public void buttonImages() {

		undoPic = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\Undo.png");
		optionsPic = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\Cog.png");
		exitPic = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\Exit.png");
		resetPic = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\Reset.png");
		helpPic = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\Help.png");

		// rolloever pics
		undoPicRollover = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\UndoR.png");
		helpPicRollover = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\HelpR.png");
		optionsPicRollover = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\CogR.png");
		exitPicRollover = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\ExitR.png");
		resetPicRollover = new ImageIcon(
				"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\ResetR.png");

	}

	// player turn
	public void printPlayerTurn() {
		if (currentPlayer == 1) {
			JOptionPane.showMessageDialog(null, "Player 2's turn", "Next Turn",
					JOptionPane.PLAIN_MESSAGE);
		}
		if (currentPlayer == 2) {
			JOptionPane.showMessageDialog(null, "Player 1's turn", "Next Turn",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	// refresh images on board
	public void resetImages(int[][] storeGame, int[][] grid) {
		for (int i = 0; i < 43; i++) {
			if (storeGame[i][0] != -1) {
				int r = storeGame[i][0];
				int c = storeGame[i][1];
				if (grid[r][c] == 1) {

					try {
						ClassLoader cL = this.getClass().getClassLoader();
						java.net.URL imageURL = cL.getResource("images\\"
								+ pieceColourPlayer1 + pieceTypePlayer1
								+ ".png");
						connectFourGrid[r][c].setIcon(new ImageIcon(imageURL));
					} catch (NullPointerException e) {
					}
				}
				if (grid[r][c] == 2) {

					try {
						ClassLoader cL = this.getClass().getClassLoader();
						java.net.URL imageURL = cL.getResource("images\\"
								+ pieceColourPlayer2 + pieceTypePlayer2
								+ ".png");
						connectFourGrid[r][c].setIcon(new ImageIcon(imageURL));
					} catch (NullPointerException e) {
					}
				}
			}
		}
	}

	// undo previous move
	public void undoMove(int[][] storeGame, int[][] grid) {
		int row = 0;
		int row2 = 0;
		int column = 0;
		int column2 = 0;
		boolean findLastMove = false;

		if (storeGame[0][0] == -1) {
			System.out.println("Nothing to undo ...");

			JOptionPane.showMessageDialog(null, "Nothing to undo!",
					"Undo move", JOptionPane.PLAIN_MESSAGE);

		} else {
			for (int i = 1; i < 43; i++) {
				if (findLastMove == false) {

					if (storeGame[i][0] == -1) {
						row = storeGame[(i - 1)][0];
						column = storeGame[(i - 1)][1];

						check.storeGame[(i - 1)][0] = -1;

						connectFourGrid[row][column].setIcon(null);
						grid[row][column] = 0;

						currentPlayer = (currentPlayer % 2) + 1;

						if (gameType.equals("Computer")) {

							if (storeGame[0][0] != -1) {
								row2 = storeGame[(i - 2)][0];
								column2 = storeGame[(i - 2)][1];
								check.storeGame[(i - 2)][0] = -1;

								connectFourGrid[row2][column2].setIcon(null);
								grid[row2][column2] = 0;

								currentPlayer = (currentPlayer % 2) + 1;
							}
						}

						findLastMove = true;
					}
				}
			}
		}
	}

	// reset the board
	public void resetBoard() {
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				connectFourGrid[row][column].setIcon(null);
			}
		}
		currentPlayer = 1;
		check.resetBoard();
	}

	// white lines
	public void whiteBorder() {
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				connectFourGrid[row][column].setBorder(new LineBorder(
						Color.white, 1, true));
			}
		}
	}

	// black lines
	public void blackBorder() {
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				connectFourGrid[row][column].setBorder(new LineBorder(
						Color.black, 1, true));
			}
		}
	}

	// add listeners
	public void addListener(C4Listener listener) {
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				connectFourGrid[row][column].addMouseListener(listener);
			}
		}
	}

	// returns current player
	public int getPlayer() {
		return currentPlayer;
	}

	// Return the column of a square on the grid
	public int getColumn(JLabel squareOnGrid) {
		int returnColumn = 0;
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				if (connectFourGrid[row][column] == (squareOnGrid)) {
					returnColumn = column;
				}
			}
		}
		return returnColumn;
	}

	// Return the row of a square on the grid
	public int getRow(JLabel squareOnGrid) {
		int returnRow = 0;
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 7; column++) {
				if (connectFourGrid[row][column] == (squareOnGrid)) {
					returnRow = row;
				}
			}
		}
		return returnRow;
	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// set a coloured piece of the board
	public void set(int row, int column) {

		if (currentPlayer == 1) {

			connectFourGrid[row][column].setIcon(new ImageIcon(
					"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\"
							+ pieceColourPlayer1 + pieceTypePlayer1 + ".png"));
		} else {
			connectFourGrid[row][column].setIcon(new ImageIcon(
					"E:\\Coursework\\Computing project\\\\Workspace\\Connect4\\src\\Default\\images\\"
							+ pieceColourPlayer2 + pieceTypePlayer2 + ".png",
					"hello"));

		}
		currentPlayer = (currentPlayer % 2) + 1;

	}

	// action performed by buttons etc
	public void actionPerformed(ActionEvent e) {
		// add actions to events
		if (e.getActionCommand() == ("reset")) {
			System.out.println("Reseting the game...");
			
			resetBoard();
		}
		if (e.getActionCommand() == ("undo")) {
			System.out.println("Undoing move...");
			check.undoMove();
		}
		if (e.getActionCommand() == ("options")) {
			System.out.println("Bringing up options...");
			C4Options options = new C4Options(main, check);
		}
		if (e.getActionCommand() == ("help")) {
			JOptionPane
					.showConfirmDialog(
							null,
							"Click on the grid to play a piece. First player to get four in a row wins.\nChange any appearances on the options menu.",
							"Help", JOptionPane.DEFAULT_OPTION);

		}
		if (e.getActionCommand() == ("exit")) {

			int reply = JOptionPane.showConfirmDialog(null, "Are you sure?",
					"Exit Game", JOptionPane.WARNING_MESSAGE);

			if (reply == JOptionPane.YES_OPTION) {
				System.out.println("Exiting game...");
				// sound.titleSound.stop();
				connectFourWindow.dispose();
			}
		}
	}
}