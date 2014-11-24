package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import resources.InvalidMoveException;
import resources.NoActionPointsException;
import resources.NoTilesRemainException;
import code.Board;
import code.GameModel;

public class AddTileListener implements ActionListener {
	
	private Board _board;

	
	public AddTileListener(Board board){
		_board = board;	
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		try {
			_board.placeTile(GameModel.getOnDeckTile());
			GameModel.nextTile();
		} catch (InvalidMoveException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (NoActionPointsException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}catch (NoTilesRemainException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		_board.refreshMenuPanel();

	}

}
