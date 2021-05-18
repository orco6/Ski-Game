package game.entities;

import utilities.Point;

public interface IMobileEntity {
	/**
	 * func that improve the speed and the location of the competitor
	 * @param friction
	 */
	public void move(double friction);
	/**
	 * func that returns the location of the competitor
	 * @return loaction
	 */
	public Point getLocation();
}
