package code;

import java.awt.Color;
import java.util.ArrayList;

public class GameModel {

	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static int currentPlayer;
	private static int actionPoints = 10;
	
	public static Player getPlayer(){
		return playerList.get(currentPlayer);
	}
	
	public static Player nextPlayer(){
		if(currentPlayer < playerList.size()-1){
			currentPlayer++;
		}
		else{
			currentPlayer = 0;
		}
		actionPoints = 10;
		return playerList.get(currentPlayer);
	}
	
	public static int getActionPoints(){
		return actionPoints;
	}
	
	
	public GameModel(){
		
	}
	
	public static ArrayList<Player> getPlayerList(){
		return playerList;
	}
	
	
	
	
	public static void main(String[] args) {
		
						
		new Board();
				
		
		Player p1 = new Player("Tom", Color.GREEN);
		Player p2 = new Player("Mike", Color.ORANGE);
		GameModel.getPlayerList().add(p1);
		GameModel.getPlayerList().add(p2);
		
		

	}

}
