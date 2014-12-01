package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import resources.InvalidMoveException;
import code.Board;
import code.Temple;

public class TempleListener implements ActionListener {

	
	private Temple temple;
	private Board board;
	
	public TempleListener(Board b, Temple t){
		temple = t;
		board = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			temple.incrementTempleValue();
		} catch (InvalidMoveException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		board.refreshMenuPanel();
		board.pack();

	}

}
