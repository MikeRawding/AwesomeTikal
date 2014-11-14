package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.*;

public class Tile {

	
	protected HashMap<Player, ArrayList<Piece>> pieces = new HashMap<Player, ArrayList<Piece>>();
	
	private int[] sides;
	private JPanel tilePanel = new JPanel();
	private JPanel top = new JPanel();
	private JPanel center = new JPanel();
	private JPanel bottom = new JPanel();
	private JPanel[] paths = new JPanel[6];
	
	
	public Tile(int[] sides){
		this.sides = sides;
		initPanel();
	}
	
	public JPanel getTilePanel(){
		return tilePanel;
	}
	
	private void initPanel(){
		for(int i = 0; i < paths.length; i++){
			paths[i] = new JPanel();
			paths[i].setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
			paths[i].setBackground(Color.gray);
			paths[i].add(new JLabel(""+sides[i]+""));
			paths[i].setVisible(true);
		}
		
		tilePanel.setLayout(new BoxLayout(tilePanel, BoxLayout.Y_AXIS));
		tilePanel.setSize(250, 180);
		tilePanel.setMaximumSize(tilePanel.getSize());
		tilePanel.setMinimumSize(tilePanel.getSize());
		tilePanel.setVisible(true);
		top.setLayout(new BorderLayout());
		top.add(paths[0],BorderLayout.WEST);
		top.add(paths[1],BorderLayout.NORTH);
		top.add(paths[2],BorderLayout.EAST);
		top.setVisible(true);
		bottom.setLayout(new BorderLayout());
		bottom.add(paths[3],BorderLayout.EAST);
		bottom.add(paths[4],BorderLayout.SOUTH);
		bottom.add(paths[5],BorderLayout.WEST);
		bottom.setVisible(true);
		
		
		
		tilePanel.add(top);
		tilePanel.add(center);
		center.setVisible(true);
		tilePanel.add(bottom);
		
	}
	
	
	
	public void rotateCounterClockwise(){
		//decrements the values in sides[]. i.e. sides[0] gets sides[1], sides[sides.length] gets sides[0]
		int temp = sides[0];
		for(int i = 0; i < sides.length-1; i++){
			sides[i] = sides[i+1];
		}
		sides[sides.length-1] = temp;	
		for(int i = 0; i < paths.length; i ++){
			paths[i].removeAll();
			paths[i].add(new JLabel(""+sides[i]+""));
		}
	}
	
	public void rotateClockwise(){
		//increments the values in sides[]. i.e. sides[1] gets sides[0], sides [0] gets sides[sides.length]
		int temp = sides[sides.length-1];
		for(int i = sides.length-1; i > 0; i--){
			sides[i]=sides[i-1]; 
		}
		sides[0] = temp;
		for(int i = 0; i < paths.length; i ++){
			paths[i].removeAll();
			paths[i].add(new JLabel(""+sides[i]+""));
		}
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
	
	//Define JPanel for tile
	
	
	
	
	
}
