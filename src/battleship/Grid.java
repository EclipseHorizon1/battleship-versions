package battleship;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFrame;

import java.awt.event.*;

class MyCanvas extends JPanel implements ActionListener {

	// declare variables here

	int[][] playerGrid; // Both 2d arrays declared.
	int[][] opponentGrid;

	int sqrSize; // Size of each square in the grids.
	int playerGridOffset; // start of x axis for the player's and opponent's grid
	int opponentGridOffset;
	int gridsYoffset; // start of Y axis for both grids so they are aligned.
	int selectedShipLength;
	JComboBox<Ship> shipDropdown; // declare that this is the variable for the drop down and it will contain
									// strings.
	JTextField xTextField; // declare that these will be text fields.
	JTextField yTextField;

	int xShipCoordinate;
	int yShipCoordinate;

	String xInput;
	String yInput;
	char yColumn;

	Button horizontalButton, verticalButton;
	boolean horizontal;
	boolean hBoundary;
	boolean vBoundary;

	public void init() {

		// instantiate your variables here

		// and anything else you need to set up.

		playerGrid = new int[10][10];
		opponentGrid = new int[10][10];

		sqrSize = 40;
		playerGridOffset = 480;
		opponentGridOffset = 920;
		gridsYoffset = 60;

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				playerGrid[row][col] = 0;
				opponentGrid[row][col] = 0;
			}
		}

		playerGrid[0][1] = 1;
		playerGrid[1][0] = 1;
		playerGrid[2][1] = 1;
		playerGrid[1][2] = 1;

		setLayout(null); // this is here so that it doesn't use the default coordinates of the system for
							// the drop down.
							// it allows me to place my own custom position.

		shipDropdown = new JComboBox<Ship>(); // Creates a new drop down.

		shipDropdown.addItem(patrolBoat); // These lines add each ship to the drop down.
		shipDropdown.addItem(submarine);
		shipDropdown.addItem(destroyer);
		shipDropdown.addItem(battleship);
		shipDropdown.addItem(aircraftCarrier);

		shipDropdown.setBounds(50, 100, 200, 30); // Here are the custom size and position of the drop down.
		shipDropdown.addActionListener(this); // this calls on the action listener methods to do something.
		add(shipDropdown); // This adds the drop down to the window

		xTextField = new JTextField(); // This adds the text fields and determines the bakcground,bounds and location
		xTextField.setBackground(new Color(230, 230, 230));
		xTextField.setBounds(50, 160, 200, 40);
		xTextField.addActionListener(this);
		add(xTextField);

		yTextField = new JTextField();// this does the same a the row text field.
		yTextField.setBackground(new Color(230, 230, 230));
		yTextField.setBounds(50, 210, 200, 40);
		yTextField.addActionListener(this);
		add(yTextField);

		horizontalButton = new Button("Horizontal");

		horizontalButton.setBackground(Color.lightGray);

		horizontalButton.setBounds(50, 260, 200, 40); // (x start, y start, width, height)

		horizontalButton.addActionListener(this);

		add(horizontalButton);

		verticalButton = new Button("Vertical");

		verticalButton.setBackground(Color.lightGray);

		verticalButton.setBounds(50, 310, 200, 40); // (x start, y start, width, height)

		verticalButton.addActionListener(this);

		add(verticalButton);

		revalidate(); // This tells the internal system that there is a new drop down, so it needs to
						// recalculate the stuff.
		repaint(); // This paints the drop down on the window.

	}
	// Creating the each ship as an object

	Ship patrolBoat = new Ship("Patrol Boat", 2, 0);
	Ship submarine = new Ship("Submarine", 3, 0);
	Ship destroyer = new Ship("Destroyer", 3, 0);
	Ship battleship = new Ship("Battleship", 4, 0);
	Ship aircraftCarrier = new Ship("Aircraft Carrier", 5, 0);

	public void actionPerformed(ActionEvent e) {

		// actions go here

		if (e.getSource() == shipDropdown) {

			selectedShipLength = ((Ship) shipDropdown.getSelectedItem()).shipLength();
			System.out.println(selectedShipLength);
		}

		// This block of code above checks if the input was on the dropdown, then it
		// converts the string to int and stores in the variable.

		if (e.getSource() == xTextField) {

			String xInput = xTextField.getText();
			xShipCoordinate = Integer.parseInt(xInput);

			System.out.println(xShipCoordinate);

			if (xShipCoordinate >= 1 && xShipCoordinate <= 10) {
				System.out.println("Valid");
			} else {

				System.out.println("Invalid");
			}
		}

		// this block of code above checks if the input was on the row text field, it
		// converts the string to an int, then prints the number on the console
		// It also checks if the input number was valid or invalid.

		if (e.getSource() == yTextField) {

			String yInput = yTextField.getText();
			yInput = yInput.toUpperCase();
			char yColumn = yInput.charAt(0);

			if (yColumn >= 'A' && yColumn <= 'J') {
				System.out.println("Valid");
			} else {
				System.out.println("Invalid");
			}

			yShipCoordinate = yColumn - 'A';

			System.out.println(yShipCoordinate);
		}

		// This block of code checks if the input was from the column text field.
		// It gets the text, converts it to upper case.
		// then uses A-J as positions 0-9 in the array.
		// It checks if the input is within the A-J range.If it is, it prints "Valid";
		// otherwise, it prints "Invalid".
		// It then converts the letter to an integer by subtracting
		// the character value of A (65) from the character value of the letter.
		// e.g. C = 67 - A = 65 = 2.
		// Therefore, column C corresponds to index 2 in the array.

		if (e.getSource() == horizontalButton) {

			horizontal = true;

		}

		if (e.getSource() == verticalButton) {

			horizontal = false;

		}
		
		
		hBoundary = false;
		vBoundary = false;

		if (horizontal) {
			if (xShipCoordinate + selectedShipLength - 1 >= 1 && xShipCoordinate + selectedShipLength - 1 <= 10) {

				hBoundary = true;
			}
		}

		if (!horizontal) {
			if (yShipCoordinate + selectedShipLength - 1 >= 0 && yShipCoordinate + selectedShipLength - 1 <= 9) {

				vBoundary = true;
			}
		}

	}

	public void paint(Graphics g) {

		super.paint(g);

		// put instructions for pretty stuff here

		g.drawRect(450, 19, 901, 501); // Background rectangle
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(451, 20, 900, 500);

		g.setColor(Color.black); // Labels for the grids
		g.setFont(new Font("ARIAL", Font.BOLD, 16));
		g.drawString("YOUR GRID", playerGridOffset, gridsYoffset - 15);
		g.drawString("OPPONENT GRID", opponentGridOffset, gridsYoffset - 15);

		// DRAW PLAYER GRID
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				int x = playerGridOffset + (col * sqrSize);
				int y = gridsYoffset + (row * sqrSize);

				// Read array data to decide block colour or if its a ship or not
				if (playerGrid[row][col] == 1) {
					g.setColor(Color.DARK_GRAY); // Ship
				} else {
					g.setColor(Color.CYAN); // Water
				}

				g.fillRect(x, y, sqrSize, sqrSize);
				g.setColor(Color.BLACK); // Outline color
				g.drawRect(x, y, sqrSize, sqrSize);
			}
		}

		// DRAW OPPONENT GRID
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				// Uses opponent offset to push it to the right
				int x = opponentGridOffset + (col * sqrSize);
				int y = gridsYoffset + (row * sqrSize);

				g.setColor(Color.CYAN); // Default opponent water
				g.fillRect(x, y, sqrSize, sqrSize);
				g.setColor(Color.BLACK);
				g.drawRect(x, y, sqrSize, sqrSize);
			}
		}

	}

}

public class Grid {

	public static void main(String[] a) {

		MyCanvas myCanvas = new MyCanvas();

		JFrame window = new JFrame();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setBounds(30, 30, 400, 600); // Use this to change the window size

		window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Use this to make the window full screen automatically

		window.getContentPane().add(myCanvas);

		window.setVisible(true);

		myCanvas.init();

	}

}