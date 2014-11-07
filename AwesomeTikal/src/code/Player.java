package code;

import java.awt.Color;

public class Player {

	private Color color;
	private String name;
	
	
	
	private int piecesRemaining; 
	
	public Color getColor(){
		return color;
	}
	
	public Player (String name, Color color){
		this.name = name;
		this.color = color;
		piecesRemaining = 10;
	}
	
	public int getPiecesRemaing(){
		return piecesRemaining;
	}
	
	public void setPiecesRemaining(int n){
		piecesRemaining = n;
	}
	
	
}
