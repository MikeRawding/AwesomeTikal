package code;
//Would this work for us?
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import actionListeners.EndTurnListener;

public class Board{

	private JPanel[][] grid;
	private JPanel[] columns;
	
	public Board(){
		grid = new JPanel[6][6];
		
		//Frame setup
		JFrame frame = new JFrame("Welcome to Tikal (the game of stealing from the natives)");
		frame.setSize(new Dimension(1800,1000));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),0));
		
		JPanel masterPanel = new JPanel();
		masterPanel.setLayout(new BoxLayout(masterPanel,BoxLayout.X_AXIS));
		frame.add(masterPanel);
		
		
		//boardPanel setup
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new BoxLayout(boardPanel,0));
		masterPanel.add(boardPanel);
		
		columns = new JPanel[6];
		
		populateColumns(boardPanel);
		
		//Make and add menu bar
		JPanel menuBar = new JPanel();
		menuBar.setLayout(new BoxLayout(menuBar,1));
		menuBar.setBackground(Color.blue);
		menuBar.setSize(300, 900);
		menuBar.setMaximumSize(menuBar.getSize());
		
		
		//endTurn button
		JButton endTurn = new JButton("End Turn");
		endTurn.setSize(300, 100);
		endTurn.setMaximumSize(endTurn.getSize());
		endTurn.setMinimumSize(endTurn.getSize());
		endTurn.addActionListener(new EndTurnListener(menuBar));
		menuBar.add(endTurn);
		
		//AP counter
		JLabel aP = new JLabel(""+GameModel.getActionPoints()+"");
		menuBar.add(aP);

		masterPanel.add(menuBar);
		
		
		
		
		
	}

	private void populateColumns(JPanel boardPanel) {
		for(int i = 0; i < columns.length; i++){
			columns[i] = new JPanel();
			columns[i].setLayout(new BoxLayout(columns[i],1));
		}
		
		for(int x=0; x<columns.length; x++){
			boardPanel.add(columns[x]);
			if(x%2==0){
				columns[x].add(Box.createRigidArea(new Dimension(200,100))); //spacing panel
				columns[x].getComponent(0).setSize(100, 100);
				for(int y=0; y<5; y++){
					makeTileSpace(Color.cyan,x,y);
				}
				columns[x].add(Box.createRigidArea(new Dimension(200,100))); //spacing panel
			}
			else{
				for(int y=0; y<6; y++){
					makeTileSpace(Color.magenta, x, y);
				}
			}
		
		}
	}

	private void makeTileSpace(Color color, Integer x, Integer y){
		grid[x][y]=new JPanel(); //creates new button 
		grid[x][y].setBackground(color);
		grid[x][y].repaint();
		grid[x][y].setBorder(BorderFactory.createLineBorder(Color.black, 5, false));
		grid[x][y].setSize(new Dimension(250,180));
		grid[x][y].setMaximumSize(grid[x][y].getSize());
		grid[x][y].setMinimumSize(grid[x][y].getSize());
		grid[x][y].add(new JButton("place tile"));
		columns[x].add(grid[x][y]); //adds button to grid
		
	}	
		

}
