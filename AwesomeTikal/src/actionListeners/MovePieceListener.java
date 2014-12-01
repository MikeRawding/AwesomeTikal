package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import resources.InvalidMoveException;
import code.Board;

public class MovePieceListener implements ActionListener {

	private Board b;
	
	public MovePieceListener(Board b){
		this.b = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		b.setTwoSelections(false);
		try {
			b.movePiece(b.getPrevSelectedX(), b.getPrevSelectedY(), b.getSelectedX(), b.getSelectedY());
		} catch (InvalidMoveException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		System.out.println(b.calculatePath(b.getPrevSelectedX(), b.getPrevSelectedY(), b.getSelectedX(), b.getSelectedY()));
	}

}
