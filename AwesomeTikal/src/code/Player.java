package code;

public class Player {

	private String color;
	private String name;
	
	private int piecesRemaining; 
	
	public Player (String name, String color){
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
