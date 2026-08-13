package battleship;

public class Ship {

	
	
	String Name;
	int Lenght;
	int Hits;
	
	public Ship(){
		Name = "";
		Lenght = 0;
		Hits = 0;
		
	}
	
	public Ship(String shipName,int shipLenght,int shipHits) {
		
		Name = shipName; 
		Lenght = shipLenght;
		Hits = shipHits;
	}
	

}

