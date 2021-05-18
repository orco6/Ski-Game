package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public abstract class Arena implements IArena {
	private double length;
	private SnowSurface surface;
	private WeatherCondition condition;
	
	/**
	 * WinterArena Ctor
	 * @param length is the length of the arena
	 * @param surface enum that returns the friction of the arena
	 * @param condition enum that returns the conditioin of the arena
	 */
	public Arena(double length,SnowSurface surface,WeatherCondition condition){
		if(length==0||surface==null||condition==null)
			throw new IllegalArgumentException("WinterArena:C'tor Invalid values");
		this.length=length;
		this.surface=surface;
		this.condition=condition; 
	}
	
	/**
	 * return the friction of the surface
	 */
	@Override
	public double getFriction() {
		return surface.getFriction();
	}

	/**
	 * return if competitor is finished
	 */
	@Override
	public boolean isFinished(IMobileEntity mobileEntity) {
		return mobileEntity.getLocation().getY() >=length;
	}
	
	/**
	 * func that return the length of the arena
	 * @return length
	 */
	public double getLength() {
		return length;
	}
}
