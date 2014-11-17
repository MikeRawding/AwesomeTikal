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
import javax.swing.JPanel;

import resources.NoTilesRemainException;
import actionListeners.AddTileListener;
import actionListeners.EndTurnListener;
import actionListeners.RotateListener;
import actionListeners.SelectTileListener;

public class Board implements Serializable{

	
	private JFrame frame; 
	private JPanel masterPanel; //panel for entire frame
	private JPanel boardPanel; //panel for tile area
	private JPanel menuPanel; //panel for menu bar
	private JPanel[] columns; //6 vertical columns to assist in a grid layout
	private Tile[][] grid; //Each space for a tile is stored here
	private JPanel onDeckPreview;
	private int selectedX = 0;
	private int selectedY = 0;
	
	
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
		
		
		
		//Make and add menu bar
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel,1));
		menuPanel.setBackground(Color.blue);
		menuPanel.setSize(300, 900);
		menuPanel.setMaximumSize(menuPanel.getSize());
		
			//endTurn button
			JButton endTurn = new JButton("End Turn");
			endTurn.setSize(300, 100);
			endTurn.setMaximumSize(endTurn.getSize());
			endTurn.setMinimumSize(endTurn.getSize());
			endTurn.addActionListener(new EndTurnListener(this,menuPanel));
			menuPanel.add(endTurn);
		
			//AP counter
			JLabel aP = new JLabel(""+GameModel.getActionPoints()+"");
			menuPanel.add(aP);
			
			//new tile preview
			onDeckPreview = GameModel.onDeckTile.getTilePanel();
			menuPanel.add(onDeckPreview);
			
			//rotate buttonOnDeck
			JButton rotateOnDeckCloclwise = new JButton("Rotate");
			rotateOnDeckCloclwise.setSize(100,100);
			rotateOnDeckCloclwise.setMaximumSize(rotateOnDeckCloclwise.getSize());
			rotateOnDeckCloclwise.setMaximumSize(rotateOnDeckCloclwise.getSize());
			rotateOnDeckCloclwise.addActionListener(new RotateListener(this, true));
			menuPanel.add(rotateOnDeckCloclwise);
			
			//Pieces remaining label

		masterPanel.add(menuPanel);
		
		
		
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
	public void addTile(int x, int y){
		
		grid[x][y]= GameModel.onDeckTile;
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
		onDeckPreview = GameModel.onDeckTile.getTilePanel();
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
		grid[selectedX][selectedY].getTilePanel().setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
		selectedX = x;
		selectedY = y;
		grid[selectedX][selectedY].getTilePanel().setBorder(BorderFactory.createLineBorder(Color.green, 5, false));
	}

}
