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
		try{
			tile01.addPieceToBoard(player01, new Piece(player01));
		}
		catch(InvalidMoveException e){
			System.out.println(e.getMessage());			
		} catch (NoActionPointsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(player01.getPiecesRemaing() == 9);
	}
		
	
	@Test
	public void ownerTest02(){
		Player player01 = new Player("Mike", Color.YELLOW);
		Player player02 = new Player("Tom", Color.BLUE);
		Temple temple01 = new Temple(new int[] {0,0,0,0,0,0}, 1);
		try {
			temple01.addPiece(player01, new Piece(player01));
		} catch (NoActionPointsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			temple01.addPiece(player01, new Piece(player01));
		} catch (NoActionPointsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			temple01.addPiece(player02, new Piece(player02));
		} catch (NoActionPointsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			temple01.addPiece(player02, new Piece(player02));
		} catch (NoActionPointsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			assertTrue(temple01.owner().equals(player02));
		} catch (UnoccupiedTileException e) {
			System.out.println(e.getMessage());
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
	
	@Test
	public void ownerTest05() throws InvalidMoveException, NoOwnerException, UnoccupiedTileException, NoActionPointsException{
		Player player01 = new Player("Mike", Color.YELLOW);
		Player player02 = new Player("Tom", Color.BLUE);
		Temple tile01 = new Temple(new int[] {0,0,0,0,0,0}, 1);
		tile01.addPiece(player01, new Piece(player01));
		tile01.addPiece(player01, new Piece(player01));
		try {
			tile01.setGuard(player01);
		} catch (InvalidMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoOwnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tile01.addPiece(player02, new Piece(player02));
		tile01.addPiece(player02, new Piece(player02));
		tile01.addPiece(player02, new Piece(player02));
		try {
			assertTrue("Temple returning player 2 as owner despite being guarded by 1", tile01.owner().equals(player01));
		} catch (UnoccupiedTileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoOwnerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

