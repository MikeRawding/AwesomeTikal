package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import code.Board;
import code.GameModel;

public class AddTileListener implements ActionListener {
	
	private Board _board;

	
	public AddTileListener(Board board){
		_board = board;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_board.placeTile(GameModel.onDeckTile);

	}

}
