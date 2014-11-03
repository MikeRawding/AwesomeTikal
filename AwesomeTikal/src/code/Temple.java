package code;

import java.util.ArrayList;
import java.util.Map.Entry;

import resources.InvalidMoveException;
import resources.NoOwnerException;
import resources.UnoccupiedTileException;

public class Temple extends Tile {
	
	private boolean isGuarded;
	
	private Player guard;
	
	private int templeValue;
	
	public Temple(int startingValue){
		templeValue = startingValue;
	}
	
	public int incrementTempleValue(){
		return templeValue++;
	}
	
	public void setGuard(Player player) throws InvalidMoveException, NoOwnerException{
		try{	
			if(!(this.owner().equals(player))){
				throw new InvalidMoveException("You must be the owner of the tile to guard a temple");
			}
		}
		catch(UnoccupiedTileException e){
			throw new InvalidMoveException("You must be the owner of the tile to guard a temple");
		}
		if(this.isGuarded){
			throw new InvalidMoveException("This temple is already guarded by another player");
		}
		isGuarded = true;
		guard = player;
	}
	
	
	public Player owner() throws UnoccupiedTileException, NoOwnerException{
		if(isGuarded){
			return guard;
		}
		if(this.isUnoccupied()){
			throw new UnoccupiedTileException("This tile has no owner");
		}
		
		Entry<Player, ArrayList<Piece>> tempMax = pieces.entrySet().iterator().next();
		boolean tie = false;
		for (Entry<Player, ArrayList<Piece>> entry : pieces.entrySet()) {
		    if(entry.getValue().size() > tempMax.getValue().size()){
		    	tempMax = entry;
		    	tie = false;
		    }
		    else if(entry.getValue().size() == tempMax.getValue().size() && entry.getValue() != tempMax.getValue()){
		    	tie = true;
		    }
		}
		
		if(tie){
			throw new NoOwnerException("This tile has no owner");
		}
		else{
			return tempMax.getKey();
		}
	}

}
