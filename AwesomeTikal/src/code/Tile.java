package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import resources.*;

public class Tile {

	
	protected HashMap<Player, ArrayList<Piece>> pieces = new HashMap<Player, ArrayList<Piece>>();
	
	private int[] sides;
	
	public void rotateClockwise(){
		//decrements the values in sides[]. i.e. sides[0] gets sides[1], sides[sides.length] gets sides[0]
		int temp = sides[0];
		for(int i = 0; i < sides.length-1; i++){
			sides[i] = sides[i+1];
		}
		sides[sides.length] = temp;	
	}
	
	public void rotateCounterClockwise(){
		//increments the values in sides[]. i.e. sides[1] gets sides[0], sides [0] gets sides[sides.length]
		int temp = sides[sides.length];
		for(int i = sides.length; i > 0; i--){
			sides[i]=sides[i-1]; 
		}
		sides[0] = temp;
	}
		
	public static void move(Player player,Tile location, Tile destination) throws InvalidMoveException{
		//removes a piece from the ArrayList in the HashMap for player in the location tile and adds said Piece to destination
		destination.addPiece(player, location.removePiece(player));
	}
	
	public void addPiece(Player player, Piece newPiece){
		//adds piece to HashMap pieces.  If player is not a Key in piece, it is added first
		
		if(!(pieces.containsKey(player))){
			pieces.put(player, new ArrayList<Piece>());
		}
		pieces.get(player).add(newPiece);
				
	}
	
	public void addPieceToBoard(Player player, Piece newPiece) throws InvalidMoveException{
		//similar to addPiece method, but decrements piecesRemaining in player
		
		if(player.getPiecesRemaing() == 0){
			throw new InvalidMoveException("You have already exhausted your supply of explorers");
		}
		
		if(!(pieces.containsKey(player))){
			pieces.put(player, new ArrayList<Piece>());
		}
		pieces.get(player).add(newPiece);
		player.setPiecesRemaining(player.getPiecesRemaing()-1);
		
				
	}
	
	public Piece removePiece(Player player) throws InvalidMoveException{
		//removes first piece in the ArrayList corresponding to player in pieces
		
		if(!(pieces.containsKey(player)) || pieces.get(player).size()<1){
			throw new InvalidMoveException("That player does not have any pieces on this tile");
		}
		
		return pieces.get(player).remove(0);
		
	
	}
	
	public HashMap<Player, ArrayList<Piece>> getPieces(){
		return pieces;
	}
	
	//Need to work out owner method
	public Player owner() throws UnoccupiedTileException{
		//UNTESTED, MIGHT WORK, WILL RETURN EITHER PLAYER IN EVENT OF A TIE
		if(this.isUnoccupied()){
			throw new UnoccupiedTileException("This tile has no owner");
		}
		
		Entry<Player, ArrayList<Piece>> tempMax = pieces.entrySet().iterator().next();
		
		for (Entry<Player, ArrayList<Piece>> entry : pieces.entrySet()) {
		    if(entry.getValue().size() > tempMax.getValue().size()){
		    	tempMax = entry;
		    }
		}
		
		return tempMax.getKey();
		
	}


	public boolean isUnoccupied(){
		//returns true if pieces is empty or if each ArrayList in pieces is size 0
		if(pieces.isEmpty()){
			return true;
		}
		for (Entry<Player, ArrayList<Piece>> entry : pieces.entrySet()) {
		    if(entry.getValue().size() > 0){
		    	return false;
		    }
		}
		return true;	
	}
	
	
}
