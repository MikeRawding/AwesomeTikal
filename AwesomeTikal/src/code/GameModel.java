package code;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import resources.NoTilesRemainException;

public class GameModel {

	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static int currentPlayer;
	private static int actionPoints = 10;
	private static JLabel _currentAP;
	public static Tile onDeckTile;
	public static JPanel selectedTile;
	
	
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
	
	
	 //changed
    public static JLabel getCurrentAP(){
        return _currentAP;
    }

 //changed
        public void reduceAP(int i){
            if (actionPoints >=i) {
            actionPoints  = actionPoints-i;
             _currentAP.setText(String.valueOf(actionPoints));
           } else {
               //message pops up
               JOptionPane.showMessageDialog(null, "Sorry Not Enough Action Points ");
        }
    }
	
	public static Tile nextTile() throws NoTilesRemainException{
		if(TilesA.size() > 0){
			Random r = new Random();
			onDeckTile = TilesA.remove(r.nextInt(TilesA.size()));
			return onDeckTile;
		}
		else if(TilesB.size() > 0){
			Random r = new Random();
			onDeckTile = TilesB.remove(r.nextInt(TilesB.size()));
			return onDeckTile;
		}
		else if(TilesC.size() > 0){
			Random r = new Random();
			onDeckTile = TilesC.remove(r.nextInt(TilesC.size()));
			return onDeckTile;
		}
		else if(TilesD.size() > 0){
			Random r = new Random();
			onDeckTile = TilesD.remove(r.nextInt(TilesD.size()));
			return onDeckTile;
		}
		else{
			throw new NoTilesRemainException("");
		}
	}
	
	public static ArrayList<Tile> TilesA = new ArrayList<Tile>();
	public static ArrayList<Tile> TilesB = new ArrayList<Tile>();;
	public static ArrayList<Tile> TilesC = new ArrayList<Tile>();;
	public static ArrayList<Tile> TilesD = new ArrayList<Tile>();;
	
	public static void main(String[] args) {
		
			
		
		//Establish tiles
		GameModel.TilesA.add(new Tile(new int[] {1,1,1,1,4,5}));
		GameModel.TilesA.add(new Tile(new int[] {2,2,2,2,4,5}));
		GameModel.TilesA.add(new Tile(new int[] {3,3,3,3,4,5}));
		GameModel.TilesA.add(new Tile(new int[] {0,1,2,3,4,5}));
		GameModel.TilesA.add(new Tile(new int[] {0,1,2,3,4,5}));
		
		try {
			GameModel.nextTile();
		} catch (NoTilesRemainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Board();
				
		//Establish players
		Player p1 = new Player("Tom", Color.GREEN);
		Player p2 = new Player("Mike", Color.ORANGE);
		GameModel.getPlayerList().add(p1);
		GameModel.getPlayerList().add(p2);
		
		
		
		
		
		

	}

}
