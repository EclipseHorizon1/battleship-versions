package battleship;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.*;

class MyCanvas extends JPanel {

	// declare variables here

	int[][] playerGrid; // Both 2d arrays declared.
	int[][] opponentGrid;

	int sqrSize; // Size of each square in the grids.
	int playerGridOffset; // start of x axis for the player's and opponent's grid
	int opponentGridOffset;
	int gridsYoffset; // start of Y axis for both grids so they are aligned.

	JComboBox<String> shipDropdown; //declare that this is the variable for the drop down and it will contain strings.

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
		
		setLayout(null); //this is here so that it doesn't use the default coordinates of the system for the drop down.
						//it allows me to place my own custom position.
		
		shipDropdown = new JComboBox<String>(); // Creates a new drop down.

		shipDropdown.addItem("Patrol Boat");  //These lines add each ship to the drop down.
		shipDropdown.addItem("Submarine");
		shipDropdown.addItem("Destroyer");
		shipDropdown.addItem("Battleship");
		shipDropdown.addItem("Aircraft Carrier");

		
		shipDropdown.setBounds(100, 100, 200, 30); //Here are the custom size and position of the drop down.
		add(shipDropdown); //This adds the drop down to the window
		
		revalidate(); // This tells the internal system that there is a new drop down, so it needs to recalculate the stuff.
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
		{

			
			
			
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

		// window.setBounds(30, 30, 1330, 600); // Use this to change the window size

		window.setExtendedState(JFrame.MAXIMIZED_BOTH); // Use this to make the window full screen automatically

		window.getContentPane().add(myCanvas);

		window.setVisible(true);

		myCanvas.init();

	}

}