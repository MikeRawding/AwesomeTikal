package code;
//Would this work for us?
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board{

	public Board(){
		JButton[][] grid = new JButton[6][6];
		JFrame frame = new JFrame("Welcome to Tikal");
		frame.setSize(new Dimension(1000,800));
		
		JPanel[] columns = new JPanel[6];
		for(int i = 0; i < columns.length; i++){
			columns[i] = new JPanel();
			columns[i].setLayout(new GridLayout(6,1));
		}
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1,6));
		for(int y=0; y<columns.length; y++){
			panel.add(columns[y]);
			if(y%2==0){
				for(int x=0; x<5; x++){
					grid[x][y]=new JButton("" +x+ "" +y+ ""); //creates new button     
					columns[y].add(grid[x][y]); //adds button to grid
				}
			}
			else{
				for(int x=0; x<6; x++){
					grid[x][y]=new JButton("" +x+ "" +y+ ""); //creates new button     
					columns[y].add(grid[x][y]); //adds button to grid
				}
			}
		}

		frame.add(panel);
		//button.setMnemonic('Q'); // associate hotkey to button

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] arg){
		new Board();
	}
}
