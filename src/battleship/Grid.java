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
	JComboBox<Ship> shipDropdown; // Dropdown containing the Ship objects so the player can select which ship to
									// place.
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

	boolean computerHorizontal;
	int computerRandomDirection;

	boolean computerHboundary;
	boolean computerVboundary;
	boolean computerShipThere;

	JLabel opponentAtkLabel;
	JLabel winORloseLabel;

	boolean opponentShipsLeft;
	boolean playerShipsLeft;

	JLabel xTextfieldLabel;
	JLabel yTextfieldLabel;
	JLabel atkXlabel;
	JLabel atkYlabel;
	JLabel chooseyourboat;
	JLabel attackLabel;

	public void init() {

		playerGrid = new int[10][10];
		opponentGrid = new int[10][10];

		sqrSize = 40;
		playerGridOffset = 300;
		opponentGridOffset = 920;
		gridsYoffset = 160;

		// Initialise every square in both 10x10 grids as water (0).

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				playerGrid[row][col] = 0;
				opponentGrid[row][col] = 0;
			}
		}

		setLayout(null); // this is here so that it doesn't use the default coordinates of the system for
							// the drop down.
							// it allows me to place my own custom position.

		shipDropdown = new JComboBox<Ship>(); // Creates a new drop down.

		shipDropdown.addItem(patrolBoat); // These lines add each ship to the drop down.
		shipDropdown.addItem(submarine);
		shipDropdown.addItem(destroyer);
		shipDropdown.addItem(battleship);
		shipDropdown.addItem(aircraftCarrier);

		shipDropdown.setBounds(50, 200, 180, 30); // Here are the custom size and position of the drop down.
		shipDropdown.addActionListener(this); // this calls on the action listener methods to do something.
		add(shipDropdown); // This adds the drop down to the window

		xTextField = new JTextField(); // This adds the text fields and determines the background,bounds and location
		xTextField.setBackground(new Color(230, 230, 230));
		xTextField.setBounds(50, 250, 180, 40);
		xTextField.addActionListener(this);
		add(xTextField);

		yTextField = new JTextField();
		yTextField.setBackground(new Color(230, 230, 230));
		yTextField.setBounds(50, 310, 180, 40);
		yTextField.addActionListener(this);
		add(yTextField);

		horizontalButton = new Button("HORIZONTAL");
		horizontalButton.setBackground(Color.lightGray);
		horizontalButton.setBounds(50, 360, 180, 40); // (x start, y start, width, height)
		horizontalButton.addActionListener(this);
		add(horizontalButton);

		verticalButton = new Button("VERTICAL");
		verticalButton.setBackground(Color.lightGray);
		verticalButton.setBounds(50, 410, 180, 40);
		verticalButton.addActionListener(this);
		add(verticalButton);

		placeShip = new Button("PLACE SHIP");
		placeShip.setBackground(Color.lightGray);
		placeShip.setBounds(50, 460, 180, 40);
		placeShip.addActionListener(this);
		add(placeShip);

		attack = new Button("ATTACK");
		attack.setBackground(Color.lightGray);
		attack.setBounds(710, 360, 175, 40);
		attack.addActionListener(this);
		add(attack);

		atkXTextField = new JTextField();
		atkXTextField.setBackground(new Color(230, 230, 230));
		atkXTextField.setBounds(710, 250, 175, 40);
		atkXTextField.addActionListener(this);
		add(atkXTextField);

		atkYTextField = new JTextField();
		atkYTextField.setBackground(new Color(230, 230, 230));
		atkYTextField.setBounds(710, 310, 175, 40);
		atkYTextField.addActionListener(this);
		add(atkYTextField);

		atkLabel = new JLabel("");
		atkLabel.setBounds(730, 405, 250, 30);
		add(atkLabel);

		placementLabel = new JLabel("");
		placementLabel.setBounds(50, 510, 300, 30);
		add(placementLabel);

		opponentAtkLabel = new JLabel("");
		opponentAtkLabel.setBounds(730, 430, 250, 30);
		add(opponentAtkLabel);

		winORloseLabel = new JLabel("");
		winORloseLabel.setBounds(730, 500, 250, 30);
		add(winORloseLabel);

		xTextfieldLabel = new JLabel("X Coordinate");
		xTextfieldLabel.setBounds(50, 225, 250, 30);
		add(xTextfieldLabel);

		yTextfieldLabel = new JLabel("Y Coordinate");
		yTextfieldLabel.setBounds(50, 285, 250, 30);
		add(yTextfieldLabel);

		atkXlabel = new JLabel("X Coordinate");
		atkXlabel.setBounds(710, 225, 250, 30);
		add(atkXlabel);

		atkYlabel = new JLabel("Y Coordinate");
		atkYlabel.setBounds(710, 285, 250, 30);
		add(atkYlabel);

		chooseyourboat = new JLabel("CHOOSE YOUR SHIP");
		chooseyourboat.setBounds(50, 175, 250, 30);
		add(chooseyourboat);

		attackLabel = new JLabel("ATTACK:");
		attackLabel.setBounds(710, 200, 250, 30);
		add(attackLabel);

		revalidate();// Updates the layout after the interface components have been added.
		repaint(); // Redraws the panel so the interface is displayed.

	}
	// Creating the each ship as an object

	Ship patrolBoat = new Ship("Patrol Boat", 2, 0);
	Ship submarine = new Ship("Submarine", 3, 0);
	Ship destroyer = new Ship("Destroyer", 3, 0);
	Ship battleship = new Ship("Battleship", 4, 0);
	Ship aircraftCarrier = new Ship("Aircraft Carrier", 5, 0);

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == shipDropdown) {

			// When a ship is selected, get its length from the Ship object
			// and store it so it can be used during placement.

			if (shipDropdown.getSelectedItem() != null) {

				selectedShipLength = ((Ship) shipDropdown.getSelectedItem()).shipLength();
				System.out.println(selectedShipLength);
			}
		}

		if (e.getSource() == xTextField) {
			// Reads the X coordinate, converts it from text to an integer,
			// and checks whether it is within the valid range of 1-10.

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

		if (e.getSource() == yTextField) {

			// Reads the Y coordinate and converts it to uppercase.
			// A-J is checked as the valid range, then subtracting 'A'
			// converts the letter into an array index from 0-9.
			// For example, 'C' (67) - 'A' (65) gives array index 2.

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

		// Stores the direction chosen by the player.
		// true represents horizontal and false represents vertical.

		if (e.getSource() == horizontalButton) {

			horizontal = true;

		}

		if (e.getSource() == verticalButton) {

			horizontal = false;

		}

		if (e.getSource() == placeShip) {

			xInput = xTextField.getText();
			yInput = yTextField.getText();

			if (!xInput.isEmpty() && !yInput.isEmpty()) {

				xShipCoordinate = Integer.parseInt(xInput);

				yInput = yInput.toUpperCase();
				yColumn = yInput.charAt(0);
				yShipCoordinate = yColumn - 'A';

				// Reads the coordinate fields again when the Place Ship button is pressed,
				// so the player doesn't need to press Enter in each text field.

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

				// Checks whether the ship will stay inside the grid
				// using its starting coordinate, length and chosen direction.

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

				// Checks every square that the ship would occupy.
				// If any of those squares already contains a ship (1),
				// shipThere becomes true and the placement is rejected.

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

					// If the ship fits inside the grid and does not overlap another ship,
					// each square it occupies is changed to 1.
					// Horizontal placement changes X, while vertical placement changes Y.

					placeComputerShip();

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

			// After a valid placement, the coordinate fields are cleared
			// and the placed ship is removed from the dropdown.
			// The label shows whether the ship was placed, all ships are placed,
			// or the attempted placement was invalid.
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

		if (e.getSource() == attack) {

			atkXInput = atkXTextField.getText();
			atkYInput = atkYTextField.getText();

			if (!atkXInput.isEmpty() && !atkYInput.isEmpty()) {

				atkXCoordinate = Integer.parseInt(atkXInput); // Converts the input into int

				atkYInput = atkYInput.toUpperCase();
				atkYColumn = atkYInput.charAt(0);
				atkYCoordinate = atkYColumn - 'A'; // Converts the string input into char

				if (!atkYInput.isEmpty() && !atkXInput.isEmpty() && atkYColumn >= 'A' && atkYColumn <= 'J'
						&& atkXCoordinate >= 1 && atkXCoordinate <= 10) {

					System.out.println(atkXCoordinate);
					System.out.println(atkYCoordinate);

					// Values 2 and 3 show that this square has already been attacked,
					// so the player is prevented from attacking the same square twice.
					if (opponentGrid[atkYCoordinate][atkXCoordinate - 1] == 2
							|| opponentGrid[atkYCoordinate][atkXCoordinate - 1] == 3) {

						atkLabel.setText("You Already Attacked Here!!");

					} else {
						// If the square contains a ship (1), it becomes a hit (2).
						// Otherwise the water square becomes a miss (3).
						// The text fields are then cleared and the result is displayed.
						if (opponentGrid[atkYCoordinate][atkXCoordinate - 1] == 1) {

							opponentGrid[atkYCoordinate][atkXCoordinate - 1] = 2;
							System.out.println("Hit");

							atkXTextField.setText("");

							atkYTextField.setText("");

							atkLabel.setText("Hit!");

						} else {

							opponentGrid[atkYCoordinate][atkXCoordinate - 1] = 3;

							System.out.println("Miss");
							atkXTextField.setText("");
							atkYTextField.setText("");

							atkLabel.setText("Miss");
						}

						// After the player's valid attack, the computer takes its turn
						// and the game checks whether either player has won.

						computerAttack();
						checkWinLose();
					}

				} else {

					atkLabel.setText("Invalid Coordinates!");
				}

				// This label appears if the attack coordinates are invalid

			}

		}

		repaint();

	}

	public void computerAttack() {
		// Generates random coordinates for the computer's attack.
		// If the chosen square was already attacked, new coordinates are generated
		// until an unused square is found.

		computerX = (int) (Math.random() * 10);
		computerY = (int) (Math.random() * 10);
		System.out.println(computerX);
		System.out.println(computerY);

		System.out.println(playerGrid[computerY][computerX]);

		while (playerGrid[computerY][computerX] == 2 || playerGrid[computerY][computerX] == 3) {

			computerX = (int) (Math.random() * 10);
			computerY = (int) (Math.random() * 10);

		}

		// Makes the computer attack as either a hit (2) or miss (3)
		// and displays the result to the player.

		if (playerGrid[computerY][computerX] == 1) {

			playerGrid[computerY][computerX] = 2;

			opponentAtkLabel.setText("You were Hit");
			System.out.println("You were Hit");
		} else if (playerGrid[computerY][computerX] == 0) {

			playerGrid[computerY][computerX] = 3;
			opponentAtkLabel.setText("Your Opponent Missed");
			System.out.println("Your Opponent Missed");
		}

	}

	public void placeComputerShip() {
		// Randomly chooses a starting coordinate and direction for the computer's ship.
		// The position is checked for grid boundaries and existing ships.
		// If it is invalid, new random values are generated until a valid position is
		// found,
		// then the ship is placed onto opponentGrid.

		computerShipX = (int) (Math.random() * 10);
		computerShipY = (int) (Math.random() * 10);
		computerRandomDirection = (int) (Math.random() * 2);

		if (computerRandomDirection == 1) {

			computerHorizontal = true;
		} else {

			computerHorizontal = false;
		}

		System.out.println(computerShipX);
		System.out.println(computerShipY);

		System.out.println(opponentGrid[computerShipY][computerShipX]);
		computerHboundary = false;
		computerVboundary = false;

		if (computerHorizontal) {
			if (computerShipX + selectedShipLength - 1 >= 0 && computerShipX + selectedShipLength - 1 <= 9) {

				computerHboundary = true;
			}
		}

		if (!computerHorizontal) {
			if (computerShipY + selectedShipLength - 1 >= 0 && computerShipY + selectedShipLength - 1 <= 9) {

				computerVboundary = true;
			}
		}

		computerShipThere = false; // Assume there is no overlap until one is found.

		if (computerHboundary || computerVboundary) {

			if (computerHorizontal) {
				for (int i = 0; i < selectedShipLength; i++) {

					if (opponentGrid[computerShipY][computerShipX + i] == 1) {

						computerShipThere = true;

					}
				}
			}

			if (!computerHorizontal) {
				for (int i = 0; i < selectedShipLength; i++) {
					if (opponentGrid[computerShipY + i][computerShipX] == 1) {
						computerShipThere = true;
					}
				}

			}

		}

		while (!(computerVboundary || computerHboundary) || computerShipThere) {
			computerShipX = (int) (Math.random() * 10);
			computerShipY = (int) (Math.random() * 10);
			computerRandomDirection = (int) (Math.random() * 2);

			if (computerRandomDirection == 1) {

				computerHorizontal = true;
			} else {

				computerHorizontal = false;
			}

			System.out.println(computerShipX);
			System.out.println(computerShipY);

			System.out.println(opponentGrid[computerShipY][computerShipX]);
			computerHboundary = false;
			computerVboundary = false;

			if (computerHorizontal) {
				if (computerShipX + selectedShipLength - 1 >= 0 && computerShipX + selectedShipLength - 1 <= 9) {

					computerHboundary = true;
				}
			}

			if (!computerHorizontal) {
				if (computerShipY + selectedShipLength - 1 >= 0 && computerShipY + selectedShipLength - 1 <= 9) {

					computerVboundary = true;
				}
			}

			computerShipThere = false; // Assume there is no overlap until one is found.

			if (computerHboundary || computerVboundary) {

				if (computerHorizontal) {
					for (int i = 0; i < selectedShipLength; i++) {

						if (opponentGrid[computerShipY][computerShipX + i] == 1) {

							computerShipThere = true;

						}
					}
				}

				if (!computerHorizontal) {
					for (int i = 0; i < selectedShipLength; i++) {
						if (opponentGrid[computerShipY + i][computerShipX] == 1) {
							computerShipThere = true;
						}
					}

				}

			}

		}

		if ((computerVboundary || computerHboundary) && !computerShipThere) {

			if (computerHorizontal) {
				for (int i = 0; i < selectedShipLength; i++) {

					opponentGrid[computerShipY][computerShipX + i] = 1;

				}
			}

			if (!computerHorizontal) {
				for (int i = 0; i < selectedShipLength; i++) {

					opponentGrid[computerShipY + i][computerShipX] = 1;

				}

			}
		}

	}

	public void checkWinLose() {
		// Searches both grids for any remaining ship squares (1).
		// If a grid has no ship squares remaining, that player has lost.

		opponentShipsLeft = false;
		playerShipsLeft = false;

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {

				if (opponentGrid[row][col] == 1) {

					opponentShipsLeft = true;

				}

				if (playerGrid[row][col] == 1) {

					playerShipsLeft = true;

				}

			}
		}

		if (!opponentShipsLeft) {

			winORloseLabel.setText("You Win");
		}

		if (!playerShipsLeft) {

			winORloseLabel.setText("You Lost");

		}

	}

	public void paint(Graphics g) {

		super.paint(g);

		g.setColor(Color.black); // Labels for the grids
		g.setFont(new Font("ARIAL", Font.BOLD, 20));
		g.drawString("YOUR GRID", playerGridOffset, gridsYoffset - 25);
		g.drawString("OPPONENT GRID", opponentGridOffset, gridsYoffset - 25);

		for (int col = 0; col < 10; col++) {
			g.drawString(String.valueOf(col + 1), playerGridOffset + col * sqrSize + 15, gridsYoffset - 5);
		}

		for (int row = 0; row < 10; row++) {
			char letter = (char) ('A' + row);
			g.drawString(String.valueOf(letter), playerGridOffset - 20, gridsYoffset + row * sqrSize + 25);
		}

		for (int col = 0; col < 10; col++) {
			g.drawString(String.valueOf(col + 1), opponentGridOffset + col * sqrSize + 15, gridsYoffset - 5);
		}

		for (int row = 0; row < 10; row++) {
			char letter = (char) ('A' + row);
			g.drawString(String.valueOf(letter), opponentGridOffset - 20, gridsYoffset + row * sqrSize + 25);
		}

		// Draws numbers 1-10 across the top and letters A-J down the side
		// of both grids so the player can identify each coordinate.

		// DRAW PLAYER GRID
		// Grid values:
		// 0 = water, 1 = ship, 2 = hit, 3 = miss.
		// Each value is drawn using a different colour.

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
					g.setColor(new Color(68, 167, 196)); // Hide opponent ship as water

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

		g.setFont(new Font("ARIAL", Font.BOLD, 32));
		g.drawString("BATTLESHIPS", 600, 60);

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