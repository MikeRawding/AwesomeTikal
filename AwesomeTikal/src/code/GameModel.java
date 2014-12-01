package code;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import resources.InvalidMoveException;
import resources.NoTilesRemainException;

public class GameModel implements Serializable {

	private static ArrayList<Player> playerList = new ArrayList<Player>();
	private static int currentPlayer = 0;
	private static int actionPoints = 12;
	private static JLabel _currentAP;
	private static Tile onDeckTile;
	public static JPanel selectedTile;
	private static int columnHeight;
	private static Stack<Tile> deck;
	private static int[] templePieces = new int[11];
	private static int templesPlaced = 0;
	
	public static void setColumnHeight(){
		System.out.println("Number of players is: " + playerList.size());
	
		int i = 0;
		while((((i+(i-1)) * (i/2.0)) / playerList.size()) < 15){
			i++;
		}
		columnHeight = i;
		System.out.println("columnHeight set to: " + columnHeight);
	}
	
	
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
		templesPlaced = 0;
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
		if(!deck.isEmpty()){
			onDeckTile = deck.pop();
			System.out.println("Tiles remaining: " + deck.size());
			return onDeckTile;
		}
		else{
			throw new NoTilesRemainException("");
		}
	}
	
	public static int getColumnHeight(){
		return columnHeight;
	}

	public static void initTemplePieces(){
		templePieces[1] = 0;
		templePieces[2] = 6;
		templePieces[3] = 6;
		templePieces[4] = 6;
		templePieces[5] = 10;
		templePieces[6] = 8;
		templePieces[7] = 7;
		templePieces[8] = 6;
		templePieces[9] = 3;
		templePieces[10] = 3;
		
	}

	public static void initDeck(){
		
		deck = new Stack<Tile>();
		int boardSize = (columnHeight * columnHeight) - (columnHeight/2);
		if(columnHeight % 2 == 1){
			boardSize--;
		}
		
		System.out.println("Board size is: " + boardSize);
		int p = columnHeight + playerList.size();
		
		
		for(int i = 0; i < p; i++){
			deck.push(new Temple(randomSides(),1));
		}
		for(int i = 0; i < p - 2; i++){
			deck.push(new Temple(randomSides(),2));
		}
		for(int i = 0; i < p - 4; i++){
			deck.push(new Temple(randomSides(),3));
		}
		for(int i = 0; i < p - 6; i++){
			deck.push(new Temple(randomSides(),4));
		}
		for(int i = 0; i < playerList.size(); i++){
			deck.push(new Volcano (new int[] {0,0,0,0,0,0}));
		}
		while(deck.size() < boardSize){
			deck.push(new Tile(randomSides()));
		}
		System.out.println(deck.size());
		Collections.shuffle(deck);
		onDeckTile = deck.pop();
		
	}
	
	
	private static int[] randomSides(){
		int[] result = new int[6];
		int sidesWithPaths;
		Random ran = new Random();
		
		do{
			sidesWithPaths = 0;
			for(int i = 0; i < 6; i++){
				result[i] = ran.nextInt(3);
				if(result[i] != 0){
					sidesWithPaths++;
				}
			}
		}	
		while(sidesWithPaths > 3);	
		
		
		return result;
	}
	
	public static Tile getOnDeckTile(){
		return onDeckTile;
	}

	public static void removeTemplePiece(int value) throws InvalidMoveException{
		if(templePieces[value] < 1){
			throw new InvalidMoveException("No Temples Pieces of :" + value + "remain");
		}
		else if(templesPlaced > 1){
			throw new InvalidMoveException("You have already placed 2 Temple Pieces this turn");
		}
		else if(actionPoints < 1){
			throw new InvalidMoveException("You do not have enough Action Points");
		}
		else{
			templePieces[value]--;
			templesPlaced++;
			actionPoints--;
		}
	}
	
	
	public static void main(String[] args) {
		
			
		
		
			
		//Establish players
		Player p1 = new Player("Tom", Color.cyan);
		Player p2 = new Player("Mike", Color.ORANGE);
		Player p3 = new Player("Tarik", Color.blue);
		Player p4 = new Player("Taiga", Color.GRAY);
		
		GameModel.getPlayerList().add(p1);
		GameModel.getPlayerList().add(p2);
		GameModel.getPlayerList().add(p3);
		GameModel.getPlayerList().add(p4);
		
		
		setColumnHeight();
		
		initDeck();
		initTemplePieces();
		
		new Board();
		
		
		
		
		
		
		
		
		//Game scoring methods ADDED BY DAZIANA ON 11/17
		
		/*   
		 public static scoreGame(){
		    	
		    	int play1 = 0;
		    	int play2 = 0;
		    	
		    	HashMap<Player, Integer> scores = new HashMap<Player, Integer>();
		    	
		    	
		    	for (int x =0; x < grid.length;x++){
		    		//goes through each tile in each column
		    		for (int y = 0; y < grid[0].length;y++){
		    			if (grid[x][y]isatemple){
		    				
		    				scores.put(tile.owner,pieces.get(tile.owner)+grid[x][y].getValue());
		    				
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
		    	return max value in score(hashmap)
		    	
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
