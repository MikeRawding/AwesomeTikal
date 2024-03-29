package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import resources.*;

public class Tile {

	
	protected HashMap<Player, ArrayList<Piece>> pieces = new HashMap<Player, ArrayList<Piece>>();
	
	private int[] sides;
	protected JPanel tilePanel = new JPanel();
	private JPanel top = new JPanel();
	protected JPanel center = new JPanel();
	private JPanel bottom = new JPanel();
	private JPanel[] paths = new JPanel[6];
	private boolean isBlank;
	private boolean piecesPlaceable = false;
	private Dimension verticalPathDimension = new Dimension(15,30);
	private Dimension horizontalPathDimension = new Dimension(30,15);
	
	
	/**
	 * Constructor used for making black Tiles.  Used to initially populate the Board.
	 */
	public Tile(){
		tilePanel.setBackground(Color.DARK_GRAY);
		tilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
		tilePanel.setPreferredSize(new Dimension(250,180));
		tilePanel.setMaximumSize(new Dimension(250,180));
		tilePanel.setMinimumSize(new Dimension(250,180));
		isBlank = true;
	}
	
	/**
	 * Constructor used for making tiles in the deck.
	 */
	public Tile(int[] sides){
		this.sides = sides;
		initPanel();
		isBlank = false;
	}
	/**
	 * 
	 * @return The Tile Panel
	 */
	public JPanel getTilePanel(){
		return tilePanel;
	}
	
	
	private void initPanel(){
		tilePanel.setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
		
		for(int i = 0; i < paths.length; i++){
			paths[i] = new JPanel();
		}
		
		setSidePanels();
		
		tilePanel.setLayout(new BoxLayout(tilePanel, BoxLayout.Y_AXIS));
		tilePanel.setPreferredSize(new Dimension(250, 180));
		tilePanel.setMaximumSize(new Dimension(250,180));
		tilePanel.setMinimumSize(new Dimension(250,180));
		tilePanel.setVisible(true);
		top.setLayout(new BorderLayout());
		top.add(paths[0],BorderLayout.WEST);
		top.add(paths[1],BorderLayout.NORTH);
		top.add(paths[2],BorderLayout.EAST);
		top.setBackground(Color.GRAY);
		top.setVisible(true);
		bottom.setLayout(new BorderLayout());
		bottom.add(paths[3],BorderLayout.EAST);
		bottom.add(paths[4],BorderLayout.SOUTH);
		bottom.add(paths[5],BorderLayout.WEST);
		bottom.setBackground(Color.GRAY);
		bottom.setVisible(true);
		center.setBackground(Color.GRAY);
		center.setVisible(true);
		tilePanel.add(top);
		tilePanel.add(center);
		tilePanel.add(bottom);
	}

	private void setSidePanels() {
		for(int i = 0; i < paths.length; i++){
			paths[i].removeAll();
			paths[i].setBackground(Color.GRAY);
			paths[i].setVisible(true);
			if(i == 1 || i == 4){
				paths[i].setLayout(new BoxLayout(paths[i],1));
				for(int y = 0; y < sides[i]; y++){
					paths[i].add(pathBlock(horizontalPathDimension));
				}
			}
			else{
				paths[i].setLayout(new BoxLayout(paths[i], 2));
				for(int y = 0; y < sides[i]; y++){
					paths[i].add(pathBlock(verticalPathDimension));
				}
			}
		}
	}
	
	/**
	 * Sets the background color of a Tile panel.  Used to set the first Tile placed to an identifiable color.
	 * @param c Color for the tile background
	 */
	public void setTileBackground(Color c){
		top.setBackground(c);
		center.setBackground(c);
		bottom.setBackground(c);
		for(int i = 0; i < paths.length; i++){
			paths[i].setBackground(c);
		}
	}
	
	/**
	 * Rotates the path blocks counter clockwise around the sides of the Tile panel.
	 */
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
	
	/**
	 * Rotates the path blocks clockwise around the sides of the Tile panel. C
	 */
	public void rotateClockwise(){
		//increments the values in sides[]. i.e. sides[1] gets sides[0], sides [0] gets sides[sides.length]
		int temp = sides[sides.length-1];
		for(int i = sides.length-1; i > 0; i--){
			sides[i]=sides[i-1]; 
		}
		sides[0] = temp;
		setSidePanels();
	}
		
	/**
	 * Moves a Piece between two Tiles.
	 * 
	 * @param player The Player whose Piece should be moved. 
	 * @param location The current location of the Piece.
	 * @param destination The destination of the Piece.
	 * @throws InvalidMoveException Use .getMessage() for more info.
	 * @throws NoActionPointsException Must have 2 AP remaining.
	 */
	public static void move(Player player,Tile location, Tile destination) throws InvalidMoveException, NoActionPointsException{
		//removes a piece from the ArrayList in the HashMap for player in the location tile and adds said Piece to destination

		
		destination.addPiece(player, location.removePiece(player));
	
	}
	
	private void refreshCenterPanel(){
		center.removeAll();
		Iterator it = pieces.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Player, ArrayList<Piece>>ent = (Map.Entry)it.next();
	        if(ent.getValue().size() > 0){
				JLabel temp = new JLabel(""+ent.getValue().size()+"");
				temp.setBackground(ent.getKey().getColor());
				temp.setOpaque(true);
				temp.setFont(new Font(temp.getFont().getName(), Font.BOLD, 22));
		        center.add(temp);
	        }
		}
		
	}
	
	/**
	 * Adds a piece to the Tile
	 * 
	 * @param player Player whose Piece is being added.
	 * @param newPiece Piece to be added.
	 */
	public void addPiece(Player player, Piece newPiece){
		
				
		if(!(pieces.containsKey(player))){
			pieces.put(player, new ArrayList<Piece>());
		}
		pieces.get(player).add(newPiece);
		refreshCenterPanel();
	}
	
	/**
	 * Adds a Piece to the first Tile placed.
	 * 
	 * @param player Player whose Piece is being added to the board.
	 * @param newPiece The Piece to be added to the board.
	 */
	public void addPieceToBoard(Player player, Piece newPiece){
		//similar to addPiece method, but decrements piecesRemaining in player
		
		if(player.getPiecesRemaing() == 0){
			JOptionPane.showMessageDialog(null, "You have no exploreres remaining");
		}
		else if(GameModel.getActionPoints() < 2){
			JOptionPane.showMessageDialog(null, "Sorry Not Enough Action Points");
		}
		else{
			player.setPiecesRemaining(player.getPiecesRemaing()-1);
			GameModel.setActionPoints(GameModel.getActionPoints()-2);
			addPiece(player, newPiece);
		}
		
				
	}
	
	
	//removes first piece in the ArrayList corresponding to player in pieces
	protected Piece removePiece(Player player) throws InvalidMoveException{
		
		if(!(pieces.containsKey(player)) || pieces.get(player).size()<1){
			throw new InvalidMoveException("That player does not have any pieces on this tile");
		}
		
		Piece temp = pieces.get(player).remove(0);
		refreshCenterPanel();
		return temp;
	}
	
	/**
	 * 
	 * @return Returns true if there are no Explorers on the Tile.
	 */
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
		
	/**
	 * 
	 * @return Returns true if the Tile is blank (was used for populating the board, not from deck).
	 */
	public boolean isBlank(){
		return isBlank;
	}
	
	public boolean piecesPlaceable(){
		return piecesPlaceable;
	}
	
	public Player owner() throws NoOwnerException{
		if(this.isUnoccupied()){
			throw new NoOwnerException("This tile has no owner");
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
	
	/**
	 * 
	 * @return The int array of sides. Index 0-5 for location. Value 0-2 for path blocks.
	 */
	public int[] getSides(){
		return sides;
	}
	
	private JPanel pathBlock(Dimension size){
		JPanel result = new JPanel();
		result.setPreferredSize(size);
		result.setMaximumSize(size);
		result.setMinimumSize(size);
		result.setBackground(Color.YELLOW);
		result.setVisible(true);
		result.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return result;
	}
}
