package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class AddTileListener implements ActionListener {
	
	private Board _board;
	private int _x;
	private int _y;
	
	public AddTileListener(Board board, int x, int y){
		_board = board;
		_x = x;
		_y = y;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_board.addTile(_x, _y);

	}

}
