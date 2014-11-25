package code;

import java.io.Serializable;

import javax.swing.JLabel;

public class Volcano extends Tile implements Serializable{

	public Volcano(int[] sides) {
		super(sides);
		center.add(new JLabel("Volcano"));
	}
		
}
