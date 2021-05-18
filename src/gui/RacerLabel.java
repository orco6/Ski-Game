package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.entities.sportsman.WinterSportsman;

public class RacerLabel extends JLabel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * ctor of RacerLabel
	 * @param imageIcon
	 */
	public RacerLabel(ImageIcon imageIcon) {
		super(imageIcon);
	}
	/**
	 * func that each competitor update the loaction of the label
	 */
	@Override
	public void update(Observable o, Object arg) {
		WinterSportsman racer=(WinterSportsman)o;
		this.setLocation((int)(racer.getLocation().getX()),(int)(racer.getLocation().getY()));	
		
		
	}
	
}
