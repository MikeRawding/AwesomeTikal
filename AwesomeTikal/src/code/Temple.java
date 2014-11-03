package code;

import resources.InvalidMoveException;
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
	
	public void setGuard(Player player) throws InvalidMoveException{
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
	
	//STUBBED OUT METHOD NEEDS SAME IMPLEMENTATION AS SUPERCLASS FOR DETERMINING OWNER
	public Player owner() throws UnoccupiedTileException{
		if(isGuarded){
			return guard;
		}
		if(this.isUnoccupied()){
			throw new UnoccupiedTileException("This tile has no owner");
		}
		return(new Player("fred", "blue"));
	}
	

}
