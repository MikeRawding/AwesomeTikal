package code;

import java.util.ArrayList;
import java.util.HashMap;
import resources.InvalidMoveException;;

public class Tile {

	
	private HashMap<Player, ArrayList<Piece>> pieces = new HashMap<Player, ArrayList<Piece>>();
	
	private int[] sides;
	
	public void rotateClockwise(){
		
	}
	
	public void rotateCounterClockwise(){
		
	}
	
	public void remove(Player player){
		
	}
	
	public void move(Player player, Tile destination){
		
	}
	
	public void addPiece(Player player, Piece newPiece){
		
		if(!(pieces.containsKey(player))){
			pieces.put(player, new ArrayList<Piece>());
		}
		pieces.get(player).add(newPiece);
				
	}
	
	public void addPieceToBoard(Player player, Piece newPiece) throws InvalidMoveException{
		if(player.getPiecesRemaing() == 0){
			throw new InvalidMoveException("You have already exhausted your supply of explorers");
		}
		
		if(!(pieces.containsKey(player))){
			pieces.put(player, new ArrayList<Piece>());
		}
		pieces.get(player).add(newPiece);
		player.setPiecesRemaining(player.getPiecesRemaing()-1);
		
				
	}
	
	public void removePiece(Player player, Piece newPiece){
		
		if(!(pieces.containsKey(player)) || pieces.get(player).size()<1){
			//THROW SOME EXCEPTION
		}
		
		pieces.get(player).remove(0);
		
	
	}
	
	public HashMap<Player, ArrayList<Piece>> getPieces(){
		return pieces;
	}
	
	//public Player owner(){
		//THROW SOME EXCEPTION IF NO PIECES ON TILE
		//return player with most pieces

	
	
	
	
	/*
	 * 
	 * 
	 * 
	 * THIS TEST MESSAGE WAS WRITTEN IN ECLIPSE ON MIKE'S PC.
	 * 
	 * "Tarik was here"
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
}