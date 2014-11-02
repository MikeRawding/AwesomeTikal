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

}
