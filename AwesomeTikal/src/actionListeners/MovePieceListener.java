package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class MovePieceListener implements ActionListener {

	private Board b;
	
	public MovePieceListener(Board b){
		this.b = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		b.setTwoSelections(false);

	}

}
