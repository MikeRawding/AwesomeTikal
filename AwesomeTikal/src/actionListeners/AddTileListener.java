package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import code.Board;

public class AddTileListener implements ActionListener, MouseListener {
	
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

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
