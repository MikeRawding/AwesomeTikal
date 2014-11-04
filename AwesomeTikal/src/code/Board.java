package code;
//Would this work for us?
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board{

	
	public Board(){
		JButton[][] grid = new JButton[6][6];
		JFrame frame = new JFrame("Welcome to Tikal (the game of stealing from the natives)");
		
		
		JPanel[] columns = new JPanel[6];
		for(int i = 0; i < columns.length; i++){
			columns[i] = new JPanel();
			columns[i].setLayout(new BoxLayout(columns[i],1));
		}
		
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1,6));
		for(int x=0; x<columns.length; x++){
			panel.add(columns[x]);
			if(x%2==0){
				columns[x].add(new JPanel());
				for(int y=0; y<5; y++){
					grid[x][y]=new JButton("" +x+ "" +y+ ""); //creates new button     
					columns[x].add(grid[x][y]); //adds button to grid
				}
			}
			else{
				for(int y=0; y<6; y++){
					grid[x][y]=new JButton("" +x+ "" +y+ ""); //creates new button     
					columns[x].add(grid[x][y]); //adds button to grid
				}
			}
		}

		frame.add(panel);
		//button.setMnemonic('Q'); // associate hotkey to button
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] arg){
		new Board();
	}
}
