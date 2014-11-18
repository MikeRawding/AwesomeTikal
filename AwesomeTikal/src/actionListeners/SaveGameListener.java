package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;
import code.SaveTest;

public class SaveGameListener implements ActionListener {

	private Board b;
	public SaveGameListener(Board b){
		this.b = b;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		SaveTest.saveGame(b);

	}

}
