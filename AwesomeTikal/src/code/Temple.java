package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import actionListeners.TempleListener;
import resources.InvalidMoveException;
import resources.NoOwnerException;
import resources.UnoccupiedTileException;

public class Temple extends Tile {
	
	private boolean isGuarded;
	
	private Player guard;
	
	private int templeValue;
	
	private JButton templeButton;
	
	private Dimension templeDimension = new Dimension(40,40);
	
	public Temple(int[] sides, int startingValue){
		super(sides);
		templeValue = startingValue;
		templeButton = new JButton();
		templeButton.setLayout(new FlowLayout());
		templeButton.setPreferredSize(templeDimension);
		templeButton.setMaximumSize(templeDimension);
		templeButton.setMinimumSize(templeDimension);
		templeButton.setBackground(Color.CYAN);
		templeButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		setButtonLabel();
		center.add(templeButton);
	}
	
	private void setButtonLabel(){
		templeButton.removeAll();
		templeButton.add(new JLabel(""+ templeValue));
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
		setButtonLabel();
		center.removeAll();
		System.out.println("temple refresh center ran");
		center.add(templeButton);
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
	
	
	public int getTempleValue(){
		return templeValue;
	}
	
	public JButton getTempleButton(){
		return templeButton;
	}
	
	public void incrementTempleValue() throws InvalidMoveException{
		try {
			if(GameModel.getPlayer() != this.owner()){
				throw new InvalidMoveException("You are not the owner of this tile");
			}
		} catch (NoOwnerException e) {
			throw new InvalidMoveException("You are not the owner of this tile");
		}
		if(templeValue ==10){
			throw new InvalidMoveException("This Temple is already at max value");
		}
		GameModel.removeTemplePiece(this.getTempleValue()+1);
		templeValue++;
		refreshCenterPanel();
	}
	
	
	/*
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
	*/
	
	

}
