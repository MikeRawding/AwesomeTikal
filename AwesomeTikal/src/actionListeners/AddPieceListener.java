package actionListeners;

import java.awt.event.ActionEvent;

import code.Piece;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import resources.InvalidMoveException;
import resources.NoActionPointsException;
import resources.NoTilesRemainException;
import code.Board;
import code.GameModel;

public class AddPieceListener implements ActionListener {

	private Board b;
	
	
	public AddPieceListener(Board b){
		this.b = b;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		b.getStarterTile().addPieceToBoard(GameModel.getPlayer(), new Piece(GameModel.getPlayer()));
		b.refreshMenuPanel();
	}

}
