package tests;
import static org.junit.Assert.*;

import org.junit.*;

import code.*;
import resources.*;


public class ReferenceTests {
	


	
	@Test
	public void addPieceToBoardTest01(){
		Player player01 = new Player("Mike", "Yellow");
		Tile tile01 = new Tile();
		try{
			tile01.addPieceToBoard(player01, new Piece(player01));
		}
		catch(InvalidMoveException e){
			System.out.println(e.getMessage());			
		}
		assertTrue(player01.getPiecesRemaing() == 9);
	}
		
	
	@Test
	public void ownerTest02(){
		Player player01 = new Player("Mike", "Yellow");
		Player player02 = new Player("Tom", "Blue");
		Temple temple01 = new Temple(1);
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player02, new Piece(player02));
		temple01.addPiece(player02, new Piece(player02));
		try {
			assertTrue(temple01.owner().equals(player02));
		} catch (UnoccupiedTileException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NoOwnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void ownerTest03(){
		Player player01 = new Player("Mike", "Yellow");
		Player player02 = new Player("Tom", "Blue");
		Temple temple01 = new Temple(1);
	
		assertTrue(temple01.isUnoccupied());
		
	}
	
	
	@Test
	public void ownerTest04(){
		Player player01 = new Player("Mike", "Yellow");
		Player player02 = new Player("Tom", "Blue");
		Temple tile01 = new Temple(1);
		tile01.addPiece(player01, new Piece(player01));
	
		assertFalse(tile01.isUnoccupied());
		
	}
	
	public void ownerTest05(){
		Player player01 = new Player("Mike", "Yellow");
		Player player02 = new Player("Tom", "Blue");
		Temple tile01 = new Temple(1);
		tile01.addPiece(player01, new Piece(player01));
		tile01.addPiece(player01, new Piece(player01));
		tile01.setGaurd(player01);
		tile01.addPiece(player02, new Piece(player02));
		tile01.addPiece(player02, new Piece(player02));
		tile01.addPiece(player02, new Piece(player02));
		
		assertTrue(tile01.owner().equals(player01), "Temple returning player 2 as owner despite being guarded by 1");
	}

}

