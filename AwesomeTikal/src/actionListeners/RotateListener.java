package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.Board;
import code.GameModel;

public class RotateListener implements ActionListener {
	
	private boolean clockwise;
	private Board b;
	
	
	public RotateListener(Board board,Boolean clock) {
		b = board;
		clockwise = clock;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(clockwise){
			GameModel.getOnDeckTile().rotateClockwise();
		}
		else{
			GameModel.getOnDeckTile().rotateCounterClockwise();
		}
		b.refreshOnDeckPreview();
	}

}