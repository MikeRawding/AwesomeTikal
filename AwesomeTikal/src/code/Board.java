package code;
//Would this work for us?
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board{

	public Board(int width, int length){
		JButton[][] grid = new JButton[width][length];
		JFrame frame = new JFrame("Welcome to Tikal");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(width,length));
		for(int y=0; y<length; y++){
			for(int x=0; x<width; x++){
				grid[x][y]=new JButton("("+x+","+y+")"); //creates new button     
				frame.add(grid[x][y]); //adds button to grid
			}
		}

		frame.add(panel);
		//button.setMnemonic('Q'); // associate hotkey to button

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] arg){
		new Board(3,3);
	}
}