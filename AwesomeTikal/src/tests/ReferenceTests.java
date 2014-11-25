package tests;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;

import code.*;
import resources.*;


public class ReferenceTests {
	


	
	@Test
	public void addPieceToBoardTest01(){
		Player player01 = new Player("Mike", Color.YELLOW);
		Tile tile01 = new Tile(new int[] {0,0,0,0,0,0});
		tile01.addPieceToBoard(player01, new Piece(player01));
		assertTrue(player01.getPiecesRemaing() == 9);
	}
		
	
	@Test
	public void ownerTest02(){
		Player player01 = new Player("Mike", Color.YELLOW);
		Player player02 = new Player("Tom", Color.BLUE);
		Temple temple01 = new Temple(new int[] {0,0,0,0,0,0}, 1);
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player01, new Piece(player01));
		
			temple01.addPiece(player02, new Piece(player02));
		
		temple01.addPiece(player02, new Piece(player02));
		try {
			assertTrue(temple01.owner().equals(player02));
		} catch (NoOwnerException e) {
			System.out.println(e.getMessage());
			System.out.println("Tie expected");
		}
	}
	
	@Test
	public void ownerTest03(){
		Player player01 = new Player("Mike", Color.YELLOW);
		Player player02 = new Player("Tom", Color.BLUE);
		Temple temple01 = new Temple(new int[] {0,0,0,0,0,0}, 1);
	
		assertTrue(temple01.isUnoccupied());
		
	}
	
	
	@Test
	public void ownerTest04() throws NoActionPointsException{
		Player player01 = new Player("Mike", Color.YELLOW);
		Player player02 = new Player("Tom", Color.BLUE);
		Temple tile01 = new Temple(new int[] {0,0,0,0,0,0}, 1);
		tile01.addPiece(player01, new Piece(player01));
	
		assertFalse(tile01.isUnoccupied());
		
	}

}

