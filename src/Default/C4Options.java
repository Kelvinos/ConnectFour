package Default;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class C4Options implements ActionListener {

	private JFrame optionsWindow;
	private Random randomNo;
	public String randomColor;

	private JRadioButton[] optionButton1;
	private JRadioButton[] optionButton2;
	private JRadioButton[] optionButton3;
	private JRadioButton[] optionButton4;
	private JRadioButton[] optionButton5;
	private JRadioButton[] optionButton6;

	C4Main main;
	C4Check check;

	public String previousPlayer1PieceColour;
	public String previousPlayer2PieceColour;
	public String previousBoardColour;
	public String previousPlayer1PieceType;
	public String previousPlayer2PieceType;
	public String previousGameType;

	// make all components
	public C4Options(C4Main main, C4Check check) {
		this.main = main;
		this.check = check;

		// initiate previous setting variables
		previousPlayer1PieceColour = main.pieceColourPlayer1;
		previousPlayer2PieceColour = main.pieceColourPlayer2;
		previousBoardColour = main.boardColour;
		previousPlayer1PieceType = main.pieceTypePlayer1;
		previousPlayer2PieceType = main.pieceTypePlayer2;
		previousGameType = main.gameType;

		// make the window
		optionsWindow = new JFrame("Options");
		Container content = optionsWindow.getContentPane();
		JPanel controlArea = new JPanel(new GridLayout(2, 4));
		optionButton1 = new JRadioButton[10];
		optionButton2 = new JRadioButton[10];
		optionButton3 = new JRadioButton[10];
		optionButton4 = new JRadioButton[5];
		optionButton5 = new JRadioButton[5];
		optionButton6 = new JRadioButton[2];

		// radio button options in array

		String[] pColorsPlayer1 = { "Red", "Orange", "Yellow", "Green", "Blue",
				"Purple", "Pink", "White", "Black", "Random" };
		controlArea.add(new choicePanel("Piece Colour Player 1:",
				pColorsPlayer1, optionButton1));

		String[] pColorsPlayer2 = { "Red ", "Orange ", "Yellow ", "Green ",
				"Blue ", "Purple ", "Pink ", "White ", "Black ", "Random " };
		controlArea.add(new choicePanel("Piece Colour Player 2:",
				pColorsPlayer2, optionButton2));

		String[] bColor = { "Red Board", "Orange Board", "Yellow Board",
				"Green Board", "Blue Board", "Purple Board", "Pink Board",
				"White Board", "Black Board", "Random Board" };
		controlArea
		.add(new choicePanel("Board Colour:", bColor, optionButton3));

		String[] pTypePlayer1 = { "Circle", "Square", "Triangle", "Star",
		"Random   " };
		controlArea.add(new choicePanel("Piece Type Player 1:", pTypePlayer1,
				optionButton4));

		String[] pTypePlayer2 = { "Circle ", "Square ", "Triangle ", "Star ",
		"Random    " };
		controlArea.add(new choicePanel("Piece Type Player 2:", pTypePlayer2,
				optionButton5));

		String[] gameType = { "Player v Player", "Player v Comp" };
		controlArea.add(new choicePanel("Game Type:", gameType, optionButton6));

		// button panel
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout());
		buttonPane.setPreferredSize(new Dimension(300, 40));
		buttonPane.setBackground(Color.lightGray); // Color.lightGray
		// buttonPane.setBorder(BorderFactory.createLineBorder(Color.black));

		// make buttons
		JButton okButton = new JButton("Ok", null);
		JButton cancelButton = new JButton("Cancel", null);

		// make action commands
		okButton.setActionCommand("ok");
		cancelButton.setActionCommand("cancel");

		// add listeners
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		// add hover over text
		okButton.setToolTipText("Apply changes");
		cancelButton.setToolTipText("Cancel any changes made");

		// add buttons
		buttonPane.add(okButton);
		buttonPane.add(cancelButton);
		content.add(buttonPane, BorderLayout.SOUTH);

		for (int i = 0; i < optionButton1.length; i++) {
			optionButton1[i].addActionListener(this);
		}
		for (int i = 0; i < optionButton2.length; i++) {
			optionButton2[i].addActionListener(this);
		}
		for (int i = 0; i < optionButton3.length; i++) {
			optionButton3[i].addActionListener(this);
		}
		for (int i = 0; i < optionButton4.length; i++) {
			optionButton4[i].addActionListener(this);
		}
		for (int i = 0; i < optionButton5.length; i++) {
			optionButton5[i].addActionListener(this);
		}
		for (int i = 0; i < optionButton6.length; i++) {
			optionButton6[i].addActionListener(this);
		}

		setSelected();
		unselect();
		content.add(controlArea, BorderLayout.CENTER);
		optionsWindow.setSize(400, 500);
		optionsWindow.setLocationRelativeTo(null);
		optionsWindow.setVisible(true);
		optionsWindow.setResizable(false);
		optionsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// adds choices on screen
	public class choicePanel extends JPanel {
		public choicePanel(String title, String[] buttonLabels,
				JRadioButton[] button) {

			super(new GridLayout(10, 1)); // 3, 2
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createTitledBorder(title));

			ButtonGroup group = new ButtonGroup();
			int halfLength = (buttonLabels.length / 2);// Assumes even length

			for (int i = 0; i < buttonLabels.length; i++) { // < half length

				button[i] = new JRadioButton(buttonLabels[i]);
				group.add(button[i]);
				add(button[i]);
				button[i].setActionCommand(buttonLabels[i]);

				// button[i + halfLength] = new JRadioButton(buttonLabels[i +
				// halfLength]);
				// group.add(button[i + halfLength]);
				// add(button[i + halfLength]);;
				// button[i + halfLength].setActionCommand(buttonLabels[(i +
				// halfLength)]);

			}
		}
	}

	// set radio buttons highlighted when clicked
	public void setSelected() {
		optionButton1[main.selection1].setSelected(true);
		optionButton2[main.selection2].setSelected(true);
		optionButton3[main.selection3].setSelected(true);
		optionButton4[main.selection4].setSelected(true);
		optionButton5[main.selection5].setSelected(true);
		optionButton6[main.selection6].setSelected(true);
	}

	public void previousSelected() {

		// reset selection for previous piece colour for player 1
		if (previousPlayer1PieceColour.equals("Red")) {
			main.selection1 = 0;
		}
		if (previousPlayer1PieceColour.equals("Orange")) {
			main.selection1 = 1;
		}
		if (previousPlayer1PieceColour.equals("Yellow")) {
			main.selection1 = 2;
		}
		if (previousPlayer1PieceColour.equals("Green")) {
			main.selection1 = 3;
		}
		if (previousPlayer1PieceColour.equals("Blue")) {
			main.selection1 = 4;
		}
		if (previousPlayer1PieceColour.equals("Purple")) {
			main.selection1 = 5;
		}
		if (previousPlayer1PieceColour.equals("Pink")) {
			main.selection1 = 6;
		}
		if (previousPlayer1PieceColour.equals("White")) {
			main.selection1 = 7;
		}
		if (previousPlayer1PieceColour.equals("Black")) {
			main.selection1 = 8;
		}
		if (previousPlayer1PieceColour.equals("Random")) {
			main.selection1 = 9;
		}

		// reset selection for previous piece colour for player 2
		if (previousPlayer2PieceColour.equals("Red")) {
			main.selection2 = 0;
		}
		if (previousPlayer2PieceColour.equals("Orange")) {
			main.selection2 = 1;
		}
		if (previousPlayer2PieceColour.equals("Yellow")) {
			main.selection2 = 2;
		}
		if (previousPlayer2PieceColour.equals("Green")) {
			main.selection2 = 3;
		}
		if (previousPlayer2PieceColour.equals("Blue")) {
			main.selection2 = 4;
		}
		if (previousPlayer2PieceColour.equals("Purple")) {
			main.selection2 = 5;
		}
		if (previousPlayer2PieceColour.equals("Pink")) {
			main.selection2 = 6;
		}
		if (previousPlayer2PieceColour.equals("White")) {
			main.selection2 = 7;
		}
		if (previousPlayer2PieceColour.equals("Black")) {
			main.selection2 = 8;
		}
		if (previousPlayer2PieceColour.equals("Random")) {
			main.selection2 = 9;
		}

		// reset selection for previous board colour
		if (previousBoardColour.equals("Red")) {
			main.selection3 = 0;
		}
		if (previousBoardColour.equals("Orange")) {
			main.selection3 = 1;
		}
		if (previousBoardColour.equals("Yellow")) {
			main.selection3 = 2;
		}
		if (previousBoardColour.equals("Green")) {
			main.selection3 = 3;
		}
		if (previousBoardColour.equals("Blue")) {
			main.selection3 = 4;
		}
		if (previousBoardColour.equals("Purple")) {
			main.selection3 = 5;
		}
		if (previousBoardColour.equals("Pink")) {
			main.selection3 = 6;
		}
		if (previousBoardColour.equals("White")) {
			main.selection3 = 7;
		}
		if (previousBoardColour.equals("Black")) {
			main.selection3 = 8;
		}
		if (previousBoardColour.equals("Random")) {
			main.selection3 = 9;
		}

		// reset selection for previous player 1 piece type
		if (previousPlayer1PieceType.equals("Circle")) {
			main.selection4 = 0;
		}
		if (previousPlayer1PieceType.equals("Square")) {
			main.selection4 = 1;
		}
		if (previousPlayer1PieceType.equals("Triangle")) {
			main.selection4 = 2;
		}
		if (previousPlayer1PieceType.equals("Star")) {
			main.selection4 = 3;
		}
		if (previousPlayer1PieceType.equals("Random")) {
			main.selection4 = 4;
		}
		
		// reset selection for previous player 2 piece type
		if (previousPlayer2PieceType.equals("Circle")) {
			main.selection5 = 0;
		}
		if (previousPlayer2PieceType.equals("Square")) {
			main.selection5 = 1;
		}
		if (previousPlayer2PieceType.equals("Triangle")) {
			main.selection5 = 2;
		}
		if (previousPlayer2PieceType.equals("Star")) {
			main.selection5 = 3;
		}
		if (previousPlayer2PieceType.equals("Random")) {
			main.selection5 = 4;
		}
		
		// reset selection for previous game type
		if (previousGameType.equals("Player")) {
			main.selection6 = 0;
		}
		if (previousGameType.equals("Computer")) {
			main.selection6 = 1;
		}
		
	}

	// set selections
	public void cancelSelections() {
		String a = previousPlayer1PieceColour;
		String b = previousPlayer2PieceColour;
		String c = previousBoardColour;
		String d = previousPlayer1PieceType;
		String e = previousPlayer2PieceType;
		String f = previousGameType;
	}

	// unselect(); radio buttons if one colour is selected
	public void unselect() {

		if (main.pieceColourPlayer1.equals("Red")) {
			optionButton2[0].setEnabled(false);
		} else {
			optionButton2[0].setEnabled(true);
		}

		if (main.pieceColourPlayer1.equals("Orange")) {
			optionButton2[1].setEnabled(false);
		} else {
			optionButton2[1].setEnabled(true);
		}

		if (main.pieceColourPlayer1.equals("Yellow")) {
			optionButton2[2].setEnabled(false);
		} else {
			optionButton2[2].setEnabled(true);
		}

		if (main.pieceColourPlayer1.equals("Green")) {
			optionButton2[3].setEnabled(false);
		} else {
			optionButton2[3].setEnabled(true);
		}

		if (main.pieceColourPlayer1.equals("Blue")) {
			optionButton2[4].setEnabled(false);
		} else {
			optionButton2[4].setEnabled(true);
		}

		if (main.pieceColourPlayer1.equals("Purple")) {
			optionButton2[5].setEnabled(false);
		} else {
			optionButton2[5].setEnabled(true);
		}

		if (main.pieceColourPlayer1.equals("Pink")) {
			optionButton2[6].setEnabled(false);
		} else {
			optionButton2[6].setEnabled(true);
		}

		if (main.pieceColourPlayer1.equals("White")) {
			optionButton2[7].setEnabled(false);
		} else {
			optionButton2[7].setEnabled(true);
		}

		if (main.pieceColourPlayer1.equals("Black")) {
			optionButton2[8].setEnabled(false);
		} else {
			optionButton2[8].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("Red")) {
			optionButton1[0].setEnabled(false);
		} else {
			optionButton1[0].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("Orange")) {
			optionButton1[1].setEnabled(false);
		} else {
			optionButton1[1].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("Yellow")) {
			optionButton1[2].setEnabled(false);
		} else {
			optionButton1[2].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("Green")) {
			optionButton1[3].setEnabled(false);
		} else {
			optionButton1[3].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("Blue")) {
			optionButton1[4].setEnabled(false);
		} else {
			optionButton1[4].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("Purple")) {
			optionButton1[5].setEnabled(false);
		} else {
			optionButton1[5].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("Pink")) {
			optionButton1[6].setEnabled(false);
		} else {
			optionButton1[6].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("White")) {
			optionButton1[7].setEnabled(false);
		} else {
			optionButton1[7].setEnabled(true);
		}

		if (main.pieceColourPlayer2.equals("Black")) {
			optionButton1[8].setEnabled(false);
		} else {
			optionButton1[8].setEnabled(true);
		}

	}

	// get random shape for pieces
	public String getShape(int r) {
		switch (r) {

		case 0:
			return "Circle";
		case 1:
			return "Square";
		case 2:
			return "Triangle";
		case 3:
			return "Star";
		default:
			break;
		}
		return "";
	}

	// get random colour for pieces
	public String getColour(int r) {
		switch (r) {
		case 0:
			return "Red";
		case 1:
			return "Orange";
		case 2:
			return "Yellow";
		case 3:
			return "Green";
		case 4:
			return "Blue";
		case 5:
			return "Purple";
		case 6:
			return "Pink";
		case 7:
			return "White";
		case 8:
			return "Black";
		default:
			break;
		}
		return "";
	}

	// get random colour for board
	public void getBoard(int r) {
		switch (r) {
		case 0: {
			System.out.println("Colour : Red");
			main.connectFourPane.setBackground(Color.red);
			main.blackBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		case 1: {
			System.out.println("Colour : Orange");
			main.connectFourPane.setBackground(Color.orange);
			main.blackBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		case 2: {
			System.out.println("Colour : Yellow");
			main.connectFourPane.setBackground(Color.yellow);
			main.blackBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		case 3: {
			System.out.println("Colour : Green");
			main.connectFourPane.setBackground(Color.green);
			main.blackBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		case 4: {
			System.out.println("Colour : Blue");
			main.connectFourPane.setBackground(Color.blue);
			main.blackBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		case 5: {
			System.out.println("Colour : Purple");
			main.connectFourPane.setBackground(new Color(160, 32, 240));
			main.blackBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		case 6: {
			System.out.println("Colour : Pink");
			main.connectFourPane.setBackground(Color.pink);
			main.blackBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		case 7: {
			System.out.println("Colour : White");
			main.connectFourPane.setBackground(Color.white);
			main.blackBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		case 8: {
			System.out.println("Colour : Black");
			main.connectFourPane.setBackground(Color.black);
			main.whiteBorder();
			main.selection3 = 9;
			setSelected();
			break;
		}
		default:
			break;
		}
	}

	// return colour id
	public int getColourId(String colour) {
		if (colour.equals("Red")) {
			return 0;
		}
		if (colour.equals("Orange")) {
			return 1;
		}
		if (colour.equals("Yellow")) {
			return 2;
		}
		if (colour.equals("Green")) {
			return 3;
		}
		if (colour.equals("Blue")) {
			return 4;
		}
		if (colour.equals("Purple")) {
			return 5;
		}
		if (colour.equals("Pink")) {
			return 6;
		}
		if (colour.equals("White")) {
			return 7;
		}
		if (colour.equals("Black")) {
			return 8;
		}
		return -1;
	}

	// return board colour id
	public int getPreviousBoardId() {
		if (previousBoardColour.equals("Red")) {
			return 0;
		}
		if (previousBoardColour.equals("Orange")) {
			return 1;
		}
		if (previousBoardColour.equals("Yellow")) {
			return 2;
		}
		if (previousBoardColour.equals("Green")) {
			return 3;
		}
		if (previousBoardColour.equals("Blue")) {
			return 4;
		}
		if (previousBoardColour.equals("Purple")) {
			return 5;
		}
		if (previousBoardColour.equals("Pink")) {
			return 6;
		}
		if (previousBoardColour.equals("White")) {
			return 7;
		}
		if (previousBoardColour.equals("Black")) {
			return 8;
		}
		return -1;
	}

	// action performed by buttons etc
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == ("ok")) {
			System.out.println("Applying changes...");
			optionsWindow.dispose();
			
			if (previousGameType == "Player" && main.gameType == "Computer") {
				main.resetBoard();
			}
			if (previousGameType == "Computer" && main.gameType == "Player") {
				main.resetBoard();
			}
			
		}

		// return and set all previous settings that were saved before
		if (e.getActionCommand() == ("cancel")) {
			System.out.println("Cancelling...");

			// store what was originally saved
			main.pieceColourPlayer1 = previousPlayer1PieceColour;
			main.pieceColourPlayer2 = previousPlayer2PieceColour;
			main.pieceTypePlayer1 = previousPlayer1PieceType;
			main.pieceTypePlayer2 = previousPlayer2PieceType;
			int boardId = getPreviousBoardId();
			getBoard(boardId);
			main.boardColour = getColour(boardId);
			
			// select what was originally selected
			previousSelected();
			setSelected();

			// set previous game type
			main.gameType = previousGameType;
			check.resetImages();
			optionsWindow.dispose();
		}

		// starting player 1 action listeners

		if (e.getActionCommand() == ("Red")) {
			System.out.println("Colour : Red");
			main.pieceColourPlayer1 = "Red";
			main.selection1 = 0;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Orange")) {
			System.out.println("Colour : Orange");
			main.pieceColourPlayer1 = "Orange";
			main.selection1 = 1;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Yellow")) {
			System.out.println("Colour : Yellow");
			main.pieceColourPlayer1 = "Yellow";
			main.selection1 = 2;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Green")) {
			System.out.println("Colour : Green");
			main.pieceColourPlayer1 = "Green";
			main.selection1 = 3;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Blue")) {
			System.out.println("Colour : Blue");
			main.pieceColourPlayer1 = "Blue";
			main.selection1 = 4;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Purple")) {
			System.out.println("Colour : Purple");
			main.pieceColourPlayer1 = "Purple";
			main.selection1 = 5;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Pink")) {
			System.out.println("Colour : Pink");
			main.pieceColourPlayer1 = "Pink";
			main.selection1 = 6;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("White")) {
			System.out.println("Colour : White");
			main.pieceColourPlayer1 = "White";
			main.selection1 = 7;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Black")) {
			System.out.println("Colour : Black");
			main.pieceColourPlayer1 = "Black";
			main.selection1 = 8;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Random")) {
			System.out.println("Colour : Random");
			randomNo = new Random();
			int r = randomNo.nextInt(4);
			main.pieceColourPlayer1 = getColour(r);
			main.selection1 = 9;
			setSelected();
			check.resetImages();
			unselect();
		}

		// starting player 2 action listeners

		if (e.getActionCommand() == ("Red ")) {
			System.out.println("Colour : Red");
			main.pieceColourPlayer2 = "Red";
			main.selection2 = 0;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Orange ")) {
			System.out.println("Colour : Orange");
			main.pieceColourPlayer2 = "Orange";
			main.selection2 = 1;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Yellow ")) {
			System.out.println("Colour : Yellow");
			main.pieceColourPlayer2 = "Yellow";
			main.selection2 = 2;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Green ")) {
			System.out.println("Colour : Green");
			main.pieceColourPlayer2 = "Green";
			main.selection2 = 3;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Blue ")) {
			System.out.println("Colour : Blue");
			main.pieceColourPlayer2 = "Blue";
			main.selection2 = 4;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Purple ")) {
			System.out.println("Colour : Purple");
			main.pieceColourPlayer2 = "Purple";
			main.selection2 = 5;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Pink ")) {
			System.out.println("Colour : Pink");
			main.pieceColourPlayer2 = "Pink";
			main.selection2 = 6;
			check.resetImages();
			setSelected();
			unselect();
		}
		if (e.getActionCommand() == ("White ")) {
			System.out.println("Colour : White");
			main.pieceColourPlayer2 = "White";
			main.selection2 = 7;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Black ")) {
			System.out.println("Colour : Black");
			main.pieceColourPlayer2 = "Black";
			main.selection2 = 8;
			setSelected();
			check.resetImages();
			unselect();
		}
		if (e.getActionCommand() == ("Random ")) {
			System.out.println("Colour : Random");
			randomNo = new Random();
			int r = randomNo.nextInt(5) + 4;
			main.pieceColourPlayer2 = getColour(r);
			main.selection2 = 9;
			setSelected();
			check.resetImages();
			unselect();
		}

		// starting board action listeners

		if (e.getActionCommand() == ("Red Board")) {
			System.out.println("Colour : Red");
			main.connectFourPane.setBackground(Color.red);
			main.blackBorder();
			main.selection3 = 0;
			setSelected();
			main.boardColour = "Red";
		}
		if (e.getActionCommand() == ("Orange Board")) {
			System.out.println("Colour : Orange");
			main.connectFourPane.setBackground(Color.orange);
			main.blackBorder();
			main.selection3 = 1;
			setSelected();
			main.boardColour = "Orange";
		}
		if (e.getActionCommand() == ("Yellow Board")) {
			System.out.println("Colour : Yellow");
			main.connectFourPane.setBackground(Color.yellow);
			main.blackBorder();
			main.selection3 = 2;
			setSelected();
			main.boardColour = "Yellow";
		}
		if (e.getActionCommand() == ("Green Board")) {
			System.out.println("Colour : Green");
			main.connectFourPane.setBackground(Color.green);
			main.blackBorder();
			main.selection3 = 3;
			setSelected();
			main.boardColour = "Green";
		}
		if (e.getActionCommand() == ("Blue Board")) {
			System.out.println("Colour : Blue");
			main.connectFourPane.setBackground(Color.blue);
			main.blackBorder();
			main.selection3 = 4;
			setSelected();
			main.boardColour = "Blue";
		}
		if (e.getActionCommand() == ("Purple Board")) {
			System.out.println("Colour : Purple");
			main.connectFourPane.setBackground(new Color(160, 32, 240));
			main.blackBorder();
			main.selection3 = 5;
			setSelected();
			main.boardColour = "Purple";
		}
		if (e.getActionCommand() == ("Pink Board")) {
			System.out.println("Colour : Pink");
			main.connectFourPane.setBackground(new Color(255, 110, 180));
			main.blackBorder();
			main.selection3 = 6;
			setSelected();
			main.boardColour = "Pink";
		}
		if (e.getActionCommand() == ("White Board")) {
			System.out.println("Colour : White");
			main.connectFourPane.setBackground(Color.white);
			main.blackBorder();
			main.selection3 = 7;
			setSelected();
			main.boardColour = "White";
		}
		if (e.getActionCommand() == ("Black Board")) {
			System.out.println("Colour : Black");
			main.connectFourPane.setBackground(Color.black);
			main.whiteBorder();
			main.selection3 = 8;
			setSelected();
			main.boardColour = "Black";
		}
		if (e.getActionCommand() == ("Random Board")) {
			System.out.println("Colour : Random");
			randomNo = new Random();
			int r = randomNo.nextInt(9);
			getBoard(r);
			main.boardColour = "Random";
		}

		// starting piece type action listeners player 1
		if (e.getActionCommand() == ("Circle")) {
			main.pieceTypePlayer1 = "Circle";
			main.selection4 = 0;
			setSelected();
			check.resetImages();
		}
		if (e.getActionCommand() == ("Square")) {
			main.pieceTypePlayer1 = "Square";
			main.selection4 = 1;
			setSelected();
			check.resetImages();
		}
		if (e.getActionCommand() == ("Triangle")) {
			main.pieceTypePlayer1 = "Triangle";
			main.selection4 = 2;
			setSelected();
			check.resetImages();
		}
		if (e.getActionCommand() == ("Star")) {
			main.pieceTypePlayer1 = "Star";
			main.selection4 = 3;
			setSelected();
			check.resetImages();
		}
		if (e.getActionCommand() == ("Random   ")) {
			randomNo = new Random();
			int r = randomNo.nextInt(4);
			main.pieceTypePlayer1 = getShape(r);
			main.selection4 = 4;
			setSelected();
			check.resetImages();
		}

		// starting piece type action listeners player 2
		if (e.getActionCommand() == ("Circle ")) {
			main.pieceTypePlayer2 = "Circle";
			main.selection5 = 0;
			setSelected();
			check.resetImages();
		}
		if (e.getActionCommand() == ("Square ")) {
			main.pieceTypePlayer2 = "Square";
			main.selection5 = 1;
			setSelected();
			check.resetImages();
		}
		if (e.getActionCommand() == ("Triangle ")) {
			main.pieceTypePlayer2 = "Triangle";
			main.selection5 = 2;
			setSelected();
			check.resetImages();
		}
		if (e.getActionCommand() == ("Star ")) {
			main.pieceTypePlayer2 = "Star";
			main.selection5 = 3;
			setSelected();
			check.resetImages();
		}
		if (e.getActionCommand() == ("Random    ")) {
			randomNo = new Random();
			int r = randomNo.nextInt(4);
			main.pieceTypePlayer2 = getShape(r);
			main.selection5 = 4;
			setSelected();
			check.resetImages();
		}

		// options for different game types
		if (e.getActionCommand() == ("Player v Player")) {
			System.out.println("Player verses Player ...");
			main.gameType = "Player";
			main.selection6 = 0;
			setSelected();
		}
		if (e.getActionCommand() == ("Player v Comp")) {
			System.out.println("Player versus computer ...");
			main.gameType = "Computer";
			main.selection6 = 1;
			setSelected();
		}

	}
}
