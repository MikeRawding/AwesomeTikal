/**
 * The Board class defines the board GUI for the game.  
 * It is made up of a grid of Tiles and a menu bar.  
 * The board contains key methods for building and refreshing the GUI.
 * 
 * @author Mike Rawding
 * @version 12-1-2014
 * 
 */


//

//
// 	this is a new comment for grading

package code;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import resources.*;
import actionListeners.*;


public class Board implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame; //window to hold panels
	private JPanel masterPanel; //panel for entire frame
	private JPanel boardPanel; //panel for Tile area
	private JPanel menuPanel; //panel for menuBar
	private JPanel[] columns; //6 vertical columns to assist in a grid layout
	private Tile[][] grid; //Each Tile is stored here
	private int selectedX = 0;
	private int selectedY = 0;
	private int prevSelectedX = 0;
	private int prevSelectedY = 0;
	private int starterX; //coordinates of the first Tile paced (the only tile explorers can be placed on)
	private int starterY;
	private boolean twoSelections = false; //used to keep track of previous selection
	private boolean placingStarter = true;


	/**
	 * The only constructor of this class.  
	 * GUI is displayed when constructed.
	 * This method relies on some static variable from GameModel such as columnHeight, currentPlayer, and CurrentAP
	 */
	public Board(){

		//Frame setup
		frame = new JFrame("Welcome to Tikal (the game of stealing from the natives)");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);

		//Panel for entire board
		masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel,BoxLayout.X_AXIS));
		frame.add(masterPanel);

		//Panel for grid of Tiles
		boardPanel = new JPanel();
		boardPanel.setLayout(new BoxLayout(boardPanel,0));
		masterPanel.add(boardPanel);

		//Scrollbars for boardPanel
		JScrollPane scrollPane = new JScrollPane(boardPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		masterPanel.add(scrollPane);

		//Fills columns with Panels form grid
		columns = new JPanel[GameModel.getColumnHeight()];
		grid = new Tile[GameModel.getColumnHeight()][GameModel.getColumnHeight()];
		populateColumns(boardPanel);

		//Make and add menu bar
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel,1));
		menuPanel.setPreferredSize(new Dimension(250, 9000));
		menuPanel.setMaximumSize(new Dimension(250, 9000));
		menuPanel.setMinimumSize(new Dimension(250, 9000));

		refreshMenuPanel();
		masterPanel.add(menuPanel);
		frame.setVisible(true);

	}
	
	/**
	 * Clears and populates buttons and labels on the menuPanel with current information.
	 */
	public void refreshMenuPanel() {
		menuPanel.removeAll();
		menuPanel.setBackground(GameModel.getCurrentPlayer().getColor());
		
		//AP counter
		JLabel aP = new JLabel("AP Remaining: "+ GameModel.getActionPoints());
		aP.setFont(new Font(aP.getFont().getName(), Font.BOLD, 22));
		menuPanel.add(aP);
		
		//endTurn button
		JButton endTurn = new JButton("End Turn");
		setButtonSize(endTurn);
		endTurn.addActionListener(new EndTurnListener(this,menuPanel));
		menuPanel.add(endTurn);


		//rotate buttonOnDeck
		JButton rotateOnDeckClockwise = new JButton("Rotate");
		setButtonSize(rotateOnDeckClockwise);
		rotateOnDeckClockwise.addActionListener(new RotateListener(this, true));
		menuPanel.add(rotateOnDeckClockwise);

		//place Tile button
		JButton placeTile = new JButton("Place Tile");
		setButtonSize(placeTile);
		placeTile.addActionListener(new AddTileListener(this));
		menuPanel.add(placeTile);

		//add piece to board button
		JButton addPieceToBoard = new JButton("Add piece board");
		setButtonSize(addPieceToBoard);
		addPieceToBoard.addActionListener(new AddPieceListener(this));
		menuPanel.add(addPieceToBoard);

		//two selections button
		JButton enableTwoSelections = new JButton("Select two Tiles for Move");
		setButtonSize(enableTwoSelections);
		enableTwoSelections.addActionListener(new SetTwoSelectionsListener(this));
		menuPanel.add(enableTwoSelections);

		//move button
		JButton move = new JButton("Move");
		setButtonSize(move);
		move.addActionListener(new MovePieceListener(this));
		menuPanel.add(move);

		//save button
		JButton save = new JButton("Save");
		setButtonSize(save);
		save.addActionListener(new SaveGameListener(this));
		menuPanel.add(save);

		//score board
		JPanel scoreBoard = new JPanel();
		scoreBoard.setLayout(new BoxLayout(scoreBoard, 1));
		scoreBoard.setBackground(GameModel.getCurrentPlayer().getColor());
		for(int i = 0; i  < GameModel.getPlayerList().size(); i++){
			JLabel temp = new JLabel(GameModel.getPlayerList().get(i).getName() + ": " + GameModel.getPlayerList().get(i).getScore());
			temp.setBackground(GameModel.getPlayerList().get(i).getColor());
			temp.setOpaque(true);
			temp.setFont(new Font(temp.getFont().getName(), Font.PLAIN, 26));
			if(GameModel.getPlayerList().get(i).equals(GameModel.getCurrentPlayer())){
				temp.setText(">" + temp.getText() + "<");
				temp.setFont(new Font(temp.getFont().getName(), Font.BOLD, 30));
			}
			scoreBoard.add(temp);
		}
		menuPanel.add(scoreBoard);

		//next tile preview
		JPanel onDeckPreview = GameModel.getOnDeckTile().getTilePanel();
		onDeckPreview.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuPanel.add(onDeckPreview);

		frame.setVisible(true);
	}

	/** 
	 * Method for adding Tile to Board.  The Tile is placed at grid[selectedX][selectedY].
	 * @param t                               The Tile to be placed.
	 * @throws InvalidMoveException           Use .getMessage() for more info.
	 * @throws NoActionPointsException       Use .getMessage() for more info.
	 */
	public void placeTile(Tile t) throws InvalidMoveException, NoActionPointsException{

		if(!grid[selectedX][selectedY].isBlank()){
			throw new InvalidMoveException("That position is already occupied.");
		}
		else if(GameModel.getActionPoints() < 6){
			throw new NoActionPointsException("Sorry Not Enough Action Points");
		}
		else if(placingStarter){
			if(!onEdge(selectedX, selectedY)){
				throw new InvalidMoveException("The First Tile must be placed on and edge");
			}
		}
		if(tilePlaceable(selectedX, selectedY)){
			if(placingStarter){
				starterX = selectedX;
				starterY = selectedY;
				t.setTileBackground(Color.pink);
			}
			grid[selectedX][selectedY]= t;
			placingStarter = false;
			grid[selectedX][selectedY].getTilePanel().addMouseListener(new SelectTileListener(this,selectedX,selectedY));
			if(t instanceof Volcano){
				score();
				refreshMenuPanel();
			}
			if(t instanceof Temple){
				((Temple) t).getTempleButton().addActionListener(new TempleListener(this, (Temple)t));
			}
			GameModel.setActionPoints(GameModel.getActionPoints()-6);
			grid[selectedX][selectedY].getTilePanel().setAlignmentX(Component.CENTER_ALIGNMENT);
			refreshColumn(selectedX);
		}
		else{
			throw new InvalidMoveException("There must be a path to the tile you are placing");
		}

	}
	/**
	 * Refreshes the Tile being previewed(does not call for a new Tile)
	 */
	public void refreshOnDeckPreview(){
		JPanel onDeckPreview = GameModel.getOnDeckTile().getTilePanel();
		menuPanel.add(onDeckPreview);
		frame.setVisible(true);
	}

	/**
	 * Sets the input X,Y coordinates to the selected Tile.  Previously selected tile is forgotten
	 * unless the two selections button has been pressed.
	 * 
	 * @param x The X coordinate on the board that is to be selected.
	 * @param y The Y coordinate on the board that is to be selected.
	 */
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

		if(placingStarter && onEdge(x,y)){
			return true;
		}
		boolean v = false;
		if(GameModel.getOnDeckTile() instanceof Volcano){v = true;}
			
		if(x%2==0){
			if(isInGrid(x,y-1)){
				if(!grid[x][y-1].isBlank()){
					if(v){
						return true;
					}
					else if(calculatePath(x, y, x, y-1, GameModel.getOnDeckTile()) != 0){
						return true;
					}
				}
			}
			if(isInGrid(x+1,y)){
				if(!grid[x+1][y].isBlank()){ 
					if(v){
						return true;
					}
					else if(calculatePath(x, y, x+1, y, GameModel.getOnDeckTile()) != 0){
						return true;
					}
				}
			}
			if(isInGrid(x+1,y+1)){
				if(!grid[x+1][y+1].isBlank()){
					if(v){
						return true;
					}
					else if(calculatePath(x, y, x+1, y+1, GameModel.getOnDeckTile()) != 0){
						return true;
					}
				}
			}
			if(isInGrid(x,y+1)){
					if(y + 1 != GameModel.getColumnHeight()-1){
						if (!grid[x][y + 1].isBlank()) {
							if (v) {
								return true;
							} else if (calculatePath(x, y, x, y + 1,
									GameModel.getOnDeckTile()) != 0) {
								return true;
							}
						}
					}
			}
			if(isInGrid(x-1,y+1)){
					if(!grid[x-1][y+1].isBlank()){
						if(v){
							return true;
						}
						else if(calculatePath(x, y, x-1, y+1, GameModel.getOnDeckTile()) != 0){
							return true;
						}
					}
			}
			if(isInGrid(x-1,y)){
				if(!grid[x-1][y].isBlank()){
					if(v){
						return true;
					}
					else if(calculatePath(x, y, x-1, y, GameModel.getOnDeckTile()) != 0){
						return true;
					}
				}
			}

		}
		else{
			if(isInGrid(x,y-1)){
				if(!grid[x][y-1].isBlank()){
					if(v){
						return true;
					}
					else if(calculatePath(x, y, x, y-1, GameModel.getOnDeckTile()) != 0){
						return true;
					}
				}
			}
			if(isInGrid(x+1,y-1)){
				if(!grid[x+1][y-1].isBlank()){
					if(v){
						return true;
					}
					else if(calculatePath(x, y, x+1, y-1, GameModel.getOnDeckTile()) != 0){
						return true;
					}
				}
			}
			if(isInGrid(x+1,y)){
				if(y != GameModel.getColumnHeight()-1){
					if (!grid[x+1][y].isBlank()) {
						if (v) {
							return true;
						} else if (calculatePath(x, y, x + 1, y,
								GameModel.getOnDeckTile()) != 0) {
							return true;
						}
					}
				}
			}
			if(isInGrid(x,y+1)){
				if(!grid[x][y+1].isBlank()){
					if(v){
						return true;
					}
					else if(calculatePath(x, y, x, y+1, GameModel.getOnDeckTile()) != 0){
						return true;
					}
				}
			}
			if(isInGrid(x-1,y)){
				if(y  != GameModel.getColumnHeight()-1){
					if (!grid[x - 1][y].isBlank()) {
						if (v) {
							return true;
						} else if (calculatePath(x, y, x - 1, y,
								GameModel.getOnDeckTile()) != 0) {
							return true;
						}
					}
				}
			}
			if(isInGrid(x-1,y-1)){
				if(!grid[x-1][y-1].isBlank()){
					if(v){
						return true;
					}
					else if(calculatePath(x, y, x-1, y-1, GameModel.getOnDeckTile()) != 0){
						return true;
					}
				}
			}
		}


		return false;
	}


	private boolean isInGrid(int x, int y){
		return(x>=0 && x<grid.length && y>=0 && y<grid[0].length);
	}

	/**
	 * Mutator for twoSelections.  When true the user can select two tiles at once(used to move explorer).
	 * 
	 * @param b the value to which twoSelections will be set.
	 */
	public void setTwoSelections(boolean b){
		twoSelections = b;
	}

	private int calculatePath(int x1, int y1, int x2, int y2){
		int locationValue = 0 ;
		int destinationValue = 0;
		if(x1 == x2){
			if(y1 < y2){
				locationValue = grid[x1][y1].getSides()[4];
				destinationValue = grid[x2][y2].getSides()[1];
			}
			else{
				locationValue = grid[x1][y1].getSides()[1];
				destinationValue = grid[x2][y2].getSides()[4];
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

	private int calculatePath(int x1, int y1, int x2, int y2, Tile t){
		int locationValue = 0 ;
		int destinationValue = 0;
		if(x1 == x2){
			if(y1 < y2){
				locationValue = t.getSides()[4];
				destinationValue = grid[x2][y2].getSides()[1];
			}
			else{
				locationValue = t.getSides()[1];
				destinationValue = grid[x2][y2].getSides()[4];
			}
		}
		else if(x1 < x2){
			if(x1 % 2 == 0){
				if(y1 == y2){
					locationValue = t.getSides()[2];
					destinationValue = grid[x2][y2].getSides()[5];
				}
				else{
					locationValue = t.getSides()[3];
					destinationValue = grid[x2][y2].getSides()[0];
				}
			}
			else{
				if(y1 == y2){
					locationValue = t.getSides()[3];
					destinationValue = grid[x2][y2].getSides()[0];
				}
				else{
					locationValue = t.getSides()[2];
					destinationValue = grid[x2][y2].getSides()[5];
				}
			}
		}
		else if(x1 > x2){
			if(x1 % 2 == 0){
				if(y1 == y2){
					locationValue = t.getSides()[0];
					destinationValue = grid[x2][y2].getSides()[3];
				}
				else{
					locationValue = t.getSides()[5];
					destinationValue = grid[x2][y2].getSides()[2];
				}
			}
			else{
				if(y1 == y2){
					locationValue = t.getSides()[5];
					destinationValue = grid[x2][y2].getSides()[2];
				}
				else{
					locationValue = t.getSides()[0];
					destinationValue = grid[x2][y2].getSides()[3];
				}
			}
		}
		return locationValue + destinationValue;
	}

	/**
	 * Takes in two sets of coordinates and attempts to move a piece from grid[fromX][fromY] to grid[toX][toY].
	 * @param fromX  X coordinate of from Tile
	 * @param fromY  Y coordinate of from Tile
	 * @param toX    X coordinate of to Tile
	 * @param toY    y coordinate of to Tile
	 * @throws InvalidMoveException Use .getMessage() for specifics
	 */
	public void movePiece(int fromX, int fromY, int toX, int toY) throws InvalidMoveException{
		if(grid[toX][toY] instanceof Volcano){
			throw new InvalidMoveException("You cannot place explorers on a Volcano Tile");
		}
		if((calculatePath(fromX, fromY, toX, toY) > GameModel.getActionPoints())){
			JOptionPane.showMessageDialog(null,"No enough action points remaining");
			return;
		}
		try{
			Tile.move(GameModel.getCurrentPlayer(), grid[fromX][fromY], grid[toX][toY]);
			GameModel.setActionPoints(GameModel.getActionPoints()-calculatePath(fromX, fromY, toX, toY));
		}
		catch(InvalidMoveException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (NoActionPointsException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		refreshMenuPanel();
		frame.setVisible(true);
	}

	private void score(){
		for(int x = 0; x < grid.length; x++){
			if(x % 2 == 0){
				for(int y = 0; y < grid[0].length -1 ; y++){
					if(!grid[x][y].isBlank()){
						if(grid[x][y] instanceof Temple){
							try {
								grid[x][y].owner().addToScore(((Temple) grid[x][y]).getTempleValue());
							} catch (NoOwnerException e) {
							}
						}
						else{
							try {
								grid[x][y].owner().addToScore(1);
							} catch (NoOwnerException e) {
							}
						}
					}
				}
			}
			else{
				for(int y = 0; y < grid[0].length ; y++){
					if(!grid[x][y].isBlank()){
						if(grid[x][y] instanceof Temple){
							try {
								grid[x][y].owner().addToScore(((Temple) grid[x][y]).getTempleValue());
							} catch (NoOwnerException e) {
							}
						}
						else{
							try {
								grid[x][y].owner().addToScore(1);
							} catch (NoOwnerException e) {
							}
						}
					}
				}
			}

		}
	}

	/**
	 * 
	 * @return X coordinate of previously selected Tile.
	 */
	public int getPrevSelectedX(){
		return prevSelectedX;
	}
	/**
	 * 
	 * @return Y coordinate of previously selected Tile.
	 */
	public int getPrevSelectedY(){
		return prevSelectedY;
	}
	
	/**
	 * 
	 * @return X coordinate of selected Tile.
	 */
	public int getSelectedX(){
		return selectedX;
	}
	
	/**
	 * 
	 * @return Y coordinate of selected Tile.
	 */
	public int getSelectedY(){
		return selectedY;
	}

	/**
	 * Packs window.  Useful to refresh elements.
	 */
	public void pack() {
		frame.pack();
	}

	/**
	 * 
	 * @return First Tile that was placed.  This is where all Explorers are added to the Board.
	 */
	public Tile getStarterTile() {
		return grid[starterX][starterY];
	}
	
	
	private void makeTileSpace(Integer x, Integer y){
		grid[x][y] = new Tile(); //creates new Tile 
		grid[x][y].getTilePanel().addMouseListener(new SelectTileListener(this,x,y));
		columns[x].add(grid[x][y].getTilePanel()); //adds Tile to grid

	}
	
	/**
	 * Refreshes a column to display a change
	 * @param x position is columns[] to be refreshed
	 */
	public void refreshColumn(int x) {
		columns[x].removeAll();
		if(x%2 == 0){
			columns[x].add(spacer());
			for(int i = 0; i < GameModel.getColumnHeight()-1; i ++){
				columns[x].add(grid[x][i].getTilePanel());
			}
			columns[x].add(spacer());
		}
		else{
			for(int i = 0; i < GameModel.getColumnHeight(); i++){
				columns[x].add(grid[x][i].getTilePanel());
			}
		}
		masterPanel.repaint();
		frame.setVisible(true);
	}
	
	//Used to check if a grid location is on the edge of the board (necessary for placing the first Tile).
	private boolean onEdge(int x, int y) {
			if(x == 0){
				return true;
			}
			else if(x == GameModel.getColumnHeight()-1){
				return true;
			}
			else if(y == 0){
				return true;
			}
			else if(x % 2 == 0){
				if(y == GameModel.getColumnHeight()-2){
					return true;
				}
			}
			else if(y == GameModel.getColumnHeight()-1){
				return true;
			}
			
			return false;
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
				for(int y=0; y<GameModel.getColumnHeight()-1; y++){
					makeTileSpace(x,y);
				}
				columns[x].add(spacer()); //spacing panel
			}
			else{
				for(int y=0; y<GameModel.getColumnHeight(); y++){
					makeTileSpace(x, y);
				}
			}
		}
	}
	
	private void setButtonSize(JButton in){
		Dimension d = new Dimension(250, 50);
		in.setPreferredSize(d);
		in.setMaximumSize(d);
		in.setMinimumSize(d);
	}
	
	//Spacer used to create stagger between columns
	private Component spacer(){
		Component spacer = Box.createRigidArea(new Dimension(200,100));
		return spacer;
	}
	

}
