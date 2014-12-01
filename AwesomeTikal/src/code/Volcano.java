package code;

import java.io.Serializable;

import javax.swing.JLabel;

public class Volcano extends Tile implements Serializable{

	/**
	 * Constructor for a subclass of Tile.
	 * 
	 * @param sides int [] of paths.
	 */
	public Volcano(int[] sides) {
		super(sides);
		center.add(new JLabel("Volcano"));
	}
		
}
