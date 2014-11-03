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
	public void ownerTestTempleGuarded() throws InvalidMoveException, NoOwnerException{
		Player player01 = new Player("Mike", "Yellow");
		Player player02 = new Player("Tom", "Blue");
<<<<<<< HEAD
		Temple temple01 = new Temple(1);
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player02, new Piece(player02));
		temple01.addPiece(player02, new Piece(player02));
		temple01.addPiece(player02, new Piece(player02));
=======
<<<<<<< HEAD
<<<<<<< HEAD
		Temple tile01 = new Temple(1);
		tile01.addPiece(player01, new Piece(player01));
		tile01.addPiece(player01, new Piece(player01));
		try{
			tile01.setGuard(player01);
		}
		catch(InvalidMoveException e){
			System.out.println(e.getMessage());
		}
		catch(NoOwnerException e){
			System.out.println(e.getMessage());
		}
		tile01.addPiece(player02, new Piece(player02));
		tile01.addPiece(player02, new Piece(player02));
		tile01.addPiece(player02, new Piece(player02));
>>>>>>> branch 'master' of https://github.com/MikeRawding/AwesomeTikal
		try {
<<<<<<< HEAD
			assertTrue(temple01.owner().equals(player02));
=======
			assertTrue(tile01.owner().equals(player01));
=======
		Temple temple01 = new Temple(1);
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player02, new Piece(player02));
		temple01.addPiece(player02, new Piece(player02));
		temple01.addPiece(player02, new Piece(player02));
		try {
			assertTrue(temple01.owner().equals(player02));
>>>>>>> FETCH_HEAD
=======
		Temple temple01 = new Temple(1);
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player01, new Piece(player01));
		temple01.addPiece(player02, new Piece(player02));
		temple01.addPiece(player02, new Piece(player02));
		temple01.addPiece(player02, new Piece(player02));
		try {
			assertTrue(temple01.owner().equals(player02));
>>>>>>> FETCH_HEAD
>>>>>>> branch 'master' of https://github.com/MikeRawding/AwesomeTikal
		} catch (UnoccupiedTileException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
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
<<<<<<< HEAD
}
=======
}
>>>>>>> FETCH_HEAD
