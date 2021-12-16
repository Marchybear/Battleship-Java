package battleship;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import battleship.Attack.*;
import battleship.Model.Board;
import battleship.Model.Constants;
import battleship.Model.Coordinate;
import battleship.Model.Ship;

public class Main {
	AI ai;

	@Override
	public boolean equals(Object obj) {
		return (this == obj);
	}

	public static void main(String[] args) {

		// Scanner input = new Scanner(System.in);

		// DumbAI ai = new DumbAI();

		// Board testBoard = new Board(Constants.boardSize);

		// Ship carrier = new Ship("Carrier", 5);
		// Ship destroyer = new Ship("Destroyer", 2);

		// Ship[] shipArr = new Ship[] { carrier, destroyer };

		// HashMap<String, Ship> ships = ai.placeShips(shipArr, testBoard);

		// while (ships.get("Carrier").getShipSurvivingPoints() > 0) {

		// 	testBoard.drawBoard(testBoard);

		// 	System.out.println("Enter a coordinate:");


			// convert inputted coord into an array of 2 ints

			// String coord = input.nextLine().toUpperCase();

			// int col = Integer.parseInt(coord.substring(1)) - 1;
			// int row = ((int) coord.charAt(0)) - 65;
			
			// Coordinate numcoord = new Coordinate(row,col);


			//convert num coordinate to (letter, number)

			// Coordinate numcoord = new Coordinate(ai.attack().getRow(), ai.attack().getColumn());

			// System.out.println("Ai attacks "+numcoord);


			// String letter = String.valueOf((char)numcoord.getRow()).toUpperCase();

			// char letter = (char)(numcoord.getRow()+65);
			// System.out.println(letter);

			// String num = String.valueOf(numcoord.getColumn());
			// System.out.println(numcoord.getColumn());
        	// String coord = letter+num;

			// System.out.println("Ai attacks "+coord);

			// String next = input.next();

			// System.out.println(ships.get("Carrier").getShipPoints().size());
			// for (Coordinate p : ships.get("Carrier").getShipPoints()) {
			// 	System.out.println(p);
			// }

		// 	System.out.println(numcoord);

		// 	if (ships.get("Carrier").getShipPoints().contains(numcoord)) { // need to override equals for this to work,
		// 																	// working on that...
		// 		carrier.setShipLength(carrier.getShipLength() - 1); // temp, will create a checkShipHit method +
		// 															// shipSunk
		// 		System.out.println("Hit!");
		// 	} else {

		// 		System.out.println("Miss!");
		// 	}
		// 	testBoard.getPoint(numcoord.getRow(), numcoord.getColumn()).setIsHit(true);

		// }
		// System.out.println("Carrier is down.");



        
		// before game setup (menu)

		// prompts user to load save or start a new game

		Scanner input = new Scanner(System.in);


		System.out.println("complex (1) or simple (2) ai?");
		int gameMode = input.nextInt();
		DumbAI ai = new DumbAI();

		// set the ai

		System.out.println("Who goes first? you (1) or ai (2)?");
		int firstMove = input.nextInt();

		// turn gameplay loop
		int enemyHits = 0;
		int enemyMisses = 0;
		int ourHits = 0;
		int ourMisses = 0;

		Board ourBoard = new Board(Constants.boardSize);
		// place 5 ships on ourboard

		Ship carrier = new Ship("Carrier", 5);
		Ship destroyer = new Ship("Destroyer", 2);

		Board enemyBoard = new Board(Constants.boardSize);


		Ship[] shipArr = new Ship[] { carrier, destroyer };
		HashMap<String, Ship> ships = ai.placeShips(shipArr, ourBoard);


		// while enemyhits or ourhits are less than 17
    
		while (enemyHits < 17 && ourHits < 17) {

			ourBoard.drawBoard(ourBoard);

			// player turn
			if (firstMove == 1) {
				System.out.println("Enter a coordinate (letter-number format):");
				String coord = input.nextLine().toUpperCase();

				int col = Integer.parseInt(coord.substring(1)) - 1;
				int row = ((int) coord.charAt(0)) - 65;

				Coordinate numcoord = new Coordinate(row,col);

				System.out.println("You attacked" + numcoord);


				// if player hit
				// if numcoord coord on the ourboard is not deflaut
				if (!ourBoard.getPoint(numcoord.getRow(), numcoord.getColumn()).getShipId().equals("default")) {
					if (ourBoard.getPoint(numcoord.getRow(), numcoord.getColumn()).getIsHit())// call checkIfHit
					// tell user, which ship is hit
					System.out.println("YOU HIT MY" + ourBoard.getPoint(numcoord.getRow(), numcoord.getColumn()).getShipId());// check which ship is hit and which ship is
					carrier.setShipLength(carrier.getShipLength() - 1);
					enemyHits++;

					// if sunk
					if (ships.get(ourBoard.getPoint(numcoord.getRow(), numcoord.getColumn()).getShipId()).getShipSurvivingPoints() < 0){
						 System.out.println("YOU'VE SUNK MY"); 						 //u sunk my ship id

					}
					// call check if sunk
					// if shipIsSunk is true{
					
					// }

					// if player miss
				} else {
					System.out.println("Missed ;)");
					enemyMisses++;
				}
			}
			// prompt user to save, or save and exit

			// ai turn
			else {
				Coordinate numcoord = new Coordinate(ai.attack().getRow(), ai.attack().getColumn());

				System.out.println("Ai attacks "+numcoord);
				

				System.out.println("hit(1) or miss(2)?");
				int userinput = input.nextInt();

				// if ai hit
				if (userinput == 1) {

					System.out.println("Which ship?");
					String[] theShips = { "Carrier", "BattleShip", "Cruiser", "Submarine", "Destroyer" };

					// prints shipIDs alongside numbers
					for (int i = 0; i < theShips.length; i++) {
						System.out.println((i + 1) + " " + theShips[i]);
					}
					userinput = input.nextInt();
					enemyBoard.getPoint(numcoord.getRow(), numcoord.getColumn()).setIsHit(true);
					// updating the enemyboard
					enemyBoard.getPoint(numcoord.getRow(), numcoord.getColumn()).setShipId(theShips[userinput-1]);
					// add point to shipPoints

					ourHits++;

					// ask if ship that was hit, has suck
					System.out.println("Not sunk(1) or has sunk(2)?");
					userinput = input.nextInt();

					// if sunk
					if (userinput == 2) {
						// set all points of said enemyShip to sunk
					}

					// if ai miss
				} else {
					// enemyBoard.getPoint(numcoord[0],numcoord[1]).setIsTaken(true));
					ourMisses++;

				}

			}
		} // end of while gameplay loop

	}

}
