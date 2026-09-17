package battleship;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
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

	JTextField atkXTextField;
	JTextField atkYTextField;

	JLabel placementLabel;
	JLabel atkLabel;

	int xShipCoordinate;
	int yShipCoordinate;

	String xInput;
	String yInput;
	char yColumn;

	String atkXInput;
	String atkYInput;
	char atkYColumn;
	int atkXCoordinate;
	int atkYCoordinate;

	Button horizontalButton, verticalButton, placeShip, attack;
	boolean horizontal;
	boolean hBoundary;
	boolean vBoundary;

	boolean shipThere;

	int computerX;
	int computerY;

	int computerShipX;
	int computerShipY;

	boolean computerHorizontal, computerVertical;

	public void init() {

		// instantiate your variables here

		// and anything else you need to set up.

		playerGrid = new int[10][10];
		opponentGrid = new int[10][10];

		sqrSize = 40;
		playerGridOffset = 300;
		opponentGridOffset = 920;
		gridsYoffset = 60;

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				playerGrid[row][col] = 0;
				opponentGrid[row][col] = 0;
			}
		}

		opponentGrid[0][1] = 1;
		opponentGrid[1][0] = 1;
		opponentGrid[2][1] = 1;
		opponentGrid[1][2] = 1;
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

		xTextField = new JTextField(); // This adds the text fields and determines the background,bounds and location
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

		placeShip = new Button("Place Ship");

		placeShip.setBackground(Color.lightGray);

		placeShip.setBounds(50, 360, 200, 40); // (x start, y start, width, height)

		placeShip.addActionListener(this);

		add(placeShip);

		attack = new Button("Attack");

		attack.setBackground(Color.lightGray);

		attack.setBounds(710, 260, 200, 40);

		attack.addActionListener(this);

		add(attack);

		atkXTextField = new JTextField();

		atkXTextField.setBackground(new Color(230, 230, 230));

		atkXTextField.setBounds(710, 160, 200, 40);

		atkXTextField.addActionListener(this);

		add(atkXTextField);

		atkYTextField = new JTextField();

		atkYTextField.setBackground(new Color(230, 230, 230));

		atkYTextField.setBounds(710, 210, 200, 40);

		atkYTextField.addActionListener(this);

		add(atkYTextField);

		atkLabel = new JLabel("");

		atkLabel.setBounds(730, 300, 250, 30);

		add(atkLabel);

		placementLabel = new JLabel("");

		placementLabel.setBounds(50, 430, 250, 30);

		add(placementLabel);

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

			if (shipDropdown.getSelectedItem() != null) {

				selectedShipLength = ((Ship) shipDropdown.getSelectedItem()).shipLength();
				System.out.println(selectedShipLength);
			}
		}

		// This block of code above checks if the input was on the drop down, then it
		// stores the length in the variable.

		if (e.getSource() == xTextField) {

			String xInput = xTextField.getText();

			if (!xInput.isEmpty()) {

				xShipCoordinate = Integer.parseInt(xInput);

				System.out.println(xShipCoordinate);

				if (xShipCoordinate >= 1 && xShipCoordinate <= 10) {
					System.out.println("Valid");
				} else {

					System.out.println("Invalid");
				}
			}
		}

		// this block of code above checks if the input was on the row text field, it
		// converts the string to an int, then prints the number on the console
		// It also checks if the input number was valid or invalid.

		if (e.getSource() == yTextField) {

			String yInput = yTextField.getText();

			if (!yInput.isEmpty()) {

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

		// This block of code above check if the horizontal or vertical button was
		// pressed. If horizontal was pressed then horizontal is true
		// If vertical button was pressed then horizontal is false.

		if (e.getSource() == placeShip) {

			xInput = xTextField.getText();
			yInput = yTextField.getText();

			if (!xInput.isEmpty() && !yInput.isEmpty()) {

				xShipCoordinate = Integer.parseInt(xInput);

				yInput = yInput.toUpperCase();
				yColumn = yInput.charAt(0);
				yShipCoordinate = yColumn - 'A';

				// This Block of code above I added so that you don't need to press enter on the
				// text field.

				hBoundary = false;
				vBoundary = false;

				if (horizontal) {
					if (xShipCoordinate + selectedShipLength - 1 >= 1
							&& xShipCoordinate + selectedShipLength - 1 <= 10) {

						hBoundary = true;
					}
				}

				if (!horizontal) {
					if (yShipCoordinate + selectedShipLength - 1 >= 0
							&& yShipCoordinate + selectedShipLength - 1 <= 9) {

						vBoundary = true;
					}
				}

				// These blocks of code above checks if the ship is within the boundary of the
				// grid.
				// It takes the ship coordinate and the ship length minus 1, then checks if that
				// number is equal or between 1-10 for x, and 0-9 for y.
				// This is because the letter text field sees A as 0 and J as 9. While the
				// number text field
				// can easily check if the number is between 1-10

				shipThere = false; // sets everywhere as empty or "no ship there"

				if (hBoundary || vBoundary) {

					if (horizontal) {
						for (int i = 0; i < selectedShipLength; i++) {

							if (playerGrid[yShipCoordinate][xShipCoordinate - 1 + i] == 1) {

								shipThere = true;

							}
						}
					}

					if (!horizontal) {
						for (int i = 0; i < selectedShipLength; i++) {
							if (playerGrid[yShipCoordinate + i][xShipCoordinate - 1] == 1) {
								shipThere = true;
							}
						}

					}

				}

				// This block of code above checks if the ship is within the boundaries of the
				// grid
				// If it is, then for horizontal, it gets the x ship length and increments it.
				// Then if the x ship coordinate minus 1, plus i is exactly like 1 'Equality'.
				// it is true, so ''There is a ship there''
				// For the vertical is the same thing but it adds i to the y ship coordinate.

				if ((hBoundary || vBoundary) && !shipThere) {

					if (horizontal) {
						for (int i = 0; i < selectedShipLength; i++) {

							playerGrid[yShipCoordinate][xShipCoordinate - 1 + i] = 1;

						}

					}

					if (!horizontal) {
						for (int i = 0; i < selectedShipLength; i++) {

							playerGrid[yShipCoordinate + i][xShipCoordinate - 1] = 1;
						}
					}

					// This block of code above checks if the horizontal boundary OR vertical
					// boundary is true
					// which means it is inside the grid boundary.
					// AND if "There is not a ship there"
					// Then for horizontal it assigns 1 to the y and x ship coordinates and length
					// of the ship.
					// Which triggers the block of code above this block, which changes makes the
					// variable true
					// so "there is a ship there".
					// for vertical is the same thing, it makes the y ship coordinate plus the
					// length assigned to 1
					// which triggers the block of code above this and sets the variable to "there
					// is a ship there"

					xTextField.setText("");
					yTextField.setText("");
					shipDropdown.removeItem(shipDropdown.getSelectedItem());

					if (shipDropdown.getItemCount() == 0) {

						placementLabel.setText("All Ships Have Been Placed!");

					} else {
						placementLabel.setText("Ship Placed!");
					}

				} else {

					placementLabel.setText("Invalid Placement");

				}

			}
			repaint();

			// This block of code do some stuff after the place button is pressed.
			// It resets both text fields, it removes from the drop-down the ship you just
			// placed.
			// It gets the item count in the drop-down and if it is equal to 0.
			// It displays a message that saying 'all ships have been placed'.
			// and if it is not equal to 1, then i must mean that there is still a ship
			// there.
			// So it displays Ship placed.

			// It can also display 'invalid placement' to ships that are not within the
			// boundary
			// and that are on top of another ship, and to those that are on a diagonal.
			// That is why it is an else to the if that says if ((hBoundary || vBoundary) &&
			// !shipThere)
		}

		if (e.getSource() == atkXTextField) {
			atkXInput = atkXTextField.getText();

			if (!atkXInput.isEmpty()) {
				atkXCoordinate = Integer.parseInt(atkXInput);

				System.out.println(atkXCoordinate);

				if (atkXCoordinate >= 1 && atkXCoordinate <= 10) {
					System.out.println("Valid");
				} else {

					System.out.println("Invalid");

				}

			}

		}

		if (e.getSource() == atkYTextField) {

			atkYInput = atkYTextField.getText();

			if (!atkYInput.isEmpty()) {
				atkYInput = atkYInput.toUpperCase();
				atkYColumn = atkYInput.charAt(0);

				if (atkYColumn >= 'A' && atkYColumn <= 'J') {
					System.out.println("Valid");
				} else {
					System.out.println("Invalid");
				}

				atkYCoordinate = atkYColumn - 'A';

				System.out.println(atkYCoordinate);
			}

		}

		// This gets the Inputs from both attack text fields and it converts to Int.
		// This is pretty much the same as the other text fields but with other variable
		// names.

		if (e.getSource() == attack) {

			atkXInput = atkXTextField.getText();
			atkYInput = atkYTextField.getText();

			if (!atkXInput.isEmpty() && !atkYInput.isEmpty()) {

				atkXCoordinate = Integer.parseInt(atkXInput); // Converts the input into int

				atkYInput = atkYInput.toUpperCase();
				atkYColumn = atkYInput.charAt(0);
				atkYCoordinate = atkYColumn - 'A'; // Converts the string input into char

				// I added this so that you don't need to press enter on the text field.

				if (!atkYInput.isEmpty() && !atkXInput.isEmpty() && atkYColumn >= 'A' && atkYColumn <= 'J'
						&& atkXCoordinate >= 1 && atkXCoordinate <= 10) {

					System.out.println(atkXCoordinate);
					System.out.println(atkYCoordinate);

					// This IF above is pretty much saying that if the attack inputs are not empty
					// and the x and y coordinates are within boundary
					// print the attack coordinates on the console.
					// and also run the next if

					if (opponentGrid[atkYCoordinate][atkXCoordinate - 1] == 2
							|| opponentGrid[atkYCoordinate][atkXCoordinate - 1] == 3) {

						atkLabel.setText("You Already Attacked Here!!");

						// This if above checks if the coordinates in the array are equal to 2 or 3, if
						// yes, it means that was already attacked, and it displays on the label
						// If no it runs the else

					} else {

						if (opponentGrid[atkYCoordinate][atkXCoordinate - 1] == 1) {

							opponentGrid[atkYCoordinate][atkXCoordinate - 1] = 2;
							System.out.println("Hit");

							atkXTextField.setText("");

							atkYTextField.setText("");

							atkLabel.setText("Hit!");

							// This if above checks if the attack coordinates are equal to 1,
							// if yes then make it equal to 2, and display 'Hit' on the console
							// and on the label
							// it also resets the attack text fields
							// if the coordinates are not equal to 1 then it must be 0
							// which means there is not a ship there
							// in this case it runs the else below

						} else {

							opponentGrid[atkYCoordinate][atkXCoordinate - 1] = 3;

							System.out.println("Miss");
							atkXTextField.setText("");
							atkYTextField.setText("");

							atkLabel.setText("Miss");
						}

						// this else above makes the attack coordinates equal to 3, which is a miss
						// it displays a 'miss' on the console and on the label
						// it also resets the text fields

						computerX = (int) (Math.random() * 10);
						computerY = (int) (Math.random() * 10);
						System.out.println(computerX);
						System.out.println(computerY);

						System.out.println(playerGrid[computerY][computerX]);

						while (playerGrid[computerY][computerX] == 2 || playerGrid[computerY][computerX] == 3) {

							computerX = (int) (Math.random() * 10);
							computerY = (int) (Math.random() * 10);

						}

						// This block of code above generates random numbers to fit within the boundary
						// then it prints the coordinates in the console
						// these are the coordinates the computer choose to attack you
						// Then the WHILE, makes it generate a new coordinate after i attack

						if (playerGrid[computerY][computerX] == 1) {

							playerGrid[computerY][computerX] = 2;
							System.out.println("You were Hit");
						} else if (playerGrid[computerY][computerX] == 0) {

							playerGrid[computerY][computerX] = 3;
							System.out.println("Your Opponent Missed");
						}

					}

					// This block of code above runs if the coordinates the computer chose is equal
					// to 1. if it is, then it prints a message on the console saying that i was hit
					// else if the coordinates are equal to 0 then the opponent missed
					// and it prints on the console as well

				} else {

					atkLabel.setText("Invalid Coordinates!");
				}
				
				//This label appears if the attack coordinates are invalid

			}

		}

		repaint();

	}

	public void paint(Graphics g) {

		super.paint(g);

		// put instructions for pretty stuff here
		/*
		 * g.drawRect(270, 19, 1076, 501); // Background rectangle
		 * g.setColor(Color.LIGHT_GRAY); g.fillRect(271, 20, 1075, 500);
		 */

		g.setColor(Color.black); // Labels for the grids
		g.setFont(new Font("ARIAL", Font.BOLD, 16));
		g.drawString("YOUR GRID", playerGridOffset, gridsYoffset - 15);
		g.drawString("OPPONENT GRID", opponentGridOffset, gridsYoffset - 15);

		// DRAW PLAYER GRID
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				int x = playerGridOffset + (col * sqrSize);
				int y = gridsYoffset + (row * sqrSize);

				// Read array data to decide block color or if its a ship or not
				if (playerGrid[row][col] == 1) {
					g.setColor(Color.DARK_GRAY); // Ship

				} else if (playerGrid[row][col] == 2) {

					g.setColor(Color.green); // Hit ship

				} else if (playerGrid[row][col] == 3) {

					g.setColor(Color.red); // Miss ship

				} else {
					g.setColor(new Color(68, 167, 196)); // Default water

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

				if (opponentGrid[row][col] == 1) {
					g.setColor(Color.DARK_GRAY); // Ship

				} else if (opponentGrid[row][col] == 2) {

					g.setColor(Color.green); // Hit ship

				} else if (opponentGrid[row][col] == 3) {

					g.setColor(Color.red); // Miss ship

				} else {
					g.setColor(new Color(68, 167, 196)); // Default opponent water

				}

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

		window.setBounds(30, 30, 950, 600); // Use this to change the window size

		window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Use this to make the window full screen automatically

		window.getContentPane().add(myCanvas);

		window.setVisible(true);

		myCanvas.init();

	}

}