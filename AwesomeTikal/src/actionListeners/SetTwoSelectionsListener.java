package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;

public class SetTwoSelectionsListener implements ActionListener {

	private Board b;
	
	public SetTwoSelectionsListener(Board b){
		this.b = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		b.setTwoSelections(true);

	}

}
