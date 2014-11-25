package code;

import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color _color;
	private String _name;
	private int score;
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int s){
		score = s;
	}
	
	public void addToScore(int a){
		score += a;
	}
	
	public String getName(){
		return _name;
	}
	
	private int piecesRemaining; 
	
	public Color getColor(){
		return _color;
	}
	
	public Player (String name, Color color){
		this._name = name;
		this._color = color;
		piecesRemaining = 10;
	}
	
	public int getPiecesRemaing(){
		return piecesRemaining;
	}
	
	public void setPiecesRemaining(int n){
		piecesRemaining = n;
	}
	
	
}
