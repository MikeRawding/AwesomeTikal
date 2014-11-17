package actionListeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import resources.NoTilesRemainException;
import code.Board;
import code.GameModel;


public class EndTurnListener implements ActionListener{

	JPanel panel;
	Board b;
	
	public EndTurnListener(Board b, JPanel p){
		panel = p;
		this.b = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		GameModel.nextPlayer();
		b.refreshMenuPanel();
	}
	

}
