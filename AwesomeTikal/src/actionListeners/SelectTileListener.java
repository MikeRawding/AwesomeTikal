package actionListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import code.Board;


public class SelectTileListener implements MouseListener {

	private Board b;
	private int x;
	private int y;
	
	public SelectTileListener(Board b, int x, int y){
		this.b = b;
		this.y = y;
		this.x = x;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		b.setSelected(x,y);
		
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
