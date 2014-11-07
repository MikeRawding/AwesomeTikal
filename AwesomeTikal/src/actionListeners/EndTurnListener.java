package actionListeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import code.GameModel;


public class EndTurnListener implements ActionListener{

	JPanel panel;
	
	public EndTurnListener(JPanel p){
		panel = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.setBackground(GameModel.nextPlayer().getColor());
		
	}
	

}
