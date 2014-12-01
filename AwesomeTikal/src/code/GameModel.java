package code;

import java.awt.Color;
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
	private static Tile onDeckTile;
	public static JPanel selectedTile;
	private static int columnHeight;
	private static Stack<Tile> deck;
	private static int[] templePieces = new int[11];
	private static int templesPlaced = 0;
	
	private static void setColumnHeight(){
		System.out.println("Number of players is: " + playerList.size());
	
		int i = 0;
		while((((i+(i-1)) * (i/2.0)) / playerList.size()) < 15){
			i++;
		}
		columnHeight = i;
		System.out.println("columnHeight set to: " + columnHeight);
	}
	/**
	 * 
	 * @return The Player whose turn it currently is.
	 */
	public static Player getCurrentPlayer(){
		return playerList.get(currentPlayer);
	}
	/**
	 * Changes currentPlayer to the next Player in line.
	 * @return The new currentPlayer
	 */
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
	
	/**
	 * 
	 * @return Current Action Points.
	 */
	public static int getActionPoints(){
		return actionPoints;
	}
	
	/**
	 * Mutator for actionPoints
	 * 
	 * @param in Desired Action Points.
	 */
	public static void setActionPoints(int in){
		actionPoints = in;
	}
	
	
	public GameModel(){
		
	}
	
	/**
	 * 
	 * @return List of Players in the game.
	 */
	public static ArrayList<Player> getPlayerList(){
		return playerList;
	}
	
	/**
	 * Sets the On Deck Tile to the next Tile in the deck.
	 * @return The new On Deck Tile
	 * @throws NoTilesRemainException No Tiles are left in the deck (game over).
	 */
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
	
	/**
	 * 
	 * @return The height of the odd(longer) columns
	 */
	public static int getColumnHeight(){
		return columnHeight;
	}

	private static void initTemplePieces(){
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

	private static void initDeck(){
		
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
		while(deck.size() < boardSize + 1){
			deck.push(new Tile(randomSides()));
		}
		System.out.println(deck.size());
		Collections.shuffle(deck);
		while(deck.peek() instanceof Volcano){
			Collections.shuffle(deck);
		}
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
	
	/**
	 * 
	 * @return On Deck Tile
	 */
	public static Tile getOnDeckTile(){
		return onDeckTile;
	}

	/**
	 * Removes a temple piece of the input value from the array of available pieces
	 * 
	 * @param value
	 * @throws InvalidMoveException If no pieces of that value remain, two Temples have been placed this turn, or insufficient AP.
	 */
	public static void removeTemplePiece(int value) throws InvalidMoveException{
		if(templePieces[value] < 1){
			throw new InvalidMoveException("No Temples Pieces of :" + value + "remain");
		}
		else if(templesPlaced > 1){
			throw new InvalidMoveException("You have already placed 2 Temple Pieces this turn");
		}
		else if(actionPoints < 3){
			throw new InvalidMoveException("You do not have enough Action Points");
		}
		else{
			templePieces[value]--;
			templesPlaced++;
			actionPoints -= 3;
		}
	}
	
	private static Color randColor(){
		Random r = new Random();
		float red = r.nextFloat();
		if(red < 0.3){
			red += 0.5;
		}
		float green = r.nextFloat();
		if(green < 0.3){
			green += 0.5;
		}
		float blue = r.nextFloat();
		if(blue < 0.3){
			blue += 0.5;
		}
		return new Color(red, green, blue);
	}
	
	/**
	 * Adds players to the Player List, makes adjustments for board size, initializes Deck, initializes and displays Board.
	 * 
	 * @param args Player names separated by spaces
	 */
	public static void main(String[] args) {
		
			
		if(args.length == 0){
			JOptionPane.showMessageDialog(null, "Please run with player names for command line arguments");
			System.exit(0);
		}
		
		for(int i = 0; i < args.length; i++){
			GameModel.getPlayerList().add(new Player(args[i],GameModel.randColor()));
		}
		
		setColumnHeight();
		initDeck();
		initTemplePieces();
		
		new Board();

	}
}
