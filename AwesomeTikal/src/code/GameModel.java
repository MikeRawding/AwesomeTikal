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

public class GameModel implements Serializable {

	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static int currentPlayer = 0;
	private static int actionPoints = 12;
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
		actionPoints = 12;
		return playerList.get(currentPlayer);
	}
	
	public static int getActionPoints(){
		return actionPoints;
	}
	
	public static void setActionPoints(int in){
		actionPoints = in;
	}
	
	
	public GameModel(){
		
	}
	
	public static ArrayList<Player> getPlayerList(){
		return playerList;
	}
	
	
	 //Accessor method modified by daziana
    public static JLabel getCurrentAP(){
        return _currentAP;
    }

 //reduce AP method changed by daziana
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
		GameModel.TilesA.add(new Tile(new int[] {0,1,2,3,4,5}));
		GameModel.TilesA.add(new Tile(new int[] {0,1,2,3,4,5}));
		
		try {
			GameModel.nextTile();
		} catch (NoTilesRemainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Establish players
		Player p1 = new Player("Tom", Color.cyan);
		Player p2 = new Player("Mike", Color.ORANGE);
		
		GameModel.getPlayerList().add(p1);
		GameModel.getPlayerList().add(p2);
		
		
		
		
		new Board();
		
		//Game scoring methods ADDED BY DAZIANA ON 11/17
		
		/*   
		 public static scoreGame(){
		    	
		    	int play1 = 0;
		    	int play2 = 0;
		    	
		    	for (int i =0; i < "something goes inside of here";i++){
		    		//goes through each tile in each column
		    		for (int a = 0; a < col.length;a++){
		    			if (col[a]!=null){
		    				
		    				if (tile.get_play1() > tile.get_play2()){
		    					if (tile.get_pyramid_level() > 0){
		    						play1 += tile.get_pyramid_level();
		    					}
		    					else{play1++;}
		    				}
		    				else if (tile.get_play1() < tile.get_play2()){
		    					if (tile.get_pyramid_level() > 0){
		    						play2 += tile.get_pyramid_level();
		    					}
		    					else{play2++;}
		    				}
		    			}
		    		}
		    	}
		    	if (play1 > play2){
		    		JOptionPane.showMessageDialog(null, "Player1 WINS, your score is "+play1);
		    	}
		    	else if (play2 > play1){
		    		JOptionPane.showMessageDialog(null, "Player2 WINS, your score is "+play2);
		    		

		    	}// when play1 = play2 its a draw
		    	else{
		    		JOptionPane.showMessageDialog(null, "Draw!");
		    		}
		    	
		    	
		    }
				*/

		
		
		
		
		

	}

}
