package code;

import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
