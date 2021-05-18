package game.entities;

import java.util.Observable;

import utilities.Point;

public abstract class Entity extends Observable {
	private Point location;
	/**
	 * Default ctor of Entity
	 */
	public Entity()
	{
		this.location = new Point(0,0);
	}
	/**
	 * ctor of Entity
	 * @param p is the location of entity
	 */
	public Entity(Point p)
	{
		this.location = new Point(p.getX(),p.getY());
	}
	/**
	 * func that returns the location
	 * @return location
	 */
	public Point getLocation()
	{
		return this.location;
	}
	/**
	 * fun that sets the location
	 * @param pointi new point to sets the location
	 */
	public void setLocation(Point pointi)
	{
		if(pointi==null)
		{
			throw new IllegalArgumentException("illegal argument");
		}
		
		this.location = new Point(pointi.getX() , pointi.getY());
	}


}
