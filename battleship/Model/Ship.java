package battleship.Model;
import java.util.ArrayList;

public class Ship {
    private int shipLength;
    private String shipName;
    private Board board;
    private ArrayList<Coordinate> shipPoints = new ArrayList<Coordinate>();

    public Ship(String shipName, int shipLength){
        this.shipLength = shipLength;
        this.shipName = shipName;
    }

	public Ship(Board board, String shipName, int shipLength, int startRow, int startCol, String orientation) {
        this.shipLength = shipLength;
		this.board = board;
		this.shipName = shipName;

        if(orientation.equals("DOWN")){
			for (int i = 0; i < shipLength; i++) {
				int row = startRow + i;
				Coordinate coord = new Coordinate(row, startCol);

                shipPoints.add(coord);                 
                
				board.getPoint(row, startCol).setShipId(shipName);
				board.getPoint(row, startCol).setIsTaken(true);
            }
        }
        else{
			for (int i = 0; i < shipLength; i++) {
				int col = startCol + i;
				Coordinate coord = new Coordinate(startRow, col);
                shipPoints.add(coord); 

				board.getPoint(startRow, col).setShipId(shipName);
				board.getPoint(startRow, col).setIsTaken(true);
            }
        }
    }




    public ArrayList<Coordinate> getShipPoints(){
        return shipPoints;
    }

/*     public void printShipPoints(){
        for(int i = 0; i<shipLength; i++){
            System.out.println(Arrays.toString(shipPoints.get(i)));
        }
    } */

    public int getShipLength(){
        return shipLength;
    }

    public void setShipLength(int length){
        shipLength =  length;
    }

    public String getShipName(){
        return shipName;
    }

    public void setShipName(String name){
        shipName  = name;
    }

	public int getShipSurvivingPoints() {
		int sum = 0;
		for (Coordinate c : shipPoints) {
			if (!this.board.getPoint(c.getRow(), c.getColumn()).getIsHit())
				sum++;
		}
		return sum;
	}

}
