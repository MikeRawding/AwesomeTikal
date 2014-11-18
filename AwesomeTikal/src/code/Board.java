/*The Board class defines the board GUI for the game.  
 * 
 * 
 */



package code;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import actionListeners.AddPieceListener;
import resources.NoTilesRemainException;
import actionListeners.AddTileListener;
import actionListeners.EndTurnListener;
import actionListeners.MovePieceListener;
import actionListeners.RotateListener;
import actionListeners.SaveGameListener;
import actionListeners.SelectTileListener;
import actionListeners.SetTwoSelectionsListener;

public class Board implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel masterPanel; //panel for entire frame
	private JPanel boardPanel; //panel for tile area
	private JPanel menuPanel; //panel for menu bar
	private JPanel[] columns; //6 vertical columns to assist in a grid layout
	private Tile[][] grid; //Each space for a tile is stored here
	private int selectedX = 0;
	private int selectedY = 0;
	private int prevSelectedX = 0;
	private int prevSelectedY = 0;
	private boolean twoSelections = false;
	private boolean placingStarters = true;
	
	//The only constructor of this class.  GUI is displayed when constructed.
	public Board(){
		
		//Frame setup
		frame = new JFrame("Welcome to Tikal (the game of stealing from the natives)");
		frame.setSize(new Dimension(1800,1100));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Panel for entire board
		masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel,BoxLayout.X_AXIS));
		frame.add(masterPanel);
		
		//Panel for grid of tiles
		boardPanel = new JPanel();
		boardPanel.setLayout(new BoxLayout(boardPanel,0));
		masterPanel.add(boardPanel);
		
		//Fills columns with Panels form grid
		columns = new JPanel[6];
		grid = new Tile[6][6];
		populateColumns(boardPanel);
		
		
		//sets starting tile
		placeTile(new Tile(new int[]{0,1,2,3,4,5}));
		grid[selectedX][selectedY].setPiecesPlaceable(true);
		selectedX = 1;
		placeTile(new Tile(new int[]{0,0,0,2,1,0}));
		selectedX = 0;
		selectedY = 1;
		placeTile(new Tile(new int[]{0,1,1,2,1,0}));
		placingStarters = false;
		
		//Make and add menu bar
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel,1));
		menuPanel.setSize(300, 900);
		menuPanel.setMaximumSize(menuPanel.getSize());
		
		
		refreshMenuPanel();
			
		masterPanel.add(menuPanel);
		
		
		
	}

	public void refreshMenuPanel() {
		menuPanel.removeAll();
		
		menuPanel.setBackground(GameModel.getPlayer().getColor());

		//endTurn button
		JButton endTurn = new JButton("End Turn");
		endTurn.setSize(300, 100);
		endTurn.setMaximumSize(endTurn.getSize());
		endTurn.setMinimumSize(endTurn.getSize());
		endTurn.addActionListener(new EndTurnListener(this,menuPanel));
		menuPanel.add(endTurn);

		//AP counter
		JLabel aP = new JLabel("Action Points Remaining: "+GameModel.getActionPoints()+"");
		menuPanel.add(aP);
		
		//new tile preview
		JPanel onDeckPreview = GameModel.onDeckTile.getTilePanel();
		menuPanel.add(onDeckPreview);
		
		//rotate buttonOnDeck
		JButton rotateOnDeckCloclwise = new JButton("Rotate");
		rotateOnDeckCloclwise.setSize(100,100);
		rotateOnDeckCloclwise.setMaximumSize(rotateOnDeckCloclwise.getSize());
		rotateOnDeckCloclwise.setMaximumSize(rotateOnDeckCloclwise.getSize());
		rotateOnDeckCloclwise.addActionListener(new RotateListener(this, true));
		menuPanel.add(rotateOnDeckCloclwise);
		
		//place Tile button
		JButton placeTile = new JButton("Place Tile");
		placeTile.setSize(100,100);
		placeTile.setMaximumSize(placeTile.getSize());
		placeTile.setMaximumSize(placeTile.getSize());
		placeTile.addActionListener(new AddTileListener(this));
		menuPanel.add(placeTile);

		//add piece to board button
		JButton addPieceToBoard = new JButton("Add piece to selected tile");
		addPieceToBoard.addActionListener(new AddPieceListener(this));
		menuPanel.add(addPieceToBoard);
		
		
		//move piece button
		JButton enableTwoSelections = new JButton("Select two Tiles for Move");
		enableTwoSelections.addActionListener(new SetTwoSelectionsListener(this));
		menuPanel.add(enableTwoSelections);
		
		JButton move = new JButton("Move");
		move.addActionListener(new MovePieceListener(this));
		menuPanel.add(move);
		
		JButton save = new JButton("Save");
		save.addActionListener(new SaveGameListener(this));
		menuPanel.add(save);
		
		
		frame.setSize(new Dimension(1800,1100));
	}
	
	//Spacer used to create stagger between columns
	private Component spacer(){
		Component spacer = Box.createRigidArea(new Dimension(200,100));
		return spacer;
	}

	
	
	private void populateColumns(JPanel boardPanel) {
		for(int i = 0; i < columns.length; i++){
			columns[i] = new JPanel();
			columns[i].setLayout(new BoxLayout(columns[i],1));
		}
		
		for(int x=0; x<columns.length; x++){
			boardPanel.add(columns[x]);
			if(x%2==0){
				columns[x].add(spacer()); //spacing panel
				columns[x].getComponent(0).setSize(100, 100);
				for(int y=0; y<5; y++){
					makeTileSpace(x,y);
				}
				columns[x].add(spacer()); //spacing panel
			}
			else{
				for(int y=0; y<6; y++){
					makeTileSpace(x, y);
				}
			}
		
		}
	}
	
	
	//Incomplete method for adding Panels form Tile class to Board
	public void placeTile(Tile t){
		
		if(!grid[selectedX][selectedY].isBlank()){
			JOptionPane.showMessageDialog(null, "That position is already occupied.");
			return;
		}
		else if(GameModel.getActionPoints() < 3){
			JOptionPane.showMessageDialog(null, "You don't have enough Action Points (3) remaining");
			return;
		}
		if(tilePlaceable(selectedX, selectedY) || placingStarters){
			grid[selectedX][selectedY]= t;
			grid[selectedX][selectedY].getTilePanel().addMouseListener(new SelectTileListener(this,selectedX,selectedY));
			if(!placingStarters){
				GameModel.setActionPoints(GameModel.getActionPoints()-3);
			}
			refreshColumn(selectedX);
		}
		else{
			JOptionPane.showMessageDialog(null, "You must place the new tile next to an existing tile.");
		}
			
	}

	private void refreshColumn(int x) {
		columns[x].removeAll();
		if(x%2 == 0){
			columns[x].add(spacer());
			for(int i = 0; i < 5; i ++){
				columns[x].add(grid[x][i].getTilePanel());
			}
			columns[x].add(spacer());
		}
		else{
			for(int i = 0; i < 6; i++){
				columns[x].add(grid[x][i].getTilePanel());
			}
		}
		masterPanel.repaint();
		frame.setSize(new Dimension(1800,1100));
	}

	private void makeTileSpace(Integer x, Integer y){
		grid[x][y] = new Tile(); //creates new button 
		grid[x][y].getTilePanel().addMouseListener(new SelectTileListener(this,x,y));
		columns[x].add(grid[x][y].getTilePanel()); //adds Tile to grid
		
	}
	
	public void refreshOnDeckPreview(){
		JPanel onDeckPreview = GameModel.onDeckTile.getTilePanel();
		menuPanel.add(onDeckPreview);
		frame.setSize(new Dimension(1800,1100));
	}
	
	public void setSelectedX(int x){
		selectedX = x;
	}
	
	public void setSelectedY(int y){
		selectedY = y;
	}

	public void setSelected(int x, int y) {
		if(twoSelections){
			grid[prevSelectedX][prevSelectedY].getTilePanel().setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
			prevSelectedX = selectedX;
			prevSelectedY = selectedY;
			grid[prevSelectedX][prevSelectedY].getTilePanel().setBorder(BorderFactory.createLineBorder(Color.blue, 5, false));
		}
		if(!twoSelections){
			grid[selectedX][selectedY].getTilePanel().setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
			grid[prevSelectedX][prevSelectedY].getTilePanel().setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
		}
		selectedX = x;
		selectedY = y;
		grid[selectedX][selectedY].getTilePanel().setBorder(BorderFactory.createLineBorder(Color.green, 5, false));
	}
	
	
	
	private boolean tilePlaceable(int x, int y){
		
		if(x%2==0){
			if(isInGrid(x,y-1)){
				if(!grid[x][y-1].isBlank()){
					return true;
				}
			}
			if(isInGrid(x+1,y)){
				if(!grid[x+1][y].isBlank()){
					return true;
				}
			}
			if(isInGrid(x+1,y+1)){
				if(!grid[x+1][y+1].isBlank()){
					return true;
				}
			}
			if(isInGrid(x,y+1)){
				if(!grid[x][y+1].isBlank()){
					return true;
				}
			}
			if(isInGrid(x-1,y+1)){
				if(!grid[x-1][y+1].isBlank()){
					return true;
				}
			}
			if(isInGrid(x-1,y)){
				if(!grid[x-1][y].isBlank()){
					return true;
				}
			}
			
		}
		else{
			if(isInGrid(x,y-1)){
				if(!grid[x][y-1].isBlank()){
					return true;
				}
			}
			if(isInGrid(x+1,y-1)){
				if(!grid[x+1][y-1].isBlank()){
					return true;
				}
			}
			if(isInGrid(x+1,y)){
				if(!grid[x+1][y].isBlank()){
					return true;
				}
			}
			if(isInGrid(x,y+1)){
				if(!grid[x][y+1].isBlank()){
					return true;
				}
			}
			if(isInGrid(x-1,y)){
				if(!grid[x-1][y].isBlank()){
					return true;
				}
			}
			if(isInGrid(x-1,y-1)){
				if(!grid[x-1][y-1].isBlank()){
					return true;
				}
			}
		}
		
		
		return false;
	}
	

	private boolean isInGrid(int x, int y){
		return(x>=0 && x<grid.length && y>=0 && y<grid[0].length);
	}
	
	public Tile[][] getGrid(){
		return grid;
	}
	
	public Tile getSelectedTile(){
		return grid[selectedX][selectedY];
	}

	public void setTwoSelections(boolean b){
		twoSelections = b;
	}
	
	public int calculatePath(int x1, int y1, int x2, int y2){
		int locationValue = 0 ;
		int destinationValue = 0;
		if(x1 == x2){
			if(y1 < y2){
				locationValue = grid[x1][y1].getSides()[4];
				destinationValue = grid[x2][y2].getSides()[1];
			}
			else{
				destinationValue = grid[x2][y2].getSides()[4];
				locationValue = grid[x1][x2].getSides()[1];
			}
		}
		else if(x1 < x2){
			if(x1 % 2 == 0){
				if(y1 == y2){
					locationValue = grid[x1][y1].getSides()[2];
					destinationValue = grid[x2][y2].getSides()[5];
				}
				else{
					locationValue = grid[x1][y1].getSides()[3];
					destinationValue = grid[x2][y2].getSides()[0];
				}
			}
			else{
				if(y1 == y2){
					locationValue = grid[x1][y1].getSides()[3];
					destinationValue = grid[x2][y2].getSides()[0];
				}
				else{
					locationValue = grid[x1][y1].getSides()[2];
					destinationValue = grid[x2][y2].getSides()[5];
				}
			}
		}
		else if(x1 > x2){
			if(x1 % 2 == 0){
				if(y1 == y2){
					locationValue = grid[x1][y1].getSides()[0];
					destinationValue = grid[x2][y2].getSides()[3];
				}
				else{
					locationValue = grid[x1][y1].getSides()[5];
					destinationValue = grid[x2][y2].getSides()[2];
				}
			}
			else{
				if(y1 == y2){
					locationValue = grid[x1][y1].getSides()[5];
					destinationValue = grid[x2][y2].getSides()[2];
				}
				else{
					locationValue = grid[x1][y1].getSides()[0];
					destinationValue = grid[x2][y2].getSides()[3];
				}
			}
		}
		return locationValue + destinationValue;
	}
	
	public int getPrevSelectedX(){
		return prevSelectedX;
	}
	public int getPrevSelectedY(){
		return prevSelectedY;
	}
	public int getSelectedX(){
		return selectedX;
	}
	public int getSelectedY(){
		return selectedY;
	}
}
