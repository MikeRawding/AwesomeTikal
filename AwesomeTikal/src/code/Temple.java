package code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;

import resources.InvalidMoveException;
import resources.NoOwnerException;
import resources.UnoccupiedTileException;

public class Temple extends Tile {
	
	private boolean isGuarded;
	
	private Player guard;
	
	private int templeValue;
	
	public Temple(int[] sides, int startingValue){
		super(sides);
		templeValue = startingValue;
		center.add(new JLabel("Temple: "+ templeValue));
	}
	

	//adds piece to HashMap pieces.  If player is not a Key in pieces, it is added first
	public void addPiece(Player player, Piece newPiece){
		
				
		if(!(pieces.containsKey(player))){
			pieces.put(player, new ArrayList<Piece>());
		}
		pieces.get(player).add(newPiece);
		refreshCenterPanel();
	}
	
	private void refreshCenterPanel(){
		center.removeAll();
		System.out.println("temple refresh center ran");
		center.add(new JLabel("Temple: "+ templeValue));
		Iterator it = pieces.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Player, ArrayList<Piece>>ent = (Map.Entry)it.next();
	        if(ent.getValue().size() > 0){
				JLabel temp = new JLabel(""+ent.getValue().size()+"");
				temp.setBackground(ent.getKey().getColor());
				temp.setOpaque(true);
		        center.add(temp);
	        }
		}
		
	}
	
	public int incrementTempleValue(){
		templeValue++;
		
		
		return templeValue;
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
		pieces.get(player).clear();
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
		    else if(entry.getValue().size() == tempMax.getValue().size() && entry.getKey() != tempMax.getKey()){
		    	tie = true;
		    }
		}
		
		if(tie){
			throw new NoOwnerException("This tile has no owner due to a tie");
		}
		else{
			return tempMax.getKey();
		}
	}

}
